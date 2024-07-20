FROM gradle:jdk17 as build
LABEL maintainer="Danil Svinoukhov <svinoukhov03@gmail.com>"
COPY . /home/gradle/app/
WORKDIR /home/gradle/app
RUN gradle build

FROM openjdk:17
COPY --from=build /home/gradle/app/build/libs/delivery-service-backend-0.0.1-SNAPSHOT-plain.jar /app.jar
ENTRYPOINT ["java", "-jar"]
CMD ["app.jar"]
