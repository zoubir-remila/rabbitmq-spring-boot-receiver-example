package com.receiver.receiver.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.receiver.receiver.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class ReceiverService {

    private Logger logger = LoggerFactory.getLogger(ReceiverService.class);

    @Autowired
    ObjectMapper objectMapper;

    @RabbitListener(queues = "${queue.name}")
    public void receievedSimpleMessage(@Payload  String s) throws IOException {
        UserDto userDto = objectMapper.readValue(s, UserDto.class);
        logger.info("Received message '{}'", userDto.getFirstName());
    }


}
