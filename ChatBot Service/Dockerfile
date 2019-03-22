FROM openjdk:11.0-oracle
ADD ./target/chatbot-0.0.1-SNAPSHOT.jar /usr/src/chatbot-0.0.1-SNAPSHOT.jar
EXPOSE 8070
WORKDIR usr/src
ENTRYPOINT ["java","-jar","chatbot-0.0.1-SNAPSHOT.jar"]
