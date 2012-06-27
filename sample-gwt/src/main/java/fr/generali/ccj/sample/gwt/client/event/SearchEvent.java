package fr.generali.ccj.sample.gwt.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import fr.generali.ccj.sample.gwt.shared.dto.FacetsDto;

public class SearchEvent extends GwtEvent<SearchEvent.Handler> {

    public interface Handler extends EventHandler {

        void onSearch(SearchEvent event);

    }

    public enum SearchType {
        FACETS, AUTOCOMPLETE
    }

    public static final Type<Handler> TYPE = new Type<Handler>();

    private SearchType searchType;

    private FacetsDto facets;

    private String pattern;

    public SearchEvent(SearchType searchType) {
        this.searchType = searchType;
    }

    public SearchEvent(FacetsDto facets) {
        this(SearchType.FACETS);
        this.facets = facets;
    }

    public SearchEvent(String pattern) {
        this(SearchType.AUTOCOMPLETE);
        this.pattern = pattern;
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

    public FacetsDto getFacets() {
        return facets;
    }

    public String getPattern() {
        return pattern;
    }
}
