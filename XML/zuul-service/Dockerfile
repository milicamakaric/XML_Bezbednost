FROM anapsix/alpine-java
MAINTAINER Andrijana Jeremic <andrijana.jeremi@gmail.com>
ADD target/zuul-service-0.0.1-SNAPSHOT.jar zuul-service.jar
ENTRYPOINT ["java", "-jar", "/zuul-service.jar"]
EXPOSE 8084