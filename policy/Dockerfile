FROM openjdk:11.0-oracle
ADD ./target/policy-0.0.3-SNAPSHOT.jar /usr/src/policy-0.0.3-SNAPSHOT.jar
EXPOSE 8097
WORKDIR usr/src
ENTRYPOINT ["java","-jar","policy-0.0.3-SNAPSHOT.jar"]