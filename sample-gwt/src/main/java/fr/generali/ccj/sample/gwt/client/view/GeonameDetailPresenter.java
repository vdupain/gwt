package fr.generali.ccj.sample.gwt.client.view;

import java.util.ArrayList;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

import fr.generali.ccj.sample.gwt.client.event.GeonameSelectedEvent;
import fr.generali.ccj.sample.gwt.client.view.GeonameDetailView.Presenter;
import fr.generali.ccj.sample.gwt.shared.dto.GeonameDto;

public class GeonameDetailPresenter extends AbstractActivity implements Presenter, GeonameSelectedEvent.Handler {

    private final GeonameDetailView view;

    private final PlaceController placeController;

    private final GeonameListView geonameListView;

    @Inject
    public GeonameDetailPresenter(GeonameDetailView geonameDetailView, GeonameListView geonameListView,
                    PlaceController placeController) {
        this.view = geonameDetailView;
        this.geonameListView = geonameListView;
        this.placeController = placeController;
    }

    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        view.setPresenter(this);
        panel.setWidget(view.asWidget());
    }

    public GeonameDetailPresenter withPlace(GeonameDetailPlace place) {
        ArrayList<GeonameDto> list = geonameListView.getCurrentList();
        for (GeonameDto geonameDto : list) {
            if (Integer.toString(geonameDto.getGeonameId()).equals(place.getGeonameId())) {
                view.setGeonameDto(geonameDto);
                break;
            }
        }
        return this;
    }

    public void goTo(Place place) {
        placeController.goTo(place);
    }

    public void onGeonameSelected(GeonameSelectedEvent event) {
    }

}
