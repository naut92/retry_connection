
#Java 11, #Spring Boot, #AOP, #CompletableFuture, #Async, #docker-compose, #custom annotation


Spring-boot application. Returns HTTP 200 and empty body. Limits the number of requests from one IP address to N times per X minutes/seconds.
If the number of requests is greater, then it returns a 502 error code, until the number of requests for the specified interval falls below N.

- endpoint: http://localhost:8081/api/ip

Docker image:
1. ./gradlew build
2. docker build --build-arg JAR_FILE=build/libs/\*.jar -t retry-connection-docker .
3. docker-compose up
4. docker-compose down
