# CA3: Build Tools with Gradle

Start Date: 18, April

End Date: 15, May

[Git Repository](https://github.com/SwitchQA/devops-23-24-JPE-1222637)

## Part 2

* Go to https://www.vagrantup.com/downloads.html
* Download and install the Vagrant version according to your OS
* To check if everything is ok type ```vagrant -v```

* You can create a shallow file of a Vagrant file using ```vagrant init```
* But for this tutorial let's skip this and get the Vagrant file from the repository
* https://bitbucket.org/pssmatos/vagrant-multi-spring-tut-demo/ as an
  initial solution


* We'll now need to edit the Vagrant file to match our needs
* Instead of using ```ubuntu/bionic64``` we'll use ```ubuntu/focal64``` to use a more recent Ubuntu version.
* Update JAVA version on the provision to whatever you're using, at the time of this tutorial it's 17.
* Change the git clone, to clone your own repository
* Change the path right after it
* Update the Vagrantfile configuration so that it uses your own gradle version
* This can be done by defining the gradle version on the gradle-wrapper.properties file
  ```distributionUrl=https\://services.gradle.org/distributions/gradle-7.4.1-bin.zip```


* Finally run ```vagrant up```
* If everything goes according to plan, and this will take several minutes, 
you should see the last task run as
```Task :bootRun```
* And the web servlet ```Completed initilization in x seconds```

* Next see the changes
  necessary so that the spring application uses the H2 server in the db VM.
* Although the application is already running in a H2 server we'll explain how to implement if it was not


* On src/main/resources/application.properties add the following lines
```
server.servlet.context-path=/basic-0.0.1-SNAPSHOT
spring.data.rest.base-path=/api
spring.datasource.url=jdbc:h2:tcp://192.168.33.11:9092/./jpadb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.h2.console.settings.web-allow-others=true
```

* On React app.js update the ComponentDidMount method from
```
client({method: 'GET', path: '/api/employees'}).done(response => {
```
to
```
client({method: 'GET', path: '/basic-0.0.1-SNAPSHOT/api/employees'}).done(response => {
```
NOTES: If anything goes wrong with ```vagrant up``` you can always run ```vagrant destroy``` and try again

## Part 2.1 - An alternative to VirtualBox using Vagrant.

* For our alternative we'll use Docker.
* We've considered using Hyper-V but it's not available on Windows 10 Home

* First we'll need to install Docker
* Go to https://docs.docker.com/get-docker/
* Download and install Docker Desktop
* To check if everything is ok type ```docker -v```

* Update the Vagrantfile to use the Docker provider
```
Vagrant.configure("2") do |config|
  config.vm.provider "docker" do |d|
    d.image = "ubuntu:latest"
  end
end
```

