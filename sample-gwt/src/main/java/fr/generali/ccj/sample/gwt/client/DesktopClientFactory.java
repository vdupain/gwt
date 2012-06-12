package fr.generali.ccj.sample.gwt.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;

import fr.generali.ccj.sample.gwt.client.view.GeonameDetailView;
import fr.generali.ccj.sample.gwt.client.view.GeonameListView;
import fr.generali.ccj.sample.gwt.client.view.GeonameMainView;
import fr.generali.ccj.sample.gwt.client.view.desktop.GeonameDetailDesktopView;
import fr.generali.ccj.sample.gwt.client.view.desktop.GeonameListDesktopView;
import fr.generali.ccj.sample.gwt.client.view.desktop.GeonameMainDesktopView;

public class DesktopClientFactory implements ClientFactory {

    private final EventBus eventBus = new SimpleEventBus();

    private final PlaceController placeController = new PlaceController(eventBus);

    private final GeonameListView geonameListView = new GeonameListDesktopView();

    private final GeonameDetailView geonameDetailView = new GeonameDetailDesktopView();

    private final GeonameMainView geonameMainView = new GeonameMainDesktopView();

    public EventBus getEventBus() {
        return this.eventBus;
    }

    public PlaceController getPlaceControler() {
        return this.placeController;
    }

    public GeonameDetailView getGeonameDetailView() {
        return this.geonameDetailView;
    }

    public GeonameListView getGeonameListView() {
        return this.geonameListView;
    }

    public GeonameMainView getGeonameMainView() {
        return this.geonameMainView;
    }

}
