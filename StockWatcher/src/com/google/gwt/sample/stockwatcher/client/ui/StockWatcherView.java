package com.google.gwt.sample.stockwatcher.client.ui;

import com.google.gwt.place.shared.Place;
import com.google.gwt.sample.stockwatcher.client.event.AddStockEvent;
import com.google.gwt.sample.stockwatcher.client.event.DeleteStockEvent;
import com.google.gwt.user.client.ui.IsWidget;

public interface StockWatcherView extends IsWidget {

    void setPresenter(Presenter presenter);

    public interface Presenter {
        void goTo(Place place);

        void onAddStockButtonClicked(AddStockEvent event);

        void onDeleteStockButtonClicked(DeleteStockEvent event);

    }

    void addStock();

    void removeStock(String stockName);

    void logEvent(String message);

}
