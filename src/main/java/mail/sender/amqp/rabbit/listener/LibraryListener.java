package mail.sender.amqp.rabbit.listener;

import mail.sender.dto.MessageDto;
import mail.sender.service.EmailService;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class LibraryListener {

    private final EmailService emailService;

    @Autowired
    public LibraryListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "query-library-mail-send")
    public void sendMailFromLibrary(MessageDto message) throws InterruptedException {
        emailService.sendSimpleMessage(
                message.getTo(),
                message.getSubject(),
                message.getText());

    }
}