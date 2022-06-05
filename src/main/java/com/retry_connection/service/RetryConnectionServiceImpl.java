package com.retry_connection.service;


import com.retry_connection.annotation.TimeoutConnection;
import org.springframework.stereotype.Service;


@Service
public class RetryConnectionServiceImpl implements RetryConnectionService {


    @TimeoutConnection()
    public String someMethod1(String someParameter) {
        //do something
        return someParameter;
    }

    @TimeoutConnection()
    public Object someMethod2(Object someParameter) {
        //do something
        return someParameter;
    }
}

