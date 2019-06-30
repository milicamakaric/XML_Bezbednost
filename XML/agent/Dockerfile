FROM anapsix/alpine-java
MAINTAINER Andrijana Jeremic <andrijana.jeremi@gmail.com>
ADD target/agent-0.0.1-SNAPSHOT.jar agent.jar
ENTRYPOINT ["java", "-jar", "/agent.jar"]
EXPOSE 8081