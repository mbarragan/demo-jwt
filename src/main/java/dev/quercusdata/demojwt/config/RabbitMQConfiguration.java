package dev.quercusdata.demojwt.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RabbitMQConfiguration {
    private static final String ROUTING_A = "routing.A";
    private static final String ROUTING_B = "routing.B";
    private static final String QUEUE_A = "queue.A";
    private static final String QUEUE_B = "queue.B";


    @Bean
    @Primary
    Queue queueA() {
        return new Queue(QUEUE_A, false);
    }

    @Bean
    Queue queueB() {
        return new Queue(QUEUE_B, false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange("exchange.direct");
    }

    @Bean
    Binding binding(Queue queueA, DirectExchange directExchange) {
        return BindingBuilder.bind(queueA)
            .to(directExchange)
            .with(ROUTING_A); //
    }

    @Bean
    Binding bindingB(Queue queueB, DirectExchange directExchange) {
        return BindingBuilder.bind(queueB)
            .to(directExchange)
            .with(ROUTING_B); //
    }

    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory factory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
