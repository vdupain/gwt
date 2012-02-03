package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.sample.stockwatcher.client.mvp.AppActivityMapper;
import com.google.gwt.sample.stockwatcher.client.mvp.AppPlaceHistoryMapper;
import com.google.gwt.sample.stockwatcher.client.place.StockWatcherPlace;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class StockWatcherEntryPoint implements EntryPoint {
    private Place defaultPlace = new StockWatcherPlace("");

    private SimplePanel appWidget = new SimplePanel();

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        ClientFactory clientFactory = GWT.create(ClientFactory.class);
        EventBus eventBus = clientFactory.getEventBus();
        PlaceController placeController = clientFactory.getPlaceControler();

        // Start ActivityManager for the main widget with our ActivityMapper
        ActivityMapper activityMapper = new AppActivityMapper(clientFactory);
        ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
        activityManager.setDisplay(appWidget);

        // Start PlaceHistoryHandler with our PlaceHistoryMapper
        AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController, eventBus, defaultPlace);

        RootPanel.get("stockList").add(appWidget);
        // Goes to the place represented on URL else default place
        historyHandler.handleCurrentHistory();

        // RootPanel.get("stockList").add(new StockWatcherViewImpl());
    }

}
