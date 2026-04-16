# 1. A custom Virtual Private Cloud (VPC) Network
resource "google_compute_network" "simple_bank_vpc" {
  name                    = "simple-bank-vpc"
  auto_create_subnetworks = false
}

# 2. A Subnet within the Free Tier Region
resource "google_compute_subnetwork" "simple_bank_subnet" {
  name          = "simple-bank-subnet"
  ip_cidr_range = "10.0.1.0/24"
  region        = "us-central1"
  network       = google_compute_network.simple_bank_vpc.id
}

# 3. The Zero-Trust Firewall (Only Port 22 for Ansible)
resource "google_compute_firewall" "allow_ssh" {
  name    = "allow-ssh"
  network = google_compute_network.simple_bank_vpc.name

  allow {
    protocol = "tcp"
    ports    = ["22"]
  }

  source_ranges = ["0.0.0.0/0"]
}

# 4. The FinOps "Always Free" Server
resource "google_compute_instance" "app_server" {
  name         = "simple-bank-server"
  machine_type = "e2-micro" # STRICT FINOPS: Ensures Always Free tier
  zone         = "us-central1-a"

  metadata = {
    ssh-keys = "ubuntu:${file("~/.ssh/simple_bank_key.pub")}"
  }

  boot_disk {
    initialize_params {
      image = "ubuntu-os-cloud/ubuntu-2204-lts" # Standard Ubuntu
      type  = "pd-standard"                     # STRICT FINOPS: Free magnetic disk
      size  = 30                                # STRICT FINOPS: Max free size
    }
  }

  network_interface {
    network    = google_compute_network.simple_bank_vpc.name
    subnetwork = google_compute_subnetwork.simple_bank_subnet.name

    access_config {
      # This block requests an ephemeral public IP so I can SSH into it
    }
  }
}

# 5. Output the public IP so I don't have to dig through the GCP console
output "server_public_ip" {
  value       = google_compute_instance.app_server.network_interface[0].access_config[0].nat_ip
  description = "The public IP address of the Simple Bank server"
}