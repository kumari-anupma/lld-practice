package com.notifyMeButton.observer;

public interface StockNotificationObserver {

    void update();

    String getNotificationMethod();

    String getUserId();
}
