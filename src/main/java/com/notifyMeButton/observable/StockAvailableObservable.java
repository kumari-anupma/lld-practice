package com.notifyMeButton.observable;

import com.notifyMeButton.observer.StockNotificationObserver;

public interface StockAvailableObservable {

    void addStockObserver(StockNotificationObserver observer);

    void removeStockObserver(StockNotificationObserver observer);

    void notifyStockObservers();

    boolean purchase(int quantity);

    void restock(int quantity);

}
