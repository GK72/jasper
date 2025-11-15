# Jasper

Minimal Java project to try out Apache Flink using Gradle.

## Prerequisites
- JDK 17+
- Gradle 8+ (or add the Gradle Wrapper to the repo by running `gradle wrapper` locally)

## Build
```
gradle clean build
```

## Run the sample Flink job
```
gradle run -PmainClass=com.example.flink.BasicJob
```
If your Gradle version doesn’t support `-PmainClass`, you can run:
```
gradle -PapplicationMainClass=com.example.flink.BasicJob run
```

The job will process a tiny in-memory stream and print uppercase words to the console.

## Project layout
- build.gradle — dependencies and Java toolchain (17)
- settings.gradle — project name
- src/main/java/com/example/flink/BasicJob.java — minimal Flink streaming job
- src/test/java/SmokeTest.java — placeholder test

## Notes
- Dependencies: Flink streaming and clients, plus SLF4J simple for local logging.
- To use the Gradle Wrapper in CI, generate it locally with `gradle wrapper` and commit the generated `gradlew`, `gradlew.bat`, and `gradle/wrapper/*` files.
