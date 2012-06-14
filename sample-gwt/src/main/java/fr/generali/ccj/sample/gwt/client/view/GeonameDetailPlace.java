package fr.generali.ccj.sample.gwt.client.view;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class GeonameDetailPlace extends Place {
    private String geonameId;

    public GeonameDetailPlace(String geonameId) {
        this.geonameId = geonameId;
    }

    public String getGeonameId() {
        return geonameId;
    }

    @Prefix("detail") // Ancre utilisée pour identifier cette Place.
    public static class Tokenizer implements PlaceTokenizer<GeonameDetailPlace> {
        public String getToken(GeonameDetailPlace place) {
            return place.getGeonameId();
        }

        public GeonameDetailPlace getPlace(String token) {
            return new GeonameDetailPlace(token);
        }
    }
}
