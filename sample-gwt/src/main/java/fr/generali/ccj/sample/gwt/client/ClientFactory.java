package fr.generali.ccj.sample.gwt.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;

import fr.generali.ccj.sample.gwt.client.view.GeonameDetailView;
import fr.generali.ccj.sample.gwt.client.view.GeonameListView;
import fr.generali.ccj.sample.gwt.client.view.GeonameMainView;

public interface ClientFactory {
    EventBus getEventBus();

    PlaceController getPlaceControler();

    GeonameDetailView getGeonameDetailView();

    GeonameListView getGeonameListView();

    GeonameMainView getGeonameMainView();

}
