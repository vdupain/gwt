package fr.generali.ccj.sample.gwt.client.mvp;

import com.google.gwt.inject.client.AbstractGinModule;

import fr.generali.ccj.sample.gwt.client.view.GeonameDetailPresenter;
import fr.generali.ccj.sample.gwt.client.view.GeonameListPresenter;
import fr.generali.ccj.sample.gwt.client.view.GeonameMainContentPresenter;

public class ActivityModule extends AbstractGinModule {

    @Override
    protected void configure() {
        bind(GeonameMainContentPresenter.class);
        bind(GeonameListPresenter.class);
        bind(GeonameDetailPresenter.class);
    }

}
