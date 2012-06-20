package fr.generali.ccj.sample.gwt.client.view.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ShortcutsDesktopView extends ResizeComposite {

    interface Binder extends UiBinder<Widget, ShortcutsDesktopView> {
    }

    private static final Binder binder = GWT.create(Binder.class);

    @UiField(provided = true)
    Geoname geoname;

    @UiField
    GeoDistance geodistance;

    @Inject
    public ShortcutsDesktopView(Geoname geoname) {
        this.geoname = geoname;
        initWidget(binder.createAndBindUi(this));
    }
}
