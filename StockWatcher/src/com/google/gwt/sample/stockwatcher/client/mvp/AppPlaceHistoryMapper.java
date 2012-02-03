package com.google.gwt.sample.stockwatcher.client.mvp;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.google.gwt.sample.stockwatcher.client.place.StockWatcherPlace;

@WithTokenizers(StockWatcherPlace.Tokenizer.class)
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {

}
