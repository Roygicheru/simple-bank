# 🏦 Simple Bank - DevOps & Platform Engineering Capstone

## 📌 Overview
Simple Bank is a lightweight Spring Boot application serving as the core payload for a comprehensive **Internal Developer Platform (IDP)**.

While the application logic is purposefully simple, the surrounding ecosystem demonstrates a professional-grade lifecycle: from **GitFlow** and **DORA-optimized CI/CD pipelines** to **Infrastructure as Code (IaC)**, **Zero Trust Networking**, and **Full-Stack Observability**.

---

## 🛠 Tech Stack

### Application Layer
* **Language:** Java 17 (Spring Boot 4.0.4)
* **Build Tool:** Maven
* **UI:** Thymeleaf & HTML/CSS
* **Database:** PostgreSQL 15

### Platform & Infrastructure (The "DevOps" Layer)
* **Cloud Provider:** Google Cloud Platform (GCP) - *Project: simple-bank-491910*
* **Infrastructure as Code:** Terraform (Provider v5.x)
* **Configuration Management:** Ansible
* **Containerization:** Docker & Docker Compose V2
* **Operating Systems:** Fedora 43 (Local) / Ubuntu 22.04 LTS (Cloud)
* **CI/CD:** GitHub Actions (with Selenium & Trivy)
* **Networking/Security:** Cloudflare Zero Trust Tunnels
* **Observability (TICK Stack):** Telegraf, InfluxDB, Chronograf

---

## 🏗 Infrastructure & Automation Architecture

This project follows a "Factory" model for deployment, ensuring that the environment is reproducible, secure, and cost-efficient:

1.  **The Infrastructure (Terraform):** Automatically provisions a custom VPC, subnet, and an `e2-micro` instance in the `us-central1` region.
2.  **The FinOps Guardrail:** The infrastructure is hardcoded to stick within the GCP "Always Free" limits (30GB Standard Persistent Disk, 1GB RAM) to ensure zero operating costs—a critical constraint for student and portfolio projects.
3.  **The Configuration (Ansible):** Automatically hardens the OS by creating a **2GB Swap File** (tripling effective memory via disk-backed virtual RAM) and installs the Docker engine remotely via SSH. This allows the Java JVM to run comfortably on a low-resource instance.

---

## 🚀 CI/CD Pipeline (GitHub Actions)



The pipeline is engineered for **Elite DORA metrics**, achieving a rapid build-to-registry cycle with strict separation between Integration and Deployment.

* **Continuous Integration (`develop` & `main`):** Runs headless Selenium UI tests against a localized Spring Boot test server to validate frontend integrity. Executes Maven unit tests and builds a multi-stage Docker image.
* **DevSecOps:** Integrates **Trivy** to scan the application container for vulnerabilities before it is published to **GitHub Container Registry (GHCR)**.
* **Continuous Deployment (`main` only):** Automatically connects to the GCP server via SSH, pulls the latest GHCR images, and seamlessly restarts the Docker Compose stack without manual intervention.

---

## 📍 Project Roadmap & Progress

### ✅ Phase 1: Payload Development
* Stateless architecture using `AtomicReference` for thread-safe in-memory banking (migrated to persistent PostgreSQL).

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

### ✅ Phase 7: Zero Trust Deployment

* Deployed the app + PostgreSQL database via Docker Compose.
* Implemented **Cloudflare Tunnels** (`cloudflared`) to expose `simplebank.icu` and `monitor.simplebank.icu` securely without opening any inbound firewall ports.

### ✅ Phase 8: Full-Stack Observability

* Deployed the **TICK Stack** (Telegraf, InfluxDB, Chronograf) for real-time host and container monitoring.
* Implemented dynamic `DOCKER_GID` mapping to securely grant Telegraf read-access to the Docker daemon socket following the Principle of Least Privilege.

### ✅ Phase 9: End-to-End Automated Testing & Delivery
* Configured Selenium UI tests to run headlessly in the CI pipeline (targeting `localhost` to bypass WAF) while supporting visual, live-production testing against the Zero Trust perimeter for presentation and validation.
* Finalized CD pipeline to trigger production updates exclusively on `main` branch merges.

---

## 💻 How to Run (Local Development)

1.  **Clone & Run:**
    ```bash
    chmod +x mvnw
    ./mvnw spring-boot:run
    ```
2.  **Access:** http://localhost:8080

> **Note:** For the full cloud deployment instructions, see the `/terraform` and `/ansible` directories.