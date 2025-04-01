FROM openjdk:24-jdk-bookworm
WORKDIR home
COPY build/libs/Blanco-0.0.1-SNAPSHOT.jar /home/app.jar
CMD ["java", "-jar", "/home/app.jar"]
