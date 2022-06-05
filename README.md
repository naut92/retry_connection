
- endpoint: http://localhost:8081/api/ip

Docker image:
1. ./gradlew build
2. docker build --build-arg JAR_FILE=build/libs/\*.jar -t retry-connection-docker .
3. docker-compose up
4. docker-compose down
