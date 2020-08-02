package com.rsocket.resumablersocketclient;

import io.rsocket.core.Resume;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Log4j2
@Service
public class GreetService {
    private final Mono<RSocketRequester> requesterMono;

    public GreetService(RSocketRequester.Builder builder) {
        this.requesterMono = builder
//                .rsocketConnector(rSocketConnector -> rSocketConnector.resume(
//                        new Resume().
//                            sessionDuration(Duration.ofSeconds(10))
//                            .retry(
//                                    Retry.fixedDelay(Long.MAX_VALUE, Duration.ofSeconds(1))
//                                            .doBeforeRetry(s -> log.debug("Disconnected. Trying to resume..."))
//                            )
//                ))
                .dataMimeType(MediaType.APPLICATION_CBOR)
                .connectTcp("35.229.137.143", 7000).retry(100).cache();
    }

    public Mono<String> greet(String name) {
        return this.requesterMono.flatMap(requester -> requester.route("greet.person")
                .data(name)
                .retrieveMono(String.class));
    }
}
