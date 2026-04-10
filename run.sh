#!/bin/bash

echo "==================================="
echo "  College Management System"
echo "==================================="

# Build the project
echo "[1/3] Compiling..."
mvn compile -q
if [ $? -ne 0 ]; then
    echo "ERROR: Compilation failed. Run 'mvn compile' for details."
    exit 1
fi

echo "[2/3] Packaging..."
mvn package -q -DskipTests
if [ $? -ne 0 ]; then
    echo "ERROR: Packaging failed."
    exit 1
fi

echo "[3/3] Generating classpath..."
mvn dependency:build-classpath -q -Dmdep.outputFile=target/classpath.txt

# Run the app
JAR="target/CollegeManagementSystem-1.0-SNAPSHOT.jar"
CP=$(cat target/classpath.txt)

echo ""
echo "Starting app... (Demo login: student1 / password)"
echo ""

java -cp "$JAR;$CP" com.college.Main
