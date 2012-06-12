package fr.generali.ccj.sample.gwt.client.view;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class GeonameListPlace extends Place {
    private String token;

    public GeonameListPlace(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Prefix("list") // Ancre utilisée pour identifier cette Place.
    public static class Tokenizer implements PlaceTokenizer<GeonameListPlace> {
        public String getToken(GeonameListPlace place) {
            return place.getToken();
        }

        public GeonameListPlace getPlace(String token) {
            return new GeonameListPlace(token);
        }
    }
}
