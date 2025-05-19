FROM tomcat:9.0-jdk17

COPY target/registration-webapp-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/registration.war

EXPOSE 8080
