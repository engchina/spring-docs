springboot + opentracing

start jaeger
```
docker run -d --name jaeger   -e COLLECTOR_ZIPKIN_HOST_PORT=:9411   -e COLLECTOR_OTLP_ENABLED=true   -p 6831:6831/udp   -p 6832:6832/udp   -p 5778:5778   -p 16686:16686   -p 4317:4317   -p 4318:4318   -p 14250:14250   -p 14268:14268   -p 14269:14269   -p 9411:9411   jaegertracing/all-in-one:1.39
```

start mysql
```
docker run -d --name mysql57 -p 3306:3306 -e MYSQL_ROOT_PASSWORD=oracle mysql:5.7
```

load data
```
docker exec -i mysql57 mysql -uroot -poracle < sql/database.sql
```

verify database
```
docker exec -i mysql57 mysql -uroot -poracle -e "select count(1) from chapter15.people"
```

select data
```
docker exec -i mysql57 mysql -uroot -poracle -e "select * from chapter15.people"
```
