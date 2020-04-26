# Dockerfile
# https://docs.docker.com/engine/reference/builder/
# https://docs.docker.com/develop/develop-images/dockerfile_best-practices/
#
# Build:
# docker build -t hello-world-microservice .
#
# Run:
# docker run -it --rm --name hello-world-microservice -p 8080:8080 hello-world-microservice
#
# Test
# curl -X GET -v "http://localhost:8080/"
# ----------------------------------------------------------------------------------------------------------------
FROM rxmicro/jre:11.0.7-alpine

ARG WORKDIR=/opt/rxmicro
COPY ./src/main/sh/start.sh ${WORKDIR}/
COPY ./target/*.jar ${WORKDIR}/
COPY ./target/lib/ ${WORKDIR}/lib/
COPY ./LICENSE ${WORKDIR}/
COPY ./NOTICE ${WORKDIR}/

WORKDIR ${WORKDIR}
EXPOSE 8080
ENV PATH $PATH:${WORKDIR}
ENV MICROSERVICE_NAME hello-world-microservice

RUN chmod +x start.sh
CMD [ "start.sh" ]