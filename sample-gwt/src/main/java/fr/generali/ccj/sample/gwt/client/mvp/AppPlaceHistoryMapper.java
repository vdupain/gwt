package fr.generali.ccj.sample.gwt.client.mvp;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

import fr.generali.ccj.sample.gwt.client.view.GeonameDetailPlace;
import fr.generali.ccj.sample.gwt.client.view.GeonameListPlace;
import fr.generali.ccj.sample.gwt.client.view.GeonameMainContentPlace;

@WithTokenizers({GeonameMainContentPlace.Tokenizer.class, GeonameListPlace.Tokenizer.class, GeonameDetailPlace.Tokenizer.class })
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {

}
