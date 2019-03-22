FROM openjdk:11.0-oracle
ADD ./target/ExternalDbService-0.0.3-SNAPSHOT.jar /usr/src/ExternalDbService-0.0.3-SNAPSHOT.jar
EXPOSE 8094
WORKDIR usr/src
ENTRYPOINT ["java","-jar","ExternalDbService-0.0.3-SNAPSHOT.jar"]
