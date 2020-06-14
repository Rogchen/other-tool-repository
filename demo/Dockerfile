FROM java:8
EXPOSE 8081
VOLUME /tmp
# A streamlined jre
# ADD jre8.tar.gz /usr/java/jdk/
ADD demo-0.0.1-SNAPSHOT.jar demo-docker.jar
#等ouch /demo-docker.jar同于上面ADD demo-0.0.1-SNAPSHOT.jar demo-docker.jar
#RUN sh -c 'touch /demo-docker.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /boot-docker.jar" ]

