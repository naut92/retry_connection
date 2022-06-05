package com.retry_connection.service;


import java.util.concurrent.CompletableFuture;

public interface RetryConnectionService {
    Object someMethod1(String someParameter);
    Object someMethod2(Object someParameter);
}
