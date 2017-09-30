FROM java:8

COPY monitoringserver-0.0.1-SNAPSHOT.jar /monitoringserver.jar

CMD java -jar /monitoringserver.jar