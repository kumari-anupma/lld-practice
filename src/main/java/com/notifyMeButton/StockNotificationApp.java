package com.notifyMeButton;

import com.notifyMeButton.observable.IphoneStockObservable;
import com.notifyMeButton.observable.StockAvailableObservable;
import com.notifyMeButton.observer.EmailNotificationObserver;
import com.notifyMeButton.observer.SmsNotificationObserver;
import com.notifyMeButton.observer.StockNotificationObserver;

public class StockNotificationApp {
    public static void main(String[] args) {
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("###### E-commerce Store - Stock Availability Notification Feature Demo ######");

        // Create an iPhone product - stock available = 10 units
        StockAvailableObservable iphoneProduct = new IphoneStockObservable("ip15", "iphone 15", 1250, 10);

        // Create observers
        StockNotificationObserver Anu_SMS = new SmsNotificationObserver("Anu123", "AnuDeviceP1");
        StockNotificationObserver Shuvo_SMS = new SmsNotificationObserver("Shuvo678", "ShuvoDeviceP2");
        StockNotificationObserver Chitra_EMAIL = new EmailNotificationObserver("Chitra783", "chitra783@gmail.com");
        StockNotificationObserver Shweta_EMAIL = new EmailNotificationObserver("Shweta993", "shweta993@gmail.com");

        iphoneProduct.purchase(10);

        // Stock unavailability leads to users subscribing for notifications
        boolean success = iphoneProduct.purchase(1); // Failed purchase
        if (!success) {
            // Register observers
            iphoneProduct.addStockObserver(Anu_SMS);
            iphoneProduct.addStockObserver(Shuvo_SMS);
            iphoneProduct.addStockObserver(Chitra_EMAIL);
            iphoneProduct.addStockObserver(Shweta_EMAIL);
        }

        // Restock 20 units of iPhone 15
        iphoneProduct.restock(20); // All 4 observers are notified

        // Users purchase upon receiving notifications
        iphoneProduct.purchase(1); // Anu purchases 1 unit
        iphoneProduct.purchase(1); // Shuvo purchases 1 unit

        // Anu & Shuvo unsubscribe from notifications
        iphoneProduct.removeStockObserver(Anu_SMS);
        iphoneProduct.removeStockObserver(Shuvo_SMS);

        // NYE Sale - All 18 units sold
        iphoneProduct.purchase(18);
        iphoneProduct.restock(5); // Only Chitra & Shweta are notified

        iphoneProduct.purchase(1); // Chitra purchases 1 unit
        iphoneProduct.purchase(1); // Shweta purchases 1 unit

        // Chitra & Shweta unsubscribe from notifications
        iphoneProduct.removeStockObserver(Chitra_EMAIL);
        iphoneProduct.removeStockObserver(Shweta_EMAIL);
    }
}
