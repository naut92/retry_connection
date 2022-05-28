package com.retry_connection.config;

import com.retry_connection.annotation.CorePoolSize;
import com.retry_connection.annotation.MaxPoolSize;
import com.retry_connection.annotation.QueueCapacity;
import lombok.experimental.PackagePrivate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@PackagePrivate
@Slf4j
@Configuration
@EnableAsync
public class AsyncConfiguration extends AsyncConfigurerSupport {

    @CorePoolSize
    int corePoolSize;

    @MaxPoolSize
    int maxPoolSize;

    @QueueCapacity
    int queueCapacity;

    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("AsyncTaskThread::");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        return executor;
    }

    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> {
            log.info("Exception: " + ex.getMessage());
            log.info("Method Name: " + method.getName());
            ex.printStackTrace();
        };
    }
}
