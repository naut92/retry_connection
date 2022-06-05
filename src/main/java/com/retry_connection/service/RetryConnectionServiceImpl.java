package com.retry_connection.service;


import com.retry_connection.annotation.TimeoutConnection;
import org.springframework.stereotype.Service;


@Service
public class RetryConnectionServiceImpl implements RetryConnectionService {


    @TimeoutConnection()
    public Object someMethod1(Object someParameter) {
        //do something
        someParameter = "parametr1";
        return someParameter;
    }
}

