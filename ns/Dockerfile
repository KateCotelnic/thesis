FROM openjdk:11
VOLUME /tmp
EXPOSE 8093
ADD target/ns-0.0.1-SNAPSHOT.jar ns-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/ns-0.0.1-SNAPSHOT.jar"]