package com.sehkmet.microservices.notificationservice.service.impl;

import com.sehkmet.microservices.notificationservice.exception.runtime.MailNotSentException;
import com.sehkmet.microservices.notificationservice.service.NotificationListenerService;
import com.sehkmet.microservices.orderservice.event.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${mail.sender.email}")
    private String senderMail;
    private final JavaMailSender javaMailSender;

    @Override
    @KafkaListener(topics = "order-placed")
    public void listenNotification(OrderPlacedEvent orderPlacedEvent) {
        log.info("Got Message from order-placed event topic {}", orderPlacedEvent);

        // Building mail body
        MimeMessagePreparator messageBoiler = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(senderMail);
            messageHelper.setTo(orderPlacedEvent.getEmail().toString());
            messageHelper.setSubject(String.format("Your Order with order number %s is placed successfully",
                    orderPlacedEvent.getOrderNumber()));
            messageHelper.setText(String.format("""
                            Hi %s, %s

                            Your order with order number %s is now placed successfully.

                            Best Regard,
                            Venom Shop
                            """,
                    orderPlacedEvent.getFirstName().toString(),
                    orderPlacedEvent.getLastName().toString(),
                    orderPlacedEvent.getOrderNumber()));
        };

        // Sending the mail
        try {
            javaMailSender.send(messageBoiler);
            log.info("Order Notification email sent successfully!!!");
        }
        catch (MailException exception) {
            throw new MailNotSentException(translate("exception.mail.not.sent.exception"));
        }
    }
}
