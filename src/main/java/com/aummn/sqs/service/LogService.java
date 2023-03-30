package com.aummn.sqs.service;

import com.aummn.sqs.model.OrderInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.messaging.config.annotation.NotificationMessage;
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogService {
    @Autowired
    private ObjectMapper mapper;
    @Value("${log.queue.name}")
    private String logQueueName;
    @Autowired
    private OrderInfoService orderInfoService;

    @SqsListener(value = "${log.queue.name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void processMessage(@NotificationMessage String message) throws JsonProcessingException {
        log.info("Message from SQS {} - {}", logQueueName, message);
        OrderInfo orderInfo = deserializeOrderInfo(message);
        orderInfoService.createOrderInfo(orderInfo);
        log.info("stored order into DynamoDB - {}", orderInfo.getOrderNo());
    }

    public OrderInfo deserializeOrderInfo(String jsonString) throws JsonProcessingException {
        return mapper.readValue(jsonString, OrderInfo.class);
    }
}
