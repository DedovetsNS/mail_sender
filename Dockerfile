FROM openjdk:11
ADD build/libs/sender.jar sender.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","sender.jar"]