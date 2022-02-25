FROM openjdk:17
VOLUME /tmp
COPY target/practiceApp-0.0.1-SNAPSHOT.jar  practiceApp-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/practiceApp-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080:80