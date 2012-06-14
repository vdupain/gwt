package fr.generali.ccj.sample.gwt.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;

import fr.generali.ccj.sample.gwt.client.view.GeonameListPlace;
import fr.generali.ccj.sample.gwt.client.view.GeonameListPresenter;

public class ListActivityMapper implements ActivityMapper {
    Activity currentActivity;

    Provider<GeonameListPresenter> geonameListPresenterProvider;

    @Inject
    public ListActivityMapper(Provider<GeonameListPresenter> geonameListPresenterProvider) {
        this.geonameListPresenterProvider = geonameListPresenterProvider;
    }

    public Activity getActivity(Place place) {
        if (place instanceof GeonameListPlace) {
            currentActivity = geonameListPresenterProvider.get().withPlace((GeonameListPlace ) place);
        }
        return currentActivity;
    }

}
