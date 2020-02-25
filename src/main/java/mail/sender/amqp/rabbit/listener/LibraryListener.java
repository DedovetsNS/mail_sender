package mail.sender.amqp.rabbit.listener;

import mail.sender.dto.MessageDto;
import mail.sender.service.EmailService;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@EnableRabbit
@Component
public class LibraryListener {

    private final EmailService emailService;
    private final AmqpAdmin amqpAdmin;

    @Autowired
    public LibraryListener(EmailService emailService, AmqpAdmin amqpAdmin) {
        this.emailService = emailService;
        this.amqpAdmin = amqpAdmin;
    }

    @RabbitListener(queues = "query-library-mail-send")
    public void sendMailFromLibrary(MessageDto message)  {
        emailService.sendSimpleMessage(
                message.getTo(),
                message.getSubject(),
                message.getText());

    }

    @PostConstruct
    public void declareQueue(){
        amqpAdmin.declareQueue(new Queue("query-library-mail-send"));
    }
}