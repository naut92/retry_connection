FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
RUN addgroup -S echo && adduser -S echo -G echo
USER echo:echo
ARG JAR_FILE=target/*.jar
COPY /build/libs/retry_connection-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
