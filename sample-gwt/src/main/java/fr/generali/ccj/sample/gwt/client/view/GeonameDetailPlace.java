package fr.generali.ccj.sample.gwt.client.view;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class GeonameDetailPlace extends Place {
    private String token;

    public GeonameDetailPlace(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public static class Tokenizer implements PlaceTokenizer<GeonameDetailPlace> {
        public String getToken(GeonameDetailPlace place) {
            return place.getToken();
        }

        public GeonameDetailPlace getPlace(String token) {
            return new GeonameDetailPlace(token);
        }
    }
}
