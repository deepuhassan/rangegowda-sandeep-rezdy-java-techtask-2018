# Lunch Recipe generator

An example application using Spring boot and docker which suggest recipes based on ingredients availability in the store

## Technology stack
-   Spring Boot
-   Spring Boot Test/JUnit/RestAssured
-   Docker


## Build Instruction

# Building using maven

#Prerequisite
- Maven and Java 8 should be installed in the machine

# Mavnen command o build and start the app  
  mvn spring-boot:run

## Building Docker image using Maven

#Prerequisite

- Maven,Java 8 and Docker engine should be installed in the machine

# Docker image build command

mvn install dockerfile:build

#Command to run application 

docker run -p 3333:3333 -t deepuhassan/recipegenerator

## Downloading the image from hub and running

Step 1: docker login

Step 2: execute 'docker pull deepuhassan/recipegenerator'

Step 3: execute 'docker run -p 3333:3333 -t deepuhassan/recipegenerator'


# Endpoint to access the API for testing :

http://localhost:3333/lunch	



