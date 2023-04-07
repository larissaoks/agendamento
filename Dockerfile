FROM openjdk:18
COPY target/agendamento-0.0.1-SNAPSHOT.jar /app/agendamento-0.0.1-SNAPSHOT.jar
ENTRYPOINT java -jar /app/agendamento-0.0.1-SNAPSHOT.jar
