docker stop monitoringserver
docker rm monitoringserver
docker run -d -e "securityKey=ERRER4R4RERTRT5T5RGDDGHJUUI889IKJHJH" -p 8080:8080 --name monitoringserver monitoringserver