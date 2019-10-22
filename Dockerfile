FROM openjdk:11
WORKDIR /usr/src
ADD target/muzix-0.0.1-SNAPSHOT.jar /usr/src/muzixapp.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/usr/src/muzixapp.jar"]