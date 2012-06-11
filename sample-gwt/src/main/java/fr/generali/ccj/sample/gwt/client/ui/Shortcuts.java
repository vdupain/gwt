package fr.generali.ccj.sample.gwt.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.StackLayoutPanel;

public class Shortcuts extends ResizeComposite {

    interface Binder extends UiBinder<StackLayoutPanel, Shortcuts> {
    }

    private static final Binder binder = GWT.create(Binder.class);

    @UiField
    Countries countries;

    @UiField
    GeoDistance geodistance;

    /**
     * Constructs a new shortcuts widget using the specified images.
     * 
     * @param images a bundle that provides the images for this widget
     */
    public Shortcuts() {
        initWidget(binder.createAndBindUi(this));
    }
}
