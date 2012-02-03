package com.google.gwt.sample.stockwatcher.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface AddStockEventHanlder extends EventHandler {

    void onAddStock(AddStockEvent event);

}
