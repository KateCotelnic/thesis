FROM openjdk:11
VOLUME /tmp
EXPOSE 8091
ADD target/rs-0.0.1-SNAPSHOT.jar rs-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/rs-0.0.1-SNAPSHOT.jar"]
