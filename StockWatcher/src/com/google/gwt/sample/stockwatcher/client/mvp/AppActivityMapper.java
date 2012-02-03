package com.google.gwt.sample.stockwatcher.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.gwt.sample.stockwatcher.client.ClientFactory;
import com.google.gwt.sample.stockwatcher.client.activity.StockWatcherActivity;
import com.google.gwt.sample.stockwatcher.client.place.StockWatcherPlace;

public class AppActivityMapper implements ActivityMapper {
    
    private ClientFactory clientFactory;

    public AppActivityMapper(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    @Override
    public Activity getActivity(Place place) {
        if (place instanceof StockWatcherPlace)
               return new StockWatcherActivity((StockWatcherPlace) place, clientFactory);
        return null;
    }

}
