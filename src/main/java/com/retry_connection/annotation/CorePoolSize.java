package com.retry_connection.annotation;

import org.springframework.beans.factory.annotation.Value;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Value("${server.core_pool_size}")
@Retention(RetentionPolicy.RUNTIME)
public @interface CorePoolSize {
}
