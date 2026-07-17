package com.contractlens.service.analyzer.integration.consume;

import com.contractlens.common.dto.GatewayTransactionEvent;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

public interface MessageConsumer {
    void consume( GatewayTransactionEvent event,
                  Channel channel,
                  Message message);
}
