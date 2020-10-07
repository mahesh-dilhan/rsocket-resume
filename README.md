# RSocket resume

In this repository 
* RSocket server connect -gcloud setup (Responder)
* Client connec to the server (Requester) 

If you are using Spring-Boot flavor you can simply test below code snippet 

```
@Bean
Mono<RSocketRequester> rSocketrequestermono() {
  return RSocketRequester.builder().rsocketConnector(rsocketConnector -> rsocketConnector.reconnect(Retry.fixedDelay(Integer.MAX_VALUE,Duration.ofSeconds(1)))).connectTcp("localhost",7000);
}
```
