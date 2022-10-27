./mvnw clean package -Dmaven.test.skip=true
docker build -t atjapan2015/mrds:0.0.1 .
docker push atjapan2015/mrds:0.0.1
