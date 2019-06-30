FROM anapsix/alpine-java
MAINTAINER Andrijana Jeremic <andrijana.jeremi@gmail.com>
ADD target/auth-service-0.0.1-SNAPSHOT.jar auth-service.jar
ENTRYPOINT ["java", "-jar", "/auth-service.jar"]
EXPOSE 8082