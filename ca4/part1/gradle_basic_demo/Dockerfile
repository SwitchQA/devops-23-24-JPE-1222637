FROM gradle:8.7.0-jdk17 AS build
WORKDIR /app
COPY . /app
RUN gradle build
EXPOSE 59001
CMD ["gradle", "runServer"]