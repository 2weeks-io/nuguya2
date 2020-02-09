#실행하기 위한 환경만 필요하기 때문에 jre, 개발까지면 jdk
FROM openjdk:8-jre

#컨테이너 안에 jar 파일은 app.jar 될꺼임
COPY target/nuguya-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]