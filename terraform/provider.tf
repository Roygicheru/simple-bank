terraform {
  required_providers {
    google = {
      source  = "hashicorp/google"
      version = "~> 5.0"
    }
  }
}

provider "google" {
  project = "simple-bank-491910"
  region  = "us-central1"
  zone    = "us-central1-a"
}