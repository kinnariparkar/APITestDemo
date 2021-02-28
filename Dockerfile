FROM maven:3.6.3-jdk-11-slim AS target

RUN mkdir -p /build
WORKDIR /build

COPY src /build/src
COPY pom.xml /build/pom.xml
COPY test-data.xml /build/
COPY testng.xml .

RUN mvn install dependency:copy-dependencies

CMD mvn -o test

