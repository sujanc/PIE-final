FROM openjdk:11

ADD ./target/userreg-0.0.3-SNAPSHOT.jar /event/src/userreg-0.0.3-SNAPSHOT.jar
EXPOSE 8093
WORKDIR event/src
ENTRYPOINT ["java","-jar","userreg-0.0.3-SNAPSHOT.jar"]
