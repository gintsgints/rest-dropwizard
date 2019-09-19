# dropwizard.io REST project example

REST example project based on https://www.dropwizard.io/ framework.
Project is build using Open JDK version 1.8
(JDK 11 will be supported with next dropwizard 2.x)

## Start project

To start project execute gradle command:

```bash
./gradlew run
```

After that you can go check address:

http://localhost:8080/add?a=5&b=10

### Build and run project as docker image

Project contains docker instructions to build:

```bash
docker build -t registry.balcia.com/balcia_dev/java/rest-dropwizard .
```

And run image after:

```bash
docker-compose up -d
```
