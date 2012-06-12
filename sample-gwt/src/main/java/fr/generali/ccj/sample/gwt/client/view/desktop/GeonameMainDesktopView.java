package fr.generali.ccj.sample.gwt.client.view.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.NotStrict;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import fr.generali.ccj.sample.gwt.client.ClientFactory;
import fr.generali.ccj.sample.gwt.client.view.GeonameMainView;
import fr.generali.ccj.sample.gwt.shared.dto.GeonameDto;

public class GeonameMainDesktopView extends Composite implements GeonameMainView {
    
    interface GlobalResources extends ClientBundle {
        @NotStrict
        @Source("global.css")
        CssResource css();
    }

    interface Binder extends UiBinder<Widget, GeonameMainDesktopView> {
    }

    private static final Binder binder = GWT.create(Binder.class);

    @UiField
    TopPanel topPanel;

    @UiField
    GeonameListDesktopView geonameListView;

    @UiField
    GeonameDetailDesktopView geonameDetailDesktopView;

    @UiField
    ShortcutsDesktopView shortcutsDesktopView;

    private Presenter presenter;

    public GeonameMainDesktopView(ClientFactory clientFactory) {
        this.geonameListView = geonameListView;
        // Inject global styles.
        GWT.<GlobalResources>create(GlobalResources.class).css().ensureInjected();
        
        initWidget(binder.createAndBindUi(this));

        // Get rid of scrollbars, and clear out the window's built-in margin,
        // because we want to take advantage of the entire client area.
        Window.enableScrolling(false);
        Window.setMargin("0px");

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
