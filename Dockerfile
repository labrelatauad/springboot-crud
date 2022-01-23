FROM adoptopenjdk/openjdk11:alpine

ENV JAVA_OPTS "-Xmx2048m -Xms2048m"
ARG APP_VERSION
ENV APP_NAME modulworkshop-${APP_VERSION}.jar

WORKDIR /app
COPY target/${APP_NAME} /app

EXPOSE 8080

CMD ["/bin/sh", "-c", "java $JAVA_OPTS -jar /app/${APP_NAME}"]