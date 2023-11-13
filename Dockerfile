# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Give execute permission to the mvnw script
RUN chmod +x mvnw

# Build the application
RUN ./mvnw clean install -DskipTests

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "/app/target/product-0.0.1-SNAPSHOT.jar"]