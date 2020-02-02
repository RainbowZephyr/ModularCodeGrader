# Parallel Batch Tester

----

A program to parallelize the testing of Java code with no external dependencies. The program can perform the following:

- Extract archives, supported formats: ZIP, XZ, GZ and RAR (version 5 not supported)
- Restructure Java code into Maven format 
- Kill tests if they exceed a certain timeout
- Scale on many cores as needed
- Clean build directories
- Log tests run per Java project into a file

## Requirements

1. Maven binary to execute the tests
2. JDK 10 or above

## How To Use

After cloning the repository generate the jar using the following command:

> mvn clean compile package

Then run the jar from the command line as follows:

> java -jar modular_code_grader-1.0-SNAPSHOT-jar-with-dependencies.jar