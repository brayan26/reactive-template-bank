FROM eclipse-temurin:21-jdk-alpine

WORKDIR /opt
ENV USER=appuser
ENV UID=10001
ADD insurance-connector-application/build/libs/insurance-connector-application-*.jar /opt/app.jar

ENV TZ="America/Bogota"
RUN adduser \
    --disabled-password \
    --gecos "" \
    --home "nonexistent" \
    --shell "/sbin/nologin" \
    --no-create-home \
    --uid "${UID}" \
    "${USER}"

RUN mkdir /opt/logs && \
    chown -R $USER:$USER /opt/logs && \
    touch /opt/app.jar

USER $USER:$USER

ENTRYPOINT ["java", "-Dloader.path=/opt", "-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
