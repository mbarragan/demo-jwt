package dev.quercusdata.demojwt.infraestructure.adapter.in.controller.rabbit;

import dev.quercusdata.demojwt.domain.model.rabbit.Message;
import dev.quercusdata.demojwt.domain.utils.Constants;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class ConsumerController {

    @RabbitListener(queues = Constants.QUEUE_A)
    private void receive(Message message){
        log.info("receiving the message {} from QUEUE A", message);
    }

    @RabbitListener(queues = Constants.QUEUE_B)
    private void receiveFromB(Message message){
        log.info("receiving the message {} from QUEUE B", message);
    }

}
