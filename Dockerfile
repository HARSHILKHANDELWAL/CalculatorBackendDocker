# Use a base image with Maven installed
FROM maven:latest AS build

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project files to the container
COPY pom.xml /app/
COPY src /app/src/

# Build the Maven project to generate the WAR file
RUN mvn package

# Use a lightweight Java runtime as the base image to run the application
FROM tomcat:latest

# Copy the WAR file from the Maven build stage to the Tomcat webapps directory
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/

# Expose the port on which Tomcat will run the application (adjust as needed)
EXPOSE 8080

# Start Tomcat when the container starts
CMD ["catalina.sh", "run"]
