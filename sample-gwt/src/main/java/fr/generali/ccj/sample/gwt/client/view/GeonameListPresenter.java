package fr.generali.ccj.sample.gwt.client.view;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

import fr.generali.ccj.sample.gwt.client.view.GeonameListView.Presenter;

public class GeonameListPresenter extends AbstractActivity implements Presenter {


    private final GeonameListView view;
    private final PlaceController placeController;

    @Inject
    public GeonameListPresenter(GeonameListView geonameListView, PlaceController placeController) {
        this.view = geonameListView;
        this.placeController = placeController;
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        view.setPresenter(this);
        panel.setWidget(view.asWidget());
    }

    public GeonameListPresenter withPlace(GeonameListPlace place) {
        view.setPageIndex(Integer.valueOf(place.getPageIndex()));
        return this;
    }

    
    /**
     * Navigate to a new Place in the browser
     */
    public void goTo(Place place) {
        placeController.goTo(place);
    }

}
