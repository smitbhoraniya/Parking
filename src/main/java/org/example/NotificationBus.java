package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationBus {
    private static final NotificationBus INSTANCE = new NotificationBus();
    private Map<ParkingLotEvent, List<Observer>> subscribers;
    public static NotificationBus getInstance() {
        return INSTANCE;
    }

    public NotificationBus() {
        this.subscribers = new HashMap<>();
        for (ParkingLotEvent event: ParkingLotEvent.values()) {
            this.subscribers.put(event, new ArrayList<>());
        }
    }

    public void subscribe(Observer observer, ParkingLotEvent event) {
        subscribers.get(event).add(observer);
    }

    public void publish(Object publisher, ParkingLotEvent event) {
        for (Observer observer: subscribers.get(event)) {
            observer.notify(event, publisher);
        }
    }
}
