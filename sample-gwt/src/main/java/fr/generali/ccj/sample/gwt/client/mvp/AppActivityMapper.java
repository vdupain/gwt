package fr.generali.ccj.sample.gwt.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;

import fr.generali.ccj.sample.gwt.client.view.GeonameMainContentPlace;
import fr.generali.ccj.sample.gwt.client.view.GeonameMainContentPresenter;

public class AppActivityMapper implements ActivityMapper {
    Activity currentActivity;

    Provider<GeonameMainContentPresenter> geonameMainPresenterProvider;

    @Inject
    public AppActivityMapper(Provider<GeonameMainContentPresenter> geonameMainPresenterProvider) {
        this.geonameMainPresenterProvider = geonameMainPresenterProvider;
    }

    public Activity getActivity(Place place) {
        if (place instanceof GeonameMainContentPlace) {
            currentActivity = geonameMainPresenterProvider.get().withPlace((GeonameMainContentPlace ) place);
        }
        return currentActivity;
    }

}
