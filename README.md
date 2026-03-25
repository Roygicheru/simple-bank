# Simple Bank - DevOps Capstone Payload

## Overview
Simple Bank is a highly lightweight, stateless Spring Boot web application designed specifically to serve as the application payload for a Platform Engineering and DevOps capstone project.

Instead of focusing on complex application logic or persistent database storage, this application uses in-memory state management. This ensures rapid startup times and predictable testing environments, allowing the primary focus to remain on infrastructure automation, CI/CD pipelines, containerization, and observability.

### Features
* **Stateless Architecture:** Account balances are held in active memory using Java's `AtomicReference`. Balances reset to zero upon application or container restart.
* **Basic Banking Operations:** Users can view their balance, deposit funds, and withdraw funds via a simple web UI.
* **Metrics Ready:** Integrated with Spring Boot Actuator to expose health and performance metrics for downstream observability scraping.

## Tech Stack
* **Language:** Java 17
* **Framework:** Spring Boot 4.0.4
* **Build Tool:** Maven
* **Frontend UI:** Thymeleaf & HTML/CSS
* **Observability:** Spring Boot Actuator

## How to Run Locally

### Prerequisites
* JDK 17 installed on your machine.
* A Linux/macOS terminal or Windows PowerShell.

### Starting the Application
The project includes a Maven wrapper, so you do not need to install Maven globally.

1. **Make the wrapper executable (Linux/macOS only):**
   ```bash
   chmod +x mvnw
   
2. **Run the Spring Boot application:**
    ```bash
   ./mvnw spring-boot:run

3. **Access the UI:**
   Open your web browser and navigate to http://localhost:8080.

## Capstone Roadmap

This repository will evolve to include a full Internal Developer Platform deployment flow. Future phases include:

1. Automated UI Testing: Selenium scripts for headless browser testing.

2. CI/CD Pipeline: GitHub Actions for automated testing, DevSecOps scanning (Trivy), and Docker image builds.

3. Containerization: Multi-stage Dockerfile pushing to GitHub Container Registry (GHCR).

4. Cloud Deployment: Orchestration via Docker Compose on a cloud server.

5. Zero Trust Networking: Secure public routing via Cloudflare Tunnels (no open inbound ports).

6. Observability: Metric scraping and visualization using the TICK stack (Telegraf, InfluxDB, Chronograf, Kapacitor).