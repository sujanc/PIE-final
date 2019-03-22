FROM openjdk:11

ADD ./target/zuul-gatewayservice-0.0.3-SNAPSHOT.jar /event/src/zuul-gatewayservice-0.0.3-SNAPSHOT.jar
EXPOSE 8092
WORKDIR event/src
ENTRYPOINT ["java","-jar","zuul-gatewayservice-0.0.3-SNAPSHOT.jar"]