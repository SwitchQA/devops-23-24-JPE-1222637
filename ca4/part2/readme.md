# CA4: Containers with Docker - Part 2

Start Date: 16, May

End Date: 29, May

[Git Repository](https://github.com/SwitchQA/devops-23-24-JPE-1222637)

## Docker-Compose

* Docker Compose is a tool for defining and running multi-container Docker applications.

* With Compose, you use a YAML file to configure your application's services.
* Then, with a single command, you create and start all the services from your configuration.


* For this part we'll be using a Spring Boot application that connects to an H2 database.


* You may use the application developed in CA2 Part2, or get this repository
```bash
https://bitbucket.org/pssmatos/tut-basic-gradle-docker/src/master/
```

* You might need slight alterations to application.properties to use a datasource for Docker instead of an IP based one


* Our docker-compose.yml file will look something like this:

```yaml
services:
  db:
    build:
      context: .
      dockerfile: Dockerfile-db
    container_name: h2-db
    ports:
      - "8082:8082"
      - "9092:9092"
    volumes:
      - h2-data:/opt/h2-data

  web:
    build:
      context: .
      dockerfile: Dockerfile-web
    container_name: spring-web
    ports:
      - "8080:8080"
    depends_on:
      - db

volumes:
  h2-data:
    driver: local
```

* The "volumes" section is used to persist the data in the H2 database.


* We'll also need a Dockerfile for our web service:

```Dockerfile
FROM gradle:7.6.2-jdk17
WORKDIR /app
COPY . /app
RUN gradle clean build
EXPOSE 8080
CMD ["java", "-jar", "build/libs/basic-0.0.1-SNAPSHOT.war"]
```

* And a Dockerfile for our database service:
Note: this Dockerfile is completely unnecessary as we're using the official H2 image from Docker Hub,
but it seems to be a requirement for the assignment.

```Dockerfile
FROM oscarfonts/h2:latest
EXPOSE 8082
EXPOSE 9092
```

You may also run a container to which you download the h2 jar file and run it with the following command:

```bash
FROM ubuntu:focal
RUN apt-get update && \
    apt-get install -y wget openjdk-17-jdk-headless && \
    rm -rf /var/lib/apt/lists/* \
WORKDIR /opt/h2
RUN wget https://repo1.maven.org/maven2/com/h2database/h2/1.4.200/h2-1.4.200.jar -O h2.jar
EXPOSE 8082
EXPOSE 9092
CMD ["java", "-cp", "h2.jar", "org.h2.tools.Server", "-ifNotExists", "-web", "-webAllowOthers", "-webPort", "8082", "-tcp", "-tcpAllowOthers", "-tcpPort", "9092"]
```

* To start the services, we run the following command:

```bash
docker-compose up --build
```

* If you have more than one docker-compose file, use the -f flag to specify the file you want to use:

```bash
docker-compose -f docker-compose.yml up --build
```

* The --build flag is used to build the images before starting the services. This is optional but preferable if you're
making changes to the docker-compose.yml file or the Dockerfiles.

* To access the services running you may use

Spring Boot Application: http://localhost:8080

H2 Database Console: http://localhost:8082

* You may also run the following command to enter the h2 shell:

```bash
docker exec -it h2-db /bin/bash
```

* To stop the services, we run the following command:

```bash
docker-compose down
```

* Unlike docker stop, docker-compose down stops and removes all the containers, and networks created by docker-compose up.



