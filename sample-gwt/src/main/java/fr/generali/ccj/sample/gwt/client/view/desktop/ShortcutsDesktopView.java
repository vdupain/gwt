package fr.generali.ccj.sample.gwt.client.view.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;

@Singleton
public class ShortcutsDesktopView extends ResizeComposite {

    interface Binder extends UiBinder<Widget, ShortcutsDesktopView> {
    }

    private static final Binder binder = GWT.create(Binder.class);

    @UiField
    Countries countries;

    @UiField
    GeoDistance geodistance;

    /**
     * Constructs a new shortcutsDesktopView widget using the specified images.
     * 
     * @param images a bundle that provides the images for this widget
     */
    public ShortcutsDesktopView() {
        initWidget(binder.createAndBindUi(this));
    }
}
