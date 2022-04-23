FROM openjdk:17-oracle
VOLUME /tmp
EXPOSE 8081
ADD build/libs/giulianotrincavelli-0.0.1-SNAPSHOT.jar giulianotrincavelli.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/giulianotrincavelli.jar"]

