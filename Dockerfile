FROM openjdk:8
ADD target/grocery.jar grocery.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "grocery.jar"]