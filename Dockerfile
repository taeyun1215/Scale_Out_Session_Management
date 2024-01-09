FROM amazoncorretto:11

VOLUME /tmp

RUN yum install -y tzdata
ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar

#ENTRYPOINT ["java","-jar","/app.jar"]
ENTRYPOINT ["java", "-jar", "-javaagent:/home/pinpoint-agent-2.2.2/pinpoint-bootstrap-2.2.2.jar", "-Dpinpoint.agentId=adventcalendarDev", "-Dpinpoint.applicationName=adventcalendar", "-Dpinpoint.config=/home/pinpoint-agent-2.2.2/pinpoint-root.config", "-Duser.timezone=Asia/Seoul", "/app.jar"]
