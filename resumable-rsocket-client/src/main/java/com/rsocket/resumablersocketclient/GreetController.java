package com.rsocket.resumablersocketclient;

import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class GreetController {

    @Autowired
    private GreetService greetService;

    @GetMapping("/greet/{name}")
    public Publisher<String> greet(@PathVariable String name ) {
        return this.greetService.greet(name);
    }
}
