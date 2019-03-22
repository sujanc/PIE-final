FROM openjdk:11

ADD ./target/settlement-0.0.3-SNAPSHOT.jar /event/src/settlement-0.0.3-SNAPSHOT.jar
EXPOSE 8073
WORKDIR event/src
ENTRYPOINT ["java","-jar","settlement-0.0.3-SNAPSHOT.jar"]
