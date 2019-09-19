FROM openjdk:8u222-jdk AS build
WORKDIR /usr/local/app

COPY . .

RUN ./gradlew clean build

FROM jecklgamis/java-runtime:latest AS release
WORKDIR /usr/local/app

LABEL maintainer="Gints Polis <gints.polis@balcia.com>"
LABEL description="REST API application"

RUN groupadd -r app && useradd -r -gapp app
RUN mkdir -m 0755 -p /usr/local/app/bin
RUN mkdir -m 0755 -p /usr/local/app/config
RUN mkdir -m 0755 -p /usr/local/app/logs/

COPY --from=build /usr/local/app/build/libs/app.jar /usr/local/app/bin/app.jar

RUN chown -R app:app /usr/local/app

EXPOSE 8080
EXPOSE 8081
EXPOSE 8443

HEALTHCHECK --interval=1m --timeout=3s --start-period=15s \
  CMD wget -qO- http://localhost:8080 &> /dev/null || exit 1

CMD ["/usr/bin/java", "-jar", "/usr/local/app/bin/app.jar", "server", "config/config.yml"]

