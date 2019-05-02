# Event Messaging for Micro-services with Spring Boot and RabbitMQ

In a micro-service environment or any other distributed system you may come upon the requirement to exchange events between services. This article shows how to implement a messaging solution with RabbitMQ.

## Getting Started

In this project We Create an event consumer which is pretty straightforward. We make use of the RabbitListener provided by the AMQP starter.

Including the Spring Boot AMQP Starter 
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```
Our Service
```
@Service
public class ReceiverService {

    private Logger logger = LoggerFactory.getLogger(ReceiverService.class);

    @Autowired
    ObjectMapper objectMapper;

    @RabbitListener(queues = "${queue.name}")
    public void receievedSimpleMessage(@Payload  String s) throws IOException {
        UserDto userDto = objectMapper.readValue(s, UserDto.class);
        logger.info("----------------------------------------------");
        logger.info("|          firstName : {}                   |", userDto.getFirstName());
        logger.info("|          lastName : {}                     |", userDto.getLastName());
        logger.info("|              age : {}                       |", userDto.getAge());
        logger.info("----------------------------------------------");
    }


}
```

Adding configuration of host where rabbitMq server is running  

```
spring.rabbitmq.host=127.0.0.1
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
```

In RabbitMq server, add a new queue with the same name as in the properties

```
queue.name=queu1
```

## Prerequisites

* [RabbitMQ](https://www.rabbitmq.com/download.html) - Download & install RabbitMq server in your machine. 

## Installing

At first, you have to clone the project into your workspace

```
git clone ...
```

Run maven clean install

```
mvn clean install
```

Run the  application

## Process

If the are some  messages into a queue at the server RabbitMQ, it will be consumed by our receiver(Consumer). 
## Author

* **Zoubir REMILA** 

## Sender

Link to our Sender [rabbitmq-sender](https://gitlab.com/zoubir/rabbitmq-sender)
