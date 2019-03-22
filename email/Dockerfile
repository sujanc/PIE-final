FROM openjdk:11

ADD ./target/email-0.0.3-SNAPSHOT.jar /event/src/email-0.0.3-SNAPSHOT.jar
EXPOSE 8055
WORKDIR event/src
ENTRYPOINT ["java","-jar","email-0.0.3-SNAPSHOT.jar"]
