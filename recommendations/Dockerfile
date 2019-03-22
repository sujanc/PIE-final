FROM openjdk:11

ADD ./target/recommendations-0.0.3-SNAPSHOT.jar /event/src/recommendations-0.0.3-SNAPSHOT.jar
EXPOSE 8086
WORKDIR event/src
ENTRYPOINT ["java","-jar","recommendations-0.0.3-SNAPSHOT.jar"]
