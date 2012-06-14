package fr.generali.ccj.sample.gwt.client.view;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class GeonameListPlace extends Place {
    private String pageIndex;

    public GeonameListPlace(String pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getPageIndex() {
        return pageIndex;
    }

    @Prefix("list") // Ancre utilisée pour identifier cette Place.
    public static class Tokenizer implements PlaceTokenizer<GeonameListPlace> {
        public String getToken(GeonameListPlace place) {
            return place.getPageIndex();
        }

        public GeonameListPlace getPlace(String token) {
            return new GeonameListPlace(token);
        }
    }
}
