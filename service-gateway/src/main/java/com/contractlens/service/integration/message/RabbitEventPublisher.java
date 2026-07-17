package com.contractlens.service.integration.message;

import com.contractlens.common.dto.GatewayTransactionEvent;
import org.springframework.amqp.AmqpException;

public interface RabbitEventPublisher {
    void publish(GatewayTransactionEvent event);
    void recover(AmqpException ex, GatewayTransactionEvent event);
    void retry();
}
