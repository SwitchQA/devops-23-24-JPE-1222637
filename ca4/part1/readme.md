# CA4: Containers with Docker

Start Date: 16, May

End Date: 29, May

[Git Repository](https://github.com/SwitchQA/devops-23-24-JPE-1222637)

## Version 1 - Build the chat server ”inside” the Dockerfile

* So the plan here is to be able to package and execute the chat server in a container
* So first let's start by cloning the repo from here
```bash
git clone https://bitbucket.org/pssmatos/gradle_basic_demo
```

* Next let's create a Dockerfile to build the chat server inside the Docker image.

* To build the Docker file we need a few things:
  * The FROM command specifies the base image to use to start the build process.
  * The WORKDIR instruction sets the working directory 
  * The COPY: Copy the project files into the Docker image.
  * The RUN: Build the project using Gradle.
  * The CMD: Specify the command to run the chat server.

* So let's create the Dockerfile
* Let's use a Docker image with Java 17 and Gradle 8.7

* Since our project already has a gradle wrapper using 8.7 we can skip that part and simply add the instruction

```Dockerfile
FROM openjdk:17-jdk-slim
```

* But let's say we didn't had the gradle wrapper, we would need to add something like

```Dockerfile
FROM gradle:8.7.0-jdk17 AS build
```

* If by change the Docker Hub, or any other registry we might be using didn't had our specified requirements 
we could build our own image with the required dependencies and use that as the base image.
To keep this short I won't demonstrate that option.

* Next we need to set the working directory
* The WORKDIR instruction sets the working directory for any subsequent 
RUN, CMD, ENTRYPOINT, COPY, and ADD instructions in the Dockerfile.

```Dockerfile
WORKDIR /app
```

* Next we need to copy the project files into the Docker image.
The Dockerfile file (lol) is important in this part since the COPY command is relative to the Dockerfile location.

```Dockerfile
COPY . /app
```

* EXPOSE is used to specify the port the container listens on at runtime.
The image works fine even without this instruction given further commands, but it's important to document 
the ports the container listens on.

* Finally to execute the chat server we need to run the gradle task that starts the server.

* With all of this we should end up with a Dockerfile similar to this

```Dockerfile
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY . /app

RUN chmod +x gradlew

EXPOSE 59001

CMD ["./gradlew", "runServer"]
```

* Now to execute this, enter a terminal inside the root folder of the project and run the following command

```bash 
docker build -t chat-server .
```

* This will build the Docker image with the name chat-server
* You might open Docker Desktop if you're using Windows like me to check the "Images" tab to see the image being built.

* After the image is built we can run the container with the following command

```bash
docker run -p 59001:59001 chat-server
```

## Version 2 - Build the chat server ”inside” the Dockerfile
* Note: Be aware that you should use in the container a JDK that is the same or newer than the one used to build
the application



