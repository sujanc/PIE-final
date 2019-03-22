FROM openjdk:11.0-oracle
ADD ./target/insurerregservice-0.0.3-SNAPSHOT.jar /usr/src/insurerregservice-0.0.3-SNAPSHOT.jar
EXPOSE 8090
WORKDIR usr/src
ENTRYPOINT ["java","-jar","insurerregservice-0.0.3-SNAPSHOT.jar"]
