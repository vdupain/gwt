package com.google.gwt.sample.stockwatcher.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.sample.stockwatcher.client.ClientFactory;
import com.google.gwt.sample.stockwatcher.client.event.AddStockEvent;
import com.google.gwt.sample.stockwatcher.client.event.AddStockEventHanlder;
import com.google.gwt.sample.stockwatcher.client.event.DeleteStockEvent;
import com.google.gwt.sample.stockwatcher.client.event.DeleteStockEventHanlder;
import com.google.gwt.sample.stockwatcher.client.ui.StockWatcherView;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class StockWatcherActivity extends AbstractActivity implements StockWatcherView.Presenter, AddStockEventHanlder,
                DeleteStockEventHanlder {

    public ClientFactory clientFactory;

    public StockWatcherActivity(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
        this.clientFactory.getEventBus().addHandler(AddStockEvent.TYPE, (AddStockEventHanlder ) this);
        this.clientFactory.getEventBus().addHandler(DeleteStockEvent.TYPE, (DeleteStockEventHanlder ) this);
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

    @Override
    public void onAddStockButtonClicked(AddStockEvent event) {
        this.clientFactory.getEventBus().fireEvent(event);
    }

    @Override
    public void onDeleteStockButtonClicked(DeleteStockEvent event) {
        this.clientFactory.getEventBus().fireEvent(event);
    }

    @Override
    public void onAddStock(AddStockEvent event) {
        clientFactory.getStockWatcherView().addStock();
        clientFactory.getStockWatcherView().logEvent(">>adding Stock " + event.getStockName());
    }

    @Override
    public void onDeleteStock(DeleteStockEvent event) {
        clientFactory.getStockWatcherView().removeStock(event.getStockName());
    }

}
