package fr.generali.ccj.sample.gwt.client.view;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

/**
 * Les URLs sont du type : http://server/index.html#ancre:token.
 * 'ancre' sert à déterminer la Place à utiliser.
 * 'token' sert à transmettre des paramètres à la Place.
 * Ce token peut être vide, mais le double-point est obligatoire.
 */
public class GeonameMainPlace extends Place {
    private String token;

    public GeonameMainPlace(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }


    @Prefix("main") // Ancre utilisée pour identifier cette Place.
    public static class Tokenizer implements PlaceTokenizer<GeonameMainPlace> {
        public String getToken(GeonameMainPlace place) {
            return place.getToken();
        }

        public GeonameMainPlace getPlace(String token) {
            return new GeonameMainPlace(token);
        }
    }
}
