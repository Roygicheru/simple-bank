# ==========================================
# STAGE 1: Build the application
# ==========================================
FROM eclipse-temurin:17-jdk AS builder
WORKDIR /app

# Copy the Maven wrapper files first
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Download dependencies (This caches the downloads so future builds are faster)
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

# Copy the actual source code and build the application
# We skip tests here because our GitHub Actions pipeline already proved the tests pass!
COPY src ./src
RUN ./mvnw clean package -DskipTests

# ==========================================
# STAGE 2: Create the secure production image
# ==========================================
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Create a non-root user for DevSecOps best practices
RUN addgroup --system spring && adduser --system --ingroup spring spring
USER spring:spring

# Copy ONLY the compiled .jar file from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the standard Spring Boot port
EXPOSE 8080

# Command to execute the application
ENTRYPOINT ["java", "-jar", "app.jar"]