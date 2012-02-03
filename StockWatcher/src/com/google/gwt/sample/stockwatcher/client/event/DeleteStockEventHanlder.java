package com.google.gwt.sample.stockwatcher.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface DeleteStockEventHanlder extends EventHandler {

    void onDeleteStock(DeleteStockEvent event);

}
