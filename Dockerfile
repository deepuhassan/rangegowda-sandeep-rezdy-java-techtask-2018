FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} RecipeGenerator-1.0.jar
ENTRYPOINT ["java","-jar","/RecipeGenerator-1.0.jar"]