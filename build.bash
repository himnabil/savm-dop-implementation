#!/bin/bash

# Ensure the script stops on errors
set -e

# Build the project using Maven
mvn clean package

# Create the native image
native-image -jar target/savm-dop-implementation-1.0-SNAPSHOT.jar run

# Print success message
echo "Native image successfully built: ./run"
