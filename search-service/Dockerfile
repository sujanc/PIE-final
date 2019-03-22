FROM openjdk:11.0-oracle
ADD ./target/search-service-0.0.3-SNAPSHOT.jar /usr/src/search-service-0.0.3-SNAPSHOT.jar
EXPOSE 8095
WORKDIR usr/src
ENTRYPOINT ["java","-jar","search-service-0.0.3-SNAPSHOT.jar"]
