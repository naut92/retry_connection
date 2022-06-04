package com.retry_connection.service;


import com.retry_connection.annotation.TimeoutConnection;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.completedFuture;


@Service
public class RetryConnectionServiceImpl implements RetryConnectionService {


    @TimeoutConnection()
    public String someMethod1(String remoteAddress, String someParameter) {
        //do something
        return someParameter;
    }

    @TimeoutConnection()
    public CompletableFuture<Object> someMethod2(String remoteAddress, Object someParameter) {
        //do something
        return completedFuture(someParameter);
    }

    @Override
    public CompletableFuture<Object> someMethod3(String remoteAddress, String someParameter) {
        return null;
    }
}

