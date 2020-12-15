FROM java:8

ADD order-*.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT java ${JAVA_OPTS} -Xmx256m -jar app.jar

EXPOSE 8082