FROM maven:3.8.4-openjdk-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src/ src/
RUN mvn clean install

FROM payara/micro:latest
COPY --from=builder /app/target/Measurements.war ${DEPLOY_DIR}
EXPOSE $PORT
CMD sh -c 'exec java -jar payara-micro.jar --deploy Measurements.war --port ${PORT}'