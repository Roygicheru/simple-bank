# 🏦 Simple Bank - DevOps & Platform Engineering Capstone

## 📌 Overview
Simple Bank is a lightweight, stateless Spring Boot application serving as the core payload for a comprehensive **Internal Developer Platform (IDP)**.

While the application logic is purposefully simple, the surrounding ecosystem demonstrates a professional-grade lifecycle: from **GitFlow** and **DORA-optimized CI pipelines** to **Infrastructure as Code (IaC)** and **Automated Configuration Management**.

---

## 🛠 Tech Stack

### Application Layer
* **Language:** Java 17 (Spring Boot 4.0.4)
* **Build Tool:** Maven
* **UI:** Thymeleaf & HTML/CSS

### Platform & Infrastructure (The "DevOps" Layer)
* **Cloud Provider:** Google Cloud Platform (GCP) - *Project: simple-bank-491910*
* **Infrastructure as Code:** Terraform (Provider v5.x)
* **Configuration Management:** Ansible
* **Containerization:** Docker & Docker Compose
* **Operating Systems:** Fedora 43 (Local) / Ubuntu 22.04 LTS (Cloud)
* **CI/CD:** GitHub Actions (with Selenium & Trivy)

---

## 🏗 Infrastructure & Automation Architecture

This project follows a "Factory" model for deployment, ensuring that the environment is reproducible, secure, and cost-efficient:

1.  **The Infrastructure (Terraform):** Automatically provisions a custom VPC, subnet, and an `e2-micro` instance in the `us-central1` region.
2.  **The FinOps Guardrail:** The infrastructure is hardcoded to stick within the GCP "Always Free" limits (30GB Standard Persistent Disk, 1GB RAM) to ensure zero operating costs—a critical constraint for student and portfolio projects.
3.  **The Configuration (Ansible):** Automatically hardens the OS by creating a **2GB Swap File** (tripling effective memory via disk-backed virtual RAM) and installs the Docker engine remotely via SSH. This allows the Java JVM to run comfortably on a low-resource instance.

---

## 🚀 CI/CD Pipeline (GitHub Actions)

The pipeline is engineered for **Elite DORA metrics**, achieving a rapid build-to-registry cycle.

* **Continuous Integration:** Runs headless Selenium UI tests and Maven unit tests on every Pull Request to the `develop` branch.
* **DevSecOps:** Integrates **Trivy** to scan the application container for vulnerabilities before it is published to the registry.
* **Continuous Deployment Prep:** Automatically builds a multi-stage Docker image and pushes it to **GitHub Container Registry (GHCR)**.

---

## 📍 Project Roadmap & Progress

### ✅ Phase 1: Payload Development
* Stateless architecture using `AtomicReference` for thread-safe in-memory banking.

### ✅ Phase 2: GitFlow Implementation
* Established `main` and `develop` branching strategy to protect production code.

### ✅ Phase 3: Testing & Security
* Integrated headless Selenium browser testing and Trivy security scanning.

### ✅ Phase 4: Artifact Orchestration
* Created a multi-stage Dockerfile and automated GHCR image publishing.

### ✅ Phase 5: Infrastructure as Code (IaC)
* Provisioned GCP cloud resources using Terraform with hardcoded FinOps limits.

### ✅ Phase 6: Configuration Management
* Used Ansible for remote server hardening, swap-file creation, and Docker orchestration.

### 🕒 Phase 7: Zero Trust Deployment (In Progress)
* Deploying the app + PostgreSQL stack via Docker Compose.
* Implementing **Cloudflare Tunnels** for secure, port-less public access (Zero Trust).

### 🕒 Phase 8: Full-Stack Observability
* Implementing the **TICK Stack** (Telegraf, InfluxDB, Chronograf) for real-time performance monitoring.

---

## 💻 How to Run (Local Development)

1.  **Clone & Run:**
    ```bash
    chmod +x mvnw
    ./mvnw spring-boot:run
    ```
2.  **Access:** http://localhost:8080

> **Note:** For the full cloud deployment instructions, see the `/terraform` and `/ansible` directories.