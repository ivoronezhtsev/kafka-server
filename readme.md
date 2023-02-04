## Запуск кафки
### Запускаем zookeeper
sudo /usr/local/apache-zookeeper-3.8.1-bin/bin/zkServer.sh start</br>
### Запускаем kafka
sudo /usr/local/kafka_2.12-3.3.2/bin/kafka-server-start.sh /usr/local/kafka_2.12-3.3.2/config/server.properties


### Отправка сообщений в кафку 
kafkaStarshipTemplate.send("server.starship", dto); 

### Чтение сообщений из кафки
@KafkaListener(id = "Starship", topics = {"server.starship"}, containerFactory = "singleFactory")
public void consume(StarshipDto dto) {
log.info("=> consumed {}", writeValueAsString(dto));
}