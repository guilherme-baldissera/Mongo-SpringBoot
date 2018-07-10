FROM java:8
MAINTAINER Guilherme Augusto Moreira Baldissera
VOLUME /tmp
COPY target/mysql-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8080