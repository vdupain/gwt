package fr.generali.ccj.sample.gwt.client.view;

import com.google.gwt.inject.client.AbstractGinModule;

import fr.generali.ccj.sample.gwt.client.view.desktop.GeonameDetailDesktopView;
import fr.generali.ccj.sample.gwt.client.view.desktop.GeonameListDesktopView;
import fr.generali.ccj.sample.gwt.client.view.desktop.GeonameMainContentDesktopView;
import fr.generali.ccj.sample.gwt.client.view.desktop.GeonameMainDesktopView;
import fr.generali.ccj.sample.gwt.client.view.desktop.ShortcutsDesktopView;

public class ViewModule extends AbstractGinModule {

    @Override
    protected void configure() {
        bind(GeonameMainDesktopView.class);
        bind(ShortcutsDesktopView.class);
        bind(GeonameMainContentView.class).to(GeonameMainContentDesktopView.class);
        bind(GeonameListView.class).to(GeonameListDesktopView.class);
        bind(GeonameDetailView.class).to(GeonameDetailDesktopView.class);
    }

}
