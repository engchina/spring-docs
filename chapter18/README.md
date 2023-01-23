springboot + zipkin

start zipkin
```
docker run -d --name zipkin -p 9411:9411 openzipkin/zipkin:2.23.19
```

start rabbitmq
```
docker run -d --name rabbitmq -p 5672:5672 -e RABBITMQ_DEFAULT_USER=oracle -e RABBITMQ_DEFAULT_PASS=oracle rabbitmq:3
docker run -d --name rabbitmqmgmt -p 15672:15672 -e RABBITMQ_DEFAULT_USER=oracle -e RABBITMQ_DEFAULT_PASS=oracle rabbitmq:3-management
```

start elk
```
vi /etc/sysctl.conf
---
vm.max_map_count=262144
---
sysctl -p

docker run -d --name elk -p 5601:5601 -p 9200:9200 -p 5044:5044 -e ES_MIN_MEM=128m -e ES_MAX_MEM=1024m sebp/elk:8.3.3

cat <<EOF > 02-beats-input.conf
input {
  tcp {
    port => 5044
    codec => json_lines
  }
}
output {
  elasticsearch {
    hosts => ["localhost:9200"]
    index => "broadview"
  }
}
EOF

docker cp 02-beats-input.conf elk:/etc/logstash/conf.d/02-beats-input.conf
docker stop elk && docker start elk
```

start jaeger
```
docker run -d --name jaeger -e COLLECTOR_ZIPKIN_HOST_PORT=:9411 -e COLLECTOR_OTLP_ENABLED=true   -p 6831:6831/udp   -p 6832:6832/udp   -p 5778:5778   -p 16686:16686   -p 4317:4317   -p 4318:4318   -p 14250:14250   -p 14268:14268   -p 14269:14269   -p 9411:9411   jaegertracing/all-in-one:1.39
```

start redis
```
docker run -d --name redis -p 6379:6379 redis:7.0.5
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
