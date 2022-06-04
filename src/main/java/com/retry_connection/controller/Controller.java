package com.retry_connection.controller;

import com.retry_connection.service.RetryConnectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class Controller {

    private final RetryConnectionService service;

    public Controller(RetryConnectionService service) {
        this.service = service;
    }

    @GetMapping("/ip")
    public CompletableFuture<ResponseEntity<?>> getIp(HttpServletRequest request) throws ExecutionException, InterruptedException {
        CompletableFuture<Object> promise = CompletableFuture
                .anyOf((CompletableFuture<?>) service.someMethod1(/*request.getRemoteAddr()*/"62.4.43.31", "parameter"));
        if (promise.get() != null)
            return CompletableFuture.completedFuture(ResponseEntity.ok(promise.get()));
        else
            return CompletableFuture.completedFuture(new ResponseEntity<>(HttpStatus.BAD_GATEWAY));
    }
}
