FROM openjdk:8
ADD /target/gkassessment.jar gkassessment.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","gkassessment.jar"]
