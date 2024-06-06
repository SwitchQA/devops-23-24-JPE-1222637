# CA5: CI/CD Pipelines with Jenkins

Start Date: 19, May

End Date: 13, June

[Git Repository](https://github.com/SwitchQA/devops-23-24-JPE-1222637)

# Part 1 - Create a Jenkins pipeline to run CA2 Part1

* First and foremost we need to have Jenkins installed and running.

* We have two options

1. Using a Docker Container
2. Using a local installation

* We'll use a local installation for this example and we offer no explanation as to why (kidding it's because it's easier)

* So simply come here -> https://www.jenkins.io/doc/book/installing/war-file/ and follow the instructions.
* Should be easy enough to download the WAR file and run it with Java.

* After you have Jenkins running, you can access it at http://localhost:8080

* Follow the instructions to get the initial password and install the suggested plugins.

* After that you can create a new pipeline and use the Jenkinsfile from the CA2 Part1 repository.

* Click on New Item -> Pipeline -> OK

* In the Pipeline section, you have two options 

1. select Pipeline script from SCM
2. select Pipeline script from Git

* We'll use the second option and provide the repository URL and the credentials.

* Just add the Repository URL. Since the repo is public no credentials are needed

* Possibly change branch to */main

* And add the Script Path which is the path to the Jenkinsfile in the repository, for instance
```ca2/part1/gradle_basic_demo/Jenkinsfile```

  
* Click Save and then Build Now

* The Jenkins file should look something like this

```groovy
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out the code from the repository'
                git branch: 'main', url: 'https://github.com/SwitchQA/devops-23-24-JPE-1222637.git'
            }
        }
        stage('Assemble') {
            steps {
                echo 'Assembling...'
                dir('ca2/part1/gradle_basic_demo') {
                    sh 'chmod +x ./gradlew'
                    sh './gradlew clean assemble'
                }
            }
        }
        stage('Test') {
            steps {
                echo 'Testing...'
                dir('ca2/part1/gradle_basic_demo') {
                    sh './gradlew test'
                    junit 'build/test-results/test/*.xml'
                }
            }
        }
        stage('Archive') {
            steps {
                echo 'Archiving...'
                dir('ca2/part1/gradle_basic_demo') {
                    archiveArtifacts 'build/libs/*.jar'
                }
            }
        }
    }
}
```

# Part 2 - Create a Jenkins pipeline to run CA2 Part2 
Note: Generate a docker image with Tomcat and the war file and publish it in the Docker Hub.

* First we need to create a Dockerfile to build the image

* The Dockerfile should look something like this

```Dockerfile
FROM tomcat:9-jdk17-openjdk

COPY ./build/libs/*.jar /usr/local/tomcat/webapps/

EXPOSE 8080
```

* As for the Jenkinsfile it should look something like this

```groovy
pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'luisafonsoisep/springboot-app:${BUILD_NUMBER}'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code from the repository'
                git branch: 'main', url: 'https://github.com/SwitchQA/devops-23-24-JPE-1222637.git'
            }
        }
        stage('Assemble') {
            steps {
                echo 'Assembling application...'
                dir('ca2/part2/react-and-spring-rest-data-basic') {
                    sh 'chmod +x ./gradlew'
                    sh './gradlew clean assemble'
                }
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests...'
                dir('ca2/part2/react-and-spring-rest-data-basic') {
                    sh './gradlew test'
                    junit 'build/test-results/test/*.xml'
                }
            }
        }
        stage('Javadoc') {
            steps {
                echo 'Generating Javadoc...'
                dir('ca2/part2/react-and-spring-rest-data-basic') {
                    sh './gradlew javadoc'
                    publishHTML(target: [
                        allowMissing: false,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,
                        reportDir: 'build/docs/javadoc',
                        reportFiles: 'index.html',
                        reportName: 'Javadoc'
                    ])
                }
            }
        }
        stage('Archive') {
            steps {
                echo 'Archiving artifacts...'
                dir('ca2/part2/react-and-spring-rest-data-basic') {
                    archiveArtifacts 'build/libs/*.jar'
                }
            }
        }
        stage('Publish Image') {
            steps {
                echo 'Building and pushing Docker image...'
                dir('ca2/part2/react-and-spring-rest-data-basic') {
                    script {
                        // Building the Docker image with a tag that includes the build number
                        def appImage = docker.build("luisafonsoisep/springboot-app:${env.BUILD_NUMBER}")
                        docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
                            // Pushing the image using the tagged image name
                            appImage.push()
                            echo "Building image with tag: luisafonsoisep/springboot-app:${env.BUILD_NUMBER}"
                        }
                    }
                }
            }
        }
    }
}
```

* Now we just create another pipeline on Jenkins as we did before

* Before running it, for this part in particular we need to get a few plugins

1. Docker Pipeline
2. Docker
3. Docker Commons
4. HTML Publisher

* After installing the plugins we need to add the Docker Hub credentials

* Go to Jenkins -> Manage Jenkins -> Manage Credentials -> Global -> Add Credentials
IMPORTANT - The credentials ID should match this part of the Jenkinsfile

```groovy
docker-hub-credentials
```

* After adding the credentials we can run the pipeline

* If there's any issues you can inspect them using the Console Output and using Google / ChatGPT to solve them