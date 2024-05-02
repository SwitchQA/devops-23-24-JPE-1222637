# CA3: Build Tools with Gradle

Start Date: 18, April

End Date: 15, May

[Git Repository](https://github.com/SwitchQA/devops-23-24-JPE-1222637)

## Part 1

* Use VirtualBox on your computer and create a VM, then install Linux (Ubuntu) on the new VM:
    * Create VM
    * Choose language
    * Define your keyboard layout
    * Select Ubuntu Server
    * Select enp0s3 option
    * Default options for the rest of the installation until
    * Profile setup - Choose your username, password, and hostname.
    * Skip Ubuntu Pro
    * Skip the installation of the OpenSSH server
    * Skip the server snaps installation.
    * After installation, Press the Reboot Now button.


* Install the following packages:
    * Update the packages repositories
   ```bash
  sudoapt update
  ```
    * Install the networks tools
    ```bash
    sudo apt install net-tools
    ```
    * Edit the network configuration file to setup the IP address
    ```bash
    sudo nano /etc/netplan/00-installer-config.yaml
    ```
    * make sure the contents of the file are similar to the following (in this case we are
      setting the IP of the second adapter as 192.168.56.5):
    ```yaml
    network:
      ethernets:
        enp0s3:
          dhcp4: true
        enp0s8:
          addresses:
            - 192.168.56.5/24
  ```
    * Apply the changes
  ```bash
    sudo netplan apply
    ```
    * Install openssh-server so that we can use ssh to open secure terminal sessions to the
      VM (from other hosts)
    ```bash
    sudo apt install openssh-server
    ```
    * Enable password authentication for ssh
      ```bash
      sudo nano /etc/ssh/sshd_config
      ```
    * uncomment the line PasswordAuthentication yes
  ```bash
  sudo service ssh restart
  ```
    * Install an ftp server so that we can use the FTP protocol to transfers files to/from
      the VM (from other hosts)
  ```bash
  sudo apt install vsftpd
  ```
    * Enable write access for vsftpd
  ```bash
  sudo nano /etc/vsftpd.conf
    ```
    * uncomment the line write_enable=YES
  ```bash
  sudo service vsftpd restart
  ```
    * Before moving on from Ubuntu onto another machine run
  ```bash
  sudo systemctl status ssh
    ```
    * To make sure the SSH server is running
    * Since the SSH server is enabled in the VM we can now use ssh to connect to the VM.
    *

* Now let's start working towards running our application and verify installation
  ```bash
  sudo apt install git
  sudo apt install openjdk-17-jdk-headless
  java -version
  git --version
    ```
* Install Maven and verify installation
  ```bash
  sudo apt install maven
  mvn -version
  ```
* git clone https://github.com/SwitchQA/devops-23-24-JPE-1222637.git (you may substitute this for your own repo)
  (repository must be public)

* Now let's test our application with Maven
  * Nagivate the repository to the folder where mvnw is located
    ```bash
    cd devops-23-24-JPE-1222637/ca1/basic
    chmod +x mvnw
    ```
  * Now let's run mvnw
    ```bash
    ./mvnw spring-boot:run
    ```
    * If you don't know your VM's IP address simply input the command
    * The default port for this project is 8080
    ```bash
    ip a
    ```
    * On your browser input the IP address and port number

  * Next, let's try our Gradle application
  * First give graddle wrapper permissions to execute
  * Change the folder path accordingly
    ```bash
    cd devops-23-24-JPE-1222637/ca2/part1/gradle_basic_demo
    chmod +x gradlew
    ```
  * Now let's run gradlew
    ```bash
    ./gradlew build
    ./gradlew runServer
    ./gradlew runClient --args="192.168.56.101 59001" 
    ```

    
    