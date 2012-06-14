package fr.generali.ccj.sample.gwt.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;

import fr.generali.ccj.sample.gwt.client.view.GeonameDetailPlace;
import fr.generali.ccj.sample.gwt.client.view.GeonameDetailPresenter;

public class DetailActivityMapper implements ActivityMapper {
    Activity currentActivity;

    Provider<GeonameDetailPresenter> geonameDetailPresenterProvider;

    @Inject
    public DetailActivityMapper(Provider<GeonameDetailPresenter> geonameDetailPresenterProvider) {
        this.geonameDetailPresenterProvider = geonameDetailPresenterProvider;
    }

    public Activity getActivity(Place place) {
        if (place instanceof GeonameDetailPlace) {
            currentActivity = geonameDetailPresenterProvider.get().withPlace((GeonameDetailPlace ) place);
        }

        return currentActivity;
    }

}
