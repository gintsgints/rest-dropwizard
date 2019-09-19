# dropwizard.io REST project example

REST example project based on https://www.dropwizard.io/ framework.

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
docker build -t api .
```

And run image after:

```bash
docker run --rm -p 8080:8080 -p 8081:8081 -p 8443:8443 -v src/main/resources:/usr/local/app/config api
```
