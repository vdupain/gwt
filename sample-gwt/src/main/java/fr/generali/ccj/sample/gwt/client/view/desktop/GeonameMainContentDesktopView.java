package fr.generali.ccj.sample.gwt.client.view.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import fr.generali.ccj.sample.gwt.client.view.GeonameMainContentView;

@Singleton
public class GeonameMainContentDesktopView extends Composite implements GeonameMainContentView {

    private static GeonameMainContentDesktopViewUiBinder uiBinder = GWT
                    .create(GeonameMainContentDesktopViewUiBinder.class);

    interface GeonameMainContentDesktopViewUiBinder extends UiBinder<Widget, GeonameMainContentDesktopView> {
    }

    @UiField(provided = true)
    GeonameListDesktopView geonameListView;

    @UiField(provided = true)
    GeonameDetailDesktopView geonameDetailDesktopView;

    private Presenter presenter;

    @Inject
    public GeonameMainContentDesktopView(GeonameListDesktopView geonameListView,
                    GeonameDetailDesktopView geonameDetailDesktopView) {
        this.geonameListView = geonameListView;
        this.geonameDetailDesktopView = geonameDetailDesktopView;
        initWidget(uiBinder.createAndBindUi(this));
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

}
