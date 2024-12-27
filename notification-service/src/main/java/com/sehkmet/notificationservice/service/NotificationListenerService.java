package com.sehkmet.notificationservice.service;

import com.sehkmet.notificationservice.event.OrderPlacedEvent;

public interface NotificationListenerService {

    void listenNotification(OrderPlacedEvent orderPlacedEvent);
}
