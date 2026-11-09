package com.notifyMeButton.observer;

public class SmsNotificationObserver implements StockNotificationObserver{
    private final String userId;
    private final String phoneNumber;

    public SmsNotificationObserver(String userId, String phoneNumber) {
        this.userId = userId;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void update() {
        sendSms();
    }

    private void sendSms() {
        System.out.println("!! SMS SENT to: " + phoneNumber + " - " + "Product is back in stock! Hurry Up!!");
    }

    @Override
    public String getNotificationMethod() {
        return "SMS";
    }

    @Override
    public String getUserId() {
        return userId;
    }
}
