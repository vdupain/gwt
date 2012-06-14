package fr.generali.ccj.sample.gwt.client.view.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import fr.generali.ccj.sample.gwt.client.view.GeonameMainContentView;
import fr.generali.ccj.sample.gwt.shared.dto.GeonameDto;

@Singleton
public class GeonameMainContentDesktopView extends Composite implements GeonameMainContentView {

    private static GeonameMainContentDesktopViewUiBinder uiBinder = GWT
                    .create(GeonameMainContentDesktopViewUiBinder.class);

    interface GeonameMainContentDesktopViewUiBinder extends UiBinder<Widget, GeonameMainContentDesktopView> {
    }

    @UiField
    GeonameListDesktopView geonameListView;

    @UiField
    GeonameDetailDesktopView geonameDetailDesktopView;

    private Presenter presenter;

    @Inject
    public GeonameMainContentDesktopView() {
        initWidget(uiBinder.createAndBindUi(this));

        // Listen for item selection, displaying the currently-selected item in
        // the detail area.

        geonameListView.setListener(new GeonameListDesktopView.Listener() {
            public void onItemSelected(GeonameDto item) {
                geonameDetailDesktopView.setGeonameDto(item);
            }
        });

    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

}
