package com.google.gwt.sample.stockwatcher.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class DeleteStockEvent extends GwtEvent<DeleteStockEventHanlder> {

    public static final Type<DeleteStockEventHanlder> TYPE = new Type<DeleteStockEventHanlder>();

    private String stockName;

    public DeleteStockEvent(String stockName) {
        this.stockName = stockName;
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<DeleteStockEventHanlder> getAssociatedType() {
        return this.TYPE;
    }

    @Override
    protected void dispatch(DeleteStockEventHanlder handler) {
        handler.onDeleteStock(this);
    }

    public String getStockName() {
        return stockName;
    }

}
