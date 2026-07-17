package com.contractlens.service.infrastructure;


import com.contractlens.common.constant.ServiceConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitTemplateConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(
            ConnectionFactory connectionFactory,
            Jackson2JsonMessageConverter converter
    ) {

        RabbitTemplate rabbitTemplate =
                new RabbitTemplate(connectionFactory);

        rabbitTemplate.setMessageConverter(converter);

        return rabbitTemplate;

    }

    @Bean
    public Jackson2JsonMessageConverter rabbitMessageConverter(
            ObjectMapper objectMapper
    ) {

        return new Jackson2JsonMessageConverter(
                objectMapper
        );

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
