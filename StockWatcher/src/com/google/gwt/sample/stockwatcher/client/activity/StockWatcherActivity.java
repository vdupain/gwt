package com.google.gwt.sample.stockwatcher.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.sample.stockwatcher.client.ClientFactory;
import com.google.gwt.sample.stockwatcher.client.place.StockWatcherPlace;
import com.google.gwt.sample.stockwatcher.client.ui.StockWatcherView;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class StockWatcherActivity extends AbstractActivity implements StockWatcherView.Presenter {

    private final ClientFactory clientFactory;

    private String name;

    public StockWatcherActivity(StockWatcherPlace stockWatcherPlace, ClientFactory clientFactory) {
        this.name = stockWatcherPlace.getStockWatcherName();
        this.clientFactory = clientFactory;
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        StockWatcherView stockWatcherView = this.clientFactory.getStockWatcherView();
        stockWatcherView.setPresenter(this);
        panel.setWidget(stockWatcherView.asWidget());
    }

    @Override
    public void goTo(Place place) {
        this.clientFactory.getPlaceControler().goTo(place);
    }

}
