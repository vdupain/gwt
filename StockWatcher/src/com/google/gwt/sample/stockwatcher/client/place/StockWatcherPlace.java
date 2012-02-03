package com.google.gwt.sample.stockwatcher.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class StockWatcherPlace extends Place {
    private String stockWatcherName;

    public StockWatcherPlace(String token) {
        this.stockWatcherName = token;
    }

    public String getStockWatcherName() {
        return stockWatcherName;
    }

    public static class Tokenizer implements PlaceTokenizer<StockWatcherPlace> {

        @Override
        public StockWatcherPlace getPlace(String token) {
            return new StockWatcherPlace(token);
        }

        @Override
        public String getToken(StockWatcherPlace place) {
            return place.getStockWatcherName();
        }

    }
}
