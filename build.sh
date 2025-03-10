#!/bin/bash

# Navigate to the "ui" directory
cd ui || { echo "Failed to navigate to ui directory"; exit 1; }

# Run the build command
npm run build || { echo "Build failed"; exit 1; }

# Remove all the files from previous builds
rm -r ../src/main/resources/web/*

# Move the contents from "dist" to "src/main/resources/web"
mv dist/* ../src/main/resources/web/ || { echo "Failed to move files"; exit 1; }

echo "Build and move completed successfully. Running application..."

# Compile and start
cd .. || { echo "Failed to navigate to ui directory"; exit 1; }
mvn clean javafx:run
