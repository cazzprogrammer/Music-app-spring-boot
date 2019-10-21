FROM openjdk:11
WORKDIR /usr/src
ADD target/muzix-0.0.1-SNAPSHOT.jar /usr/src/muzixapp.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/src/muzixapp.jar"]