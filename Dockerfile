FROM openjdk:17-slim as build
LABEL maintainer="Danil Svinoukhov <svinoukhov03@gmail.com>"
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)

#stage 2
FROM openjdk:17-slim
#Add volume pointing to /tmp
VOLUME /tmp
#Copy unpackaged application to new container
ARG DEPENDENCY=/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
#execute the application
ENTRYPOINT ["java","-cp","app:app/lib/*","com.vorstu.DeliveryServiceBackend.DeliveryServiceApplication"]