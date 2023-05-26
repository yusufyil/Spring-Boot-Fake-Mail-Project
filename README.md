
# Spring Fake Mailbox Project

This is my first internship project that covers basic rest operations
in spring boot framework.
I Used spring data jpa, spring security and spring jwt, oauth2, redis cache, apache kafka,
postgresql, mockito and junit.


## How to run

To deploy this project  you have to be installed docker and apache kafka installed on your pc.
rest of the dependecies will handled by maven automatically.

```bash
  docker run -p 6379:6379 --name redisserver -d redis
```
you need to download zookeper for kafka.
```bash
  bin/zookeeper-server-start.sh config/zookeeper.properties
```
```bash
  bin/kafka-server-start.sh config/server.properties
```
that one is optinonal.
```bash
  bin/kafka-console-consumer.sh --topic myTopic --from-beginning --bootstrap-server localhost:9092
```
