package dev.quercusdata.demojwt.infraestructure.adapter.in.controller.rabbit;

import dev.quercusdata.demojwt.domain.model.rabbit.Message;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static dev.quercusdata.demojwt.domain.utils.Constants.ROUTING_A;

@RestController
@RequestMapping("/api/v1/rabbit/producer")
@Log4j2
@AllArgsConstructor
public class ProducerController {

    private RabbitTemplate rabbitTemplate;
    private DirectExchange exchange;

    @PostMapping("/send-message")
    public String send(@RequestBody Message message){
        log.info("Sending the message {} to routing.A throw the directExchange {}", message, exchange.getName());
        rabbitTemplate.convertAndSend(exchange.getName(), ROUTING_A, message);
        return "Messsage sent successfully";
    }
}
