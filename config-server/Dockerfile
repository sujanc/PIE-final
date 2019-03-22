FROM openjdk:11

ADD ./target/config-server-0.0.3-SNAPSHOT.jar /event/src/config-server-0.0.3-SNAPSHOT.jar
EXPOSE 8888
WORKDIR event/src
ENTRYPOINT ["java","-jar","config-server-0.0.3-SNAPSHOT.jar"]