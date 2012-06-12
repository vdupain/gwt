package fr.generali.ccj.sample.gwt.client.view;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.generali.ccj.sample.gwt.client.ClientFactory;
import fr.generali.ccj.sample.gwt.client.event.GeonameSelectedEvent;
import fr.generali.ccj.sample.gwt.client.view.GeonameListView.Presenter;

public class GeonameListPresenter extends AbstractActivity implements Presenter {

    // Used to obtain views, eventBus, placeController
    // Alternatively, could be injected via GIN
    private final ClientFactory clientFactory;

    public GeonameListPresenter(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        GeonameListView geonameListView = clientFactory.getGeonameListView();
        geonameListView.setPresenter(this);
        panel.setWidget(geonameListView.asWidget());
    }

    /**
     * Navigate to a new Place in the browser
     */
    public void goTo(Place place) {
        clientFactory.getPlaceControler().goTo(place);
    }

    public void onGeonameSelected(GeonameSelectedEvent event) {
        this.clientFactory.getEventBus().fireEvent(event);
    }

}
