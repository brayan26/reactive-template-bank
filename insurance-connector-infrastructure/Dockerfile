FROM alpine:3.17

RUN apk add --no-cache openjdk21

WORKDIR /opt
ENV USER=appuser
ENV UID=10001
ADD build/libs/insurance-connector-*.jar /opt/app.jar
# ADD build/resources/main/newrelic/newrelic.jar /opt/newrelic.jar

ENV TZ="America/Bogota"
RUN sh -c 'touch /opt/app.jar'
RUN adduser \
    --disabled-password \
    --gecos "" \
    --home "nonexistent" \
    --shell "/sbin/nologin" \
    --no-create-home \
    --uid "${UID}" \
    "${USER}"

RUN mkdir /opt/logs && chown -R $USER:$USER /opt/logs/
USER $USER:$USER

ENTRYPOINT ["java", "-Dloader.path=/opt", "-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
