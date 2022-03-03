# Use th efollowing if you are on an m1 mac
FROM --platform=linux/amd64 openjdk:17
# else use this if you're not on an M1 mac
#From openjdk:17
VOLUME /tmp
COPY target/Project2-0.0.1-SNAPSHOT.jar  Project2-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","Project2-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080:8080
