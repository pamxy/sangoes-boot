FROM java:8-jre
MAINTAINER Sangoes jerrychir <jerrychir@163.com>
VOLUME /tmp
ADD ../target/sangoes-uc.jar app.jar
ADD wait-for-it.sh /wait-for-it.sh
RUN bash -c 'touch /app.jar'
RUN chmod +x /wait-for-it.sh
ENTRYPOINT ["java","-Xmx2000m","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8080