FROM openjdk:11
VOLUME /tmp
EXPOSE 8092
ADD target/as-0.0.1-SNAPSHOT.jar as-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/as-0.0.1-SNAPSHOT.jar"]