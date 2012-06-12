package fr.generali.ccj.sample.gwt.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import fr.generali.ccj.sample.gwt.client.ClientFactory;
import fr.generali.ccj.sample.gwt.client.view.GeonameDetailPlace;
import fr.generali.ccj.sample.gwt.client.view.GeonameDetailPresenter;
import fr.generali.ccj.sample.gwt.client.view.GeonameListPlace;
import fr.generali.ccj.sample.gwt.client.view.GeonameListPresenter;
import fr.generali.ccj.sample.gwt.client.view.GeonameMainPlace;
import fr.generali.ccj.sample.gwt.client.view.GeonameMainPresenter;

public class AppActivityMapper implements ActivityMapper {

    private ClientFactory clientFactory;

    public AppActivityMapper(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    public Activity getActivity(Place place) {
        if (place instanceof GeonameMainPlace)
            return new GeonameMainPresenter(clientFactory);
        else if (place instanceof GeonameListPlace)
            return new GeonameListPresenter(clientFactory);
        else if (place instanceof GeonameDetailPlace)
            return new GeonameDetailPresenter(clientFactory);
        return null;
    }

}
