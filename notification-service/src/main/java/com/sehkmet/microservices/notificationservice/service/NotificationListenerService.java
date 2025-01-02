package com.sehkmet.microservices.notificationservice.service;

import com.sehkmet.microservices.orderservice.event.OrderPlacedEvent;

public interface NotificationListenerService {

    void listenNotification(OrderPlacedEvent orderPlacedEvent);
}
