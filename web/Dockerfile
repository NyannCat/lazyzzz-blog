FROM openjdk:11-alpine

ARG JAR_FILE=target/lazyzzz-blog.jar
ARG PORT=8080
ARG TIME_ZONE=Asia/Shanghai
ARG EDG=file:/dev/./urandom

ENV TZ=${TIME_ZONE}
ENV JAVA_OPTS="-Xms256m -Xmx256m"

COPY ${JAR_FILE} lazyzzz-blog.jar

EXPOSE ${PORT}

ENTRYPOINT java ${JAVA_OPTS} -Djava.security.edg=${EDG} -XX:+HeapDumpOnOutOfMemoryError -server -jar lazyzzz-blog.jar
