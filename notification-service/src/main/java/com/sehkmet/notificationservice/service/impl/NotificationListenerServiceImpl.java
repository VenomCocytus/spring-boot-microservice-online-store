package com.sehkmet.notificationservice.service.impl;

import com.sehkmet.notificationservice.event.OrderPlacedEvent;
import com.sehkmet.notificationservice.exception.runtime.MailNotSentException;
import com.sehkmet.notificationservice.service.NotificationListenerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import static com.sehkmet.utils.utils.Utils.translate;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationListenerServiceImpl implements NotificationListenerService {

    private final JavaMailSender javaMailSender;

    @Override
    @KafkaListener(topics = "order-placed")
    public void listenNotification(OrderPlacedEvent orderPlacedEvent) {
        log.info("Got Message from order-placed event topic {}", orderPlacedEvent);

        // Building mail body
        MimeMessagePreparator messageBoiler = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("venomshop@email.com");
            messageHelper.setTo(orderPlacedEvent.getEmail().toString());
            messageHelper.setSubject(String.format("Your Order with OrderNumber %s is placed successfully",
                    orderPlacedEvent.getOrderNumber()));
            messageHelper.setText(String.format("""
                            Hi %s,%s

                            Your order with order number %s is now placed successfully.

                            Best Regards
                            Venom Shop
                            """,
                    orderPlacedEvent.getFirstName().toString(),
                    orderPlacedEvent.getLastName().toString(),
                    orderPlacedEvent.getOrderNumber()));
        };

        // Try to send the mail
        try {
            javaMailSender.send(messageBoiler);
            log.info("Order Notification email sent successfully!!!");
        }
        catch (MailException exception) {
            throw new MailNotSentException(translate("exception.mail.not.sent.exception"));
        }
    }
}
