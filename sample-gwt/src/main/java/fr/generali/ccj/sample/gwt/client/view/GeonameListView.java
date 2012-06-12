package fr.generali.ccj.sample.gwt.client.view;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

import fr.generali.ccj.sample.gwt.client.event.GeonameSelectedEvent;

public interface GeonameListView extends IsWidget {

    void setPresenter(Presenter presenter);

    public interface Presenter {
        void goTo(Place place);

        void onGeonameSelected(GeonameSelectedEvent event);

    }

}
