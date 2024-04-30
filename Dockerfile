FROM openjdk:11-jdk-slim

ADD target/jsonwebtokenproject-0.0.1-SNAPSHOT.jar jsonwebtokenproject-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","jsonwebtokenproject-0.0.1-SNAPSHOT.jar","--spring.config.location=file:/properties/application.yml"]