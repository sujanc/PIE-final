FROM openjdk:11.0-oracle
ADD ./target/porting-0.0.3-SNAPSHOT.jar /usr/src/porting-0.0.3-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar","porting-0.0.3-SNAPSHOT.jar"]