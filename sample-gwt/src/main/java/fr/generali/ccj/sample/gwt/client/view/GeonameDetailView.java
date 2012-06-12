package fr.generali.ccj.sample.gwt.client.view;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

import fr.generali.ccj.sample.gwt.shared.dto.GeonameDto;

public interface GeonameDetailView extends IsWidget {

    void setGeonameDto(GeonameDto geonameDto);

    void setPresenter(Presenter presenter);

    public interface Presenter {
        void goTo(Place place);
    }
}
