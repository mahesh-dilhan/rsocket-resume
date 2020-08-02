package com.rsocket.resumablersocketserver;

import io.rsocket.core.Resume;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.rsocket.server.RSocketServerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
@Log4j2
@SpringBootApplication
public class ResumableRsocketServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResumableRsocketServerApplication.class, args);
	}

	@Bean
	RSocketServerCustomizer rSocketResume() {
		Resume resume = new Resume();
		resume.sessionDuration(Duration.ofMinutes(15));
		resume.retry(
				Retry.fixedDelay(Long.MAX_VALUE, Duration.ofSeconds(5))
						.doBeforeRetry(s -> log.debug("Disconnected. Trying to resume..."))
		);
		return rSocketServer -> rSocketServer.resume(resume);
	}
}
@Log4j2
@RestController
class PersonController {

	@MessageMapping("greet.person")
	public Mono<String> greeting(String person) {
		log.info("Grerting {} ",person);
		return Mono.just("Greeting from RSocket " + person);
	}
}
