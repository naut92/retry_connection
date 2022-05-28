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
        //System.out.println(service.someMethod(request.getRemoteAddr()));
        CompletableFuture<Object> response = CompletableFuture
                .supplyAsync(() -> service.someMethod1(/*request.getRemoteAddr()*/"62.4.43.31", "ok"))
                .thenApply(r -> new ResponseEntity<>(HttpStatus.BAD_GATEWAY));
        //if (/*!(boolean)*/response.thenApply(result -> false))
          //  return response.thenApply(result -> new ResponseEntity<>(HttpStatus.BAD_GATEWAY));
        //else
          //  return response.thenApply(result -> ResponseEntity.ok().body(service.someMethod1(request.getRemoteAddr(), "ok")));//result -> new ResponseEntity<>(HttpStatus.OK));
        //return response;
        return null;
    }
}
