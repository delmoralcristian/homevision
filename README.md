# Houses Management API #

This README would normally document whatever steps are necessary to get your application up and running.

### What is this repository for? ###

This application is in charge of houses management.

### Considerations ###

* Basic API security implemented
* Added swagger for API documentation
* For simplicity the images are downloaded in the tmp/ directory
* Retry pattern implemented to handle flaky API

### How do I get set up? ###

* You need to set in your system:
  * docker
  * java 11

### How do I run it locally? ###

* _mvn clean install_
* _mvn spring-boot:run_

### How do I run it locally with Docker? ###

* _mvn clean package -DskipTests=true docker:build_
* _docker run -d -p 8080:8080 houses_

### How to use the API ###

* Go to swagger(http://localhost:8080/swagger-ui.html)
* First of all, you have to log in
  * Username: homevision
  * Password: homevision2023

### Who do I talk to? ###

* delmoralcristian@gmail.com
