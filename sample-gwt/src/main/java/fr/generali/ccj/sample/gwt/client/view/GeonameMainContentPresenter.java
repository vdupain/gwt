package fr.generali.ccj.sample.gwt.client.view;

import java.util.ArrayList;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

import fr.generali.ccj.sample.gwt.client.view.GeonameMainContentView.Presenter;
import fr.generali.ccj.sample.gwt.shared.dto.GeonameDto;

public class GeonameMainContentPresenter extends AbstractActivity implements Presenter {


    private final PlaceController placeController;

    private final GeonameMainContentView view;

    private final GeonameListView geonameListView;

    private final GeonameDetailView geonameDetailView;

    @Inject
    public GeonameMainContentPresenter(GeonameMainContentView geonameMainView, GeonameListView geonameListView,
                    GeonameDetailView geonameDetailView, PlaceController placeController) {
        this.view = geonameMainView;
        this.geonameListView = geonameListView;
        this.geonameDetailView = geonameDetailView;
        this.placeController = placeController;
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
        placeController.goTo(place);
    }

    public GeonameMainContentPresenter withPlace(GeonameMainContentPlace place) {
        if ("".equals(place.getToken()))
            return this;
        String sPageIndex = place.getToken().substring(0, place.getToken().indexOf("_"));
        String sGeonameId = place.getToken().substring(place.getToken().indexOf("_") +1, place.getToken().length());
        
//        geonameListView.setPageIndex(Integer.valueOf(sPageIndex));
//        geonameListView.update();
        
        ArrayList<GeonameDto> list = geonameListView.getCurrentList();
        for (GeonameDto geonameDto : list) {
            if (Integer.toString(geonameDto.getGeonameId()).equals(sGeonameId)) {
                geonameDetailView.setGeonameDto(geonameDto);
                break;
            }
        }
        return this;
    }

}
