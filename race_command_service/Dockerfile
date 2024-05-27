FROM eclipse-temurin:17.0.6_10-jre-jammy

EXPOSE 8080
RUN mkdir -p /app
COPY ./build/libs/race_command_service*.jar /app/race_command_service.jar
RUN chown -R 1001 /app && chmod 777 /app

USER 1001
WORKDIR /app

CMD java -jar /app/race_command_service.jar