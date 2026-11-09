package com.notifyMeButton.observable;

import com.notifyMeButton.observer.StockNotificationObserver;

import java.util.ArrayList;
import java.util.List;

public class IphoneStockObservable implements StockAvailableObservable {
    private final String productId;
    private final String productName;
    private final double price;
    private final List<StockNotificationObserver> stockObservers;
    private int stockQuantity;

    public IphoneStockObservable(String productId, String productName, double price, int stockQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.stockObservers = new ArrayList<>();
    }

    @Override
    public void addStockObserver(StockNotificationObserver observer) {
        stockObservers.add(observer);
    }

    @Override
    public void removeStockObserver(StockNotificationObserver observer) {
        stockObservers.remove(observer);
    }

    @Override
    public void notifyStockObservers() {
        if (stockQuantity > 0 && !stockObservers.isEmpty()) {
            System.out.println("Notifying " + stockObservers.size() + " subscribers... ");

            // Create a copy to avoid concurrent modification
            List<StockNotificationObserver> observersToNotify = new ArrayList<>(stockObservers);

            for (StockNotificationObserver observer : observersToNotify) {
                observer.update();
            }
        }
    }

    @Override
    public boolean purchase(int quantity) {
        if(quantity <= stockQuantity) {
            stockQuantity -= quantity;
            System.out.println("Purchase successful! Remaining stock: " + stockQuantity);
            return true;
        } else {
            System.out.println("Purchase failed! Not enough stock available.");
            return false;
        }
    }

    @Override
    public void restock(int quantity) {
        boolean wasOutOfStock = (stockQuantity == 0);
        stockQuantity += quantity;
        System.out.println("RESTOCKED: " + productName + " - Added " + quantity + " items " + " | " + "Current stock: " + stockQuantity);
        // Only notify if product was previously out of stock
        if (wasOutOfStock && stockQuantity > 0) {
            notifyStockObservers();
        }
    }
}
