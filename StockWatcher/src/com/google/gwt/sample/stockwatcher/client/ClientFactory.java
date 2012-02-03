package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.sample.stockwatcher.client.ui.StockWatcherView;
import com.google.web.bindery.event.shared.EventBus;

public interface ClientFactory {
    EventBus getEventBus();

    PlaceController getPlaceControler();

    StockWatcherView getStockWatcherView();
}
