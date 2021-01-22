## Running the application in dev mode
```shell script
./mvnw compile quarkus:dev
```

## Packaging and running the application
```shell script
./mvnw package
```
It produces the `server-1.0.0-SNAPSHOT-runner.jar` file in the `/target` directory.

## Creating a native executable (with Graalvm)
```shell script
./mvnw package -Pnative
```
You can then execute your native executable with: `./target/server-1.0.0-SNAPSHOT-runner`

