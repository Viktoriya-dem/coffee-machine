FROM openjdk:17

COPY ./ /opt
WORKDIR /opt

ENTRYPOINT ["java", "-jar", "build/libs/coffee-machine-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080/tcp