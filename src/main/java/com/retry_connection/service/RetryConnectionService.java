package com.retry_connection.service;


import java.util.concurrent.CompletableFuture;

public interface RetryConnectionService {
    CompletableFuture<Object> someMethod1(String remoteAddress, String someParameter);
    CompletableFuture<Object> someMethod2(String remoteAddress, Object someParameter);
}