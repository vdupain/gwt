package fr.generali.ccj.sample.gwt.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;

import fr.generali.ccj.sample.gwt.client.view.GeonameDetailPlace;
import fr.generali.ccj.sample.gwt.client.view.GeonameDetailPresenter;
import fr.generali.ccj.sample.gwt.client.view.GeonameListPlace;
import fr.generali.ccj.sample.gwt.client.view.GeonameListPresenter;
import fr.generali.ccj.sample.gwt.client.view.GeonameMainContentPlace;
import fr.generali.ccj.sample.gwt.client.view.GeonameMainContentPresenter;

public class AppActivityMapper implements ActivityMapper {

    Provider<GeonameMainContentPresenter> geonameMainPresenterProvider;

    Provider<GeonameListPresenter> geonameListPresenterProvider;

    Provider<GeonameDetailPresenter> geonameDetailPresenterProvider;

    @Inject
    public AppActivityMapper(Provider<GeonameMainContentPresenter> geonameMainPresenterProvider,
                    Provider<GeonameListPresenter> geonameListPresenterProvider,
                    Provider<GeonameDetailPresenter> geonameDetailPresenterProvider) {
        this.geonameMainPresenterProvider = geonameMainPresenterProvider;
        this.geonameListPresenterProvider = geonameListPresenterProvider;
        this.geonameDetailPresenterProvider = geonameDetailPresenterProvider;
    }

    public Activity getActivity(Place place) {
        if (place instanceof GeonameMainContentPlace) {
            return geonameMainPresenterProvider.get();
        } else if (place instanceof GeonameListPlace) {
            return geonameListPresenterProvider.get();
        } else if (place instanceof GeonameDetailPlace) {
            return geonameDetailPresenterProvider.get();
        }

        return null;
    }

}
