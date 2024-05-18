FROM openjdk:17-jdk-slim

WORKDIR /app

COPY . /app

RUN chmod +x gradlew

EXPOSE 59001

CMD ["./gradlew", "runServer"]