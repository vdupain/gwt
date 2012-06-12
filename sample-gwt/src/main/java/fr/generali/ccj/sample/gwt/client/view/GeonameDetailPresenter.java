package fr.generali.ccj.sample.gwt.client.view;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.generali.ccj.sample.gwt.client.ClientFactory;
import fr.generali.ccj.sample.gwt.client.event.GeonameSelectedEvent;
import fr.generali.ccj.sample.gwt.client.view.GeonameDetailView.Presenter;

public class GeonameDetailPresenter extends AbstractActivity implements Presenter, GeonameSelectedEvent.Handler {
    // Used to obtain views, eventBus, placeController
    // Alternatively, could be injected via GIN
    private final ClientFactory clientFactory;

    public GeonameDetailPresenter(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
        this.clientFactory.getEventBus().addHandler(GeonameSelectedEvent.TYPE, (GeonameSelectedEvent.Handler ) this);
    }

    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        GeonameDetailView geonameDetailView = clientFactory.getGeonameDetailView();
        geonameDetailView.setPresenter(this);
        panel.setWidget(geonameDetailView.asWidget());
    }

    public void goTo(Place place) {
        this.clientFactory.getPlaceControler().goTo(place);
    }

    public void onGeonameSelected(GeonameSelectedEvent event) {
        this.clientFactory.getGeonameDetailView().setGeonameDto(event.getItem());
    }

}
