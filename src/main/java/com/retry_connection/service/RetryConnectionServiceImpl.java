package com.retry_connection.service;


import com.retry_connection.annotation.TimeoutConnection;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.completedFuture;


@Service
public class RetryConnectionServiceImpl implements RetryConnectionService {


    @TimeoutConnection()
    public CompletableFuture<Object> someMethod1(String remoteAddress, String someParameter) {
        //do something
        return completedFuture(someParameter);
    }

    @TimeoutConnection()
    public CompletableFuture<Object> someMethod2(String remoteAddress, Object someParameter) {
        //do something
        return completedFuture(someParameter);
    }
}

