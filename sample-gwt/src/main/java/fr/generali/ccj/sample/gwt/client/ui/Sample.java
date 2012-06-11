package fr.generali.ccj.sample.gwt.client.ui;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.NotStrict;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import fr.generali.ccj.sample.gwt.client.gin.MyInjector;
import fr.generali.ccj.sample.gwt.shared.dto.GeonameDto;

public class Sample implements EntryPoint {
    private final MyInjector injector = GWT.create(MyInjector.class);

    interface Binder extends UiBinder<DockLayoutPanel, Sample> {
    }

    interface GlobalResources extends ClientBundle {
        @NotStrict
        @Source("global.css")
        CssResource css();
    }

    private static final Binder binder = GWT.create(Binder.class);

    @UiField
    TopPanel topPanel;

    @UiField
    CountryList countryList;

    @UiField
    CountryDetail countryDetail;

    @UiField
    Shortcuts shortcuts;

    /**
     * This method constructs the application user interface by instantiating
     * controls and hooking up event handler.
     */
    public void onModuleLoad() {
        // Inject global styles.
        GWT.<GlobalResources> create(GlobalResources.class).css().ensureInjected();

        // Create the UI defined in Mail.ui.xml.
        DockLayoutPanel outer = binder.createAndBindUi(this);

        // Get rid of scrollbars, and clear out the window's built-in margin,
        // because we want to take advantage of the entire client area.
        Window.enableScrolling(false);
        Window.setMargin("0px");

        // Special-case stuff to make topPanel overhang a bit.
        Element topElem = outer.getWidgetContainerElement(topPanel);
        topElem.getStyle().setZIndex(2);
        topElem.getStyle().setOverflow(Overflow.VISIBLE);

        // Listen for item selection, displaying the currently-selected item in
        // the detail area.
        countryList.setListener(new CountryList.Listener() {
            public void onItemSelected(GeonameDto item) {
                countryDetail.setItem(item);
            }
        });

        // Add the outer panel to the RootLayoutPanel, so that it will be
        // displayed.
        RootLayoutPanel root = RootLayoutPanel.get();
        root.add(outer);
    }
}
