FROM openjdk:11

ADD ./target/admin-0.0.3-SNAPSHOT.jar /event/src/admin-0.0.3-SNAPSHOT.jar
EXPOSE 8055
WORKDIR event/src
ENTRYPOINT ["java","-jar","admin-0.0.3-SNAPSHOT.jar"]
