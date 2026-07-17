package com.contractlens.service.analyzer.infrastructure;

import com.contractlens.common.constant.ServiceConstants;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

@Configuration
public class RabbitListenerConfiguration {


    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {

        return new Jackson2JsonMessageConverter();

    }

    @Bean(ServiceConstants.RABBIT_LISTENER_CONFIGURATION)
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory,
            Jackson2JsonMessageConverter messageConverter
    ) {

        SimpleRabbitListenerContainerFactory factory =
                new SimpleRabbitListenerContainerFactory();

        factory.setConnectionFactory(connectionFactory);

        factory.setMessageConverter(messageConverter);

        factory.setAcknowledgeMode(
                AcknowledgeMode.MANUAL
        );

        factory.setDefaultRequeueRejected(false);

        factory.setConcurrentConsumers(2);

        factory.setMaxConcurrentConsumers(5);

        factory.setPrefetchCount(10);

        return factory;

    }

}
