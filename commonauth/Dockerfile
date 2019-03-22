FROM openjdk:11.0-oracle
ADD ./target/commonauth-0.0.3-SNAPSHOT.jar /usr/src/commonauth-0.0.3-SNAPSHOT.jar
EXPOSE 8096
WORKDIR usr/src
ENTRYPOINT ["java","-jar","commonauth-0.0.3-SNAPSHOT.jar"]
