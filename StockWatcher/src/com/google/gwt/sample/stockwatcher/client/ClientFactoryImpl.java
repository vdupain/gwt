package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.sample.stockwatcher.client.ui.StockWatcherView;
import com.google.gwt.sample.stockwatcher.client.ui.StockWatcherViewImpl;
import com.google.web.bindery.event.shared.EventBus;

public class ClientFactoryImpl implements ClientFactory {

    private final EventBus eventBus = new SimpleEventBus();

    private final PlaceController placeController = new PlaceController(eventBus);

    private final StockWatcherView stockWatcherView = new StockWatcherViewImpl();

    @Override
    public EventBus getEventBus() {
        return this.eventBus;
    }

    @Override
    public PlaceController getPlaceControler() {
        return this.placeController;
    }

    @Override
    public StockWatcherView getStockWatcherView() {
        return this.stockWatcherView;
    }

}
