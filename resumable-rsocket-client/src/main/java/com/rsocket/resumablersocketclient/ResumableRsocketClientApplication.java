package com.rsocket.resumablersocketclient;

import io.rsocket.core.Resume;
import io.rsocket.resume.PeriodicResumeStrategy;
import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Publisher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.rsocket.server.RSocketServerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.Flow;
import java.util.function.Supplier;

@Log4j2
@SpringBootApplication
public class ResumableRsocketClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResumableRsocketClientApplication.class, args);
	}
//	@Bean
//	Resume rSocketResume() {
//		Resume resume = new Resume();
//		resume.sessionDuration(Duration.ofMinutes(15));
//		resume.retry(
//				Retry.fixedDelay(Long.MAX_VALUE, Duration.ofSeconds(1))
//						.doBeforeRetry(s -> log.debug("Disconnected. Trying to resume..."))
//		);
//		return resume;
//	}
//	@Bean
//	RSocketRequester rSocketRequester(RSocketRequester.Builder rsocketBuilder) {
//		return rsocketBuilder
//				.rsocketConnector(connector -> connector.resume(
//						rSocketResume()
//						)
//				)
//				.connectTcp("localhost",7001)
//				.retry(5)
//				.cache()
//				.block();
//	}
}

//@Log4j2
//@RestController
//class GreetPersonController{
//
//	private final Mono<RSocketRequester> rSocketRequester;
//
//	// Spring Boot is creating an auto-configured RSocketRequester.Builder bean
//	public GreetPersonController(RSocketRequester.Builder builder) {
//		this.rSocketRequester = builder
//				.dataMimeType(MediaType.APPLICATION_JSON)
//				.connectTcp("localhost", 7001).retry(5).cache();
//	}
//	@GetMapping("/greet/{name}")
//	public Publisher<String> greet(@PathVariable String name ) {
//		return this.rSocketRequester.flatMap(requester -> requester.route("greet.person")
//				.data(Mono.just(name))
//				.retrieveMono(String.class));
//	}
//
//
//}
