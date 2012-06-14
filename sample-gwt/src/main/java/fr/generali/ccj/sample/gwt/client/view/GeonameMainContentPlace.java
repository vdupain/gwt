package fr.generali.ccj.sample.gwt.client.view;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

/**
 * Les URLs sont du type : http://server/index.html#ancre:token. 'ancre' sert à
 * déterminer la Place à utiliser. 'token' sert à transmettre des paramètres à
 * la Place. Ce token peut être vide, mais le double-point est obligatoire.
 */
public class GeonameMainContentPlace extends Place {
    private String token;

    public GeonameMainContentPlace(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Prefix("main")
    // Ancre utilisée pour identifier cette Place.
    public static class Tokenizer implements PlaceTokenizer<GeonameMainContentPlace> {
        public String getToken(GeonameMainContentPlace place) {
            return place.getToken();
        }

        public GeonameMainContentPlace getPlace(String token) {
            return new GeonameMainContentPlace(token);
        }
    }
}
