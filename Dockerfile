FROM payara/micro:latest

COPY target/Measurements.war ${DEPLOY_DIR}

EXPOSE 8080