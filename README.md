# gkpeopleassessment

GK People 

#How to build
Run the following command:

Download the zip or clone the Git repository.

Unzip the zip file (if you downloaded one)

Open Command Prompt and Change directory (cd) to folder containing pom.xml

Open Eclipse

File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip

Select the right project

Choose the Spring Boot Application file (search for @SpringBootApplication)

Right Click on the file and Run as Java Application

#Docker Build
docker build -f Dockerfile -t gkassessment .
docker images 9090:9090 gkassessment

#Push application
docker run -p 9090:9090 gkassessment
