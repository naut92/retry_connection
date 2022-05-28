package com.retry_connection.aop;

import com.retry_connection.annotation.LimitConnection;
import com.retry_connection.annotation.TimePeriod;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.completedFuture;
import static java.util.concurrent.CompletableFuture.supplyAsync;

@Slf4j
@Aspect
@Component
public class AopService {

    @Pointcut("@annotation(com.retry_connection.annotation.TimeoutConnection)")
    public void retryPointCut() {
    }

    @Around("retryPointCut()")
    public CompletableFuture<Object> checkConnections(ProceedingJoinPoint pjp) throws Throwable {
        String remoteAddress = Arrays.toString(new Object[]{pjp.getArgs()[0]});
        //System.out.println(remoteAddress);
        Object proceed = getClientIp(remoteAddress, pjp);
        //System.out.println("proceed: " + proceed);
        return completedFuture(proceed);
    }

    @LimitConnection
    private int limitConnection;

    @TimePeriod
    private long timePeriod;

    //private static final Set<byte[]> ips = new HashSet<>();
    private static Set<String> ips = new HashSet<>();
    private static boolean timeReset = false;

    private boolean isPresent = false;

    private static int countConnection = 0;

    @Async
    public CompletableFuture<Object> getClientIp(/*byte[]*/ String remoteAddress, ProceedingJoinPoint pjp) throws Throwable {

        ips = addIp(remoteAddress);

        if (!isPresent) {
            ips = addIpIfPresent(remoteAddress);
            return completedFuture(true);
        } else {
            if (countConnection > limitConnection - 1) {
                applyTimer();
                return completedFuture(!timeReset);
            } else if (!timeReset) {
                incrementCounter();
                return completedFuture(pjp.proceed());
            }
        }
        return completedFuture(false);
    }

    private Set<String> addIp(String remoteAddress){
        for (Object key : ips){
            //if (Arrays.equals(remoteAddress, (byte[]) key)) {
            if (remoteAddress.equals(key)){
                isPresent = true;
                break;
            }
        }
        return ips;
    }

    private Set<String> addIpIfPresent(String remoteAddress){
        ips.add(remoteAddress);
        countConnection = 1;
        log.info("add ip address in Set, countConnection: " + countConnection);
        return ips;
    }

    private void applyTimer(){
        timeReset = true;
        countConnection = 0;
        log.info("reset countConnection: " + countConnection + ", timeReset: " + true);
        supplyAsync(this::time);
    }

    private synchronized void incrementCounter(){
        countConnection++;
        log.info("count connection: " + countConnection);
    }

    private boolean time(){
        while (timeReset) {
            try {
                Thread.sleep(timePeriod * 1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            timeReset = false;
        }
        log.info("time(): end,  timeReset: " + false);
        return true;
    }
}
