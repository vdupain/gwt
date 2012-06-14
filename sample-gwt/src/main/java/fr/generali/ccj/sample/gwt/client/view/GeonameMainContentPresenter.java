package fr.generali.ccj.sample.gwt.client.view;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

import fr.generali.ccj.sample.gwt.client.view.GeonameMainContentView.Presenter;

public class GeonameMainContentPresenter extends AbstractActivity implements Presenter {

    private final GeonameMainContentView view;

    @Inject
    public GeonameMainContentPresenter(GeonameMainContentView geonameMainView) {
        this.view = geonameMainView;
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        view.setPresenter(this);
        panel.setWidget(view.asWidget());
    }

    /**
     * Navigate to a new Place in the browser
     */
    public void goTo(Place place) {
        // clientFactory.getPlaceControler().goTo(place);
    }

}
