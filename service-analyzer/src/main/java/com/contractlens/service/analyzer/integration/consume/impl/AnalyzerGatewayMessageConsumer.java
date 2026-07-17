package com.contractlens.service.analyzer.integration.consume.impl;

import com.contractlens.common.constant.RabbitConstants;
import com.contractlens.common.constant.ServiceConstants;
import com.contractlens.common.dto.GatewayTransactionEvent;
import com.contractlens.service.analyzer.integration.consume.MessageConsumer;
import com.contractlens.service.analyzer.module.enhance.service.AnalyzeMainService;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component("analyzerGatewayMessageConsumer")
@RequiredArgsConstructor
public class AnalyzerGatewayMessageConsumer implements MessageConsumer {

    private final AnalyzeMainService  analyzeMainService;

    @SneakyThrows
    @Override
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = RabbitConstants.ANALYZER_QUEUE,  durable = "true"),
                    exchange = @Exchange(name = RabbitConstants.ANALYZER_EXCHANGE),
                    key = RabbitConstants.ANALYZER_ROUTING
            ),
            ackMode = RabbitConstants.ANALYZER_ACK_MODE,
            containerFactory =  ServiceConstants.RABBIT_LISTENER_CONFIGURATION
    )
    public void consume(GatewayTransactionEvent event, Channel channel, Message message) {
        try {

            analyzeMainService.analyze(event);

            channel.basicAck(
                    message.getMessageProperties().getDeliveryTag(),
                    false
            );

        } catch (Exception ex) {
            log.info("",ex);

            //Save Ke Redis lagi

            channel.basicAck(
                    message.getMessageProperties().getDeliveryTag(),
                    false
            );

        }
    }
}
