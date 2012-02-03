package com.google.gwt.sample.stockwatcher.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddStockEvent extends GwtEvent<AddStockEventHanlder> {

    public static final Type<AddStockEventHanlder> TYPE = new Type<AddStockEventHanlder>();

    private String stockName;

    public AddStockEvent(String stockName) {
        this.stockName = stockName;
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<AddStockEventHanlder> getAssociatedType() {
        return this.TYPE;
    }

    @Override
    protected void dispatch(AddStockEventHanlder handler) {
        handler.onAddStock(this);
    }

    public String getStockName() {
        return stockName;
    }

}
