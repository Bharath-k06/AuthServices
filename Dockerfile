FROM openjdk:17-slim-buster


WORKDIR /Finanace

COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=container"]
