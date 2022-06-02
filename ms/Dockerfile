FROM openjdk:11
VOLUME /tmp
EXPOSE 8090
ADD target/ms-0.0.1-SNAPSHOT.jar ms-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/ms-0.0.1-SNAPSHOT.jar"]