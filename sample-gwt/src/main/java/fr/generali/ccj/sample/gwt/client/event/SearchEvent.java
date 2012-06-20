package fr.generali.ccj.sample.gwt.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class SearchEvent extends GwtEvent<SearchEvent.Handler> {

    public interface Handler extends EventHandler {

        void onSearch(SearchEvent event);

    }

    public enum SearchType {
        FACETS
    }

    public static final Type<Handler> TYPE = new Type<Handler>();

    private final SearchType searchType;

    public SearchEvent(SearchType searchType) {
        this.searchType = searchType;
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<SearchEvent.Handler> getAssociatedType() {
        return this.TYPE;
    }

    @Override
    protected void dispatch(Handler handler) {
        handler.onSearch(this);
    }

    public SearchType getSearchType() {
        return searchType;
    }
}
