package fr.generali.ccj.sample.gwt.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;

public class CountryDetail extends ResizeComposite {

    interface Binder extends UiBinder<Widget, CountryDetail> {
    }

    private static final Binder binder = GWT.create(Binder.class);

    @UiField
    HTML body;

    public CountryDetail() {
        initWidget(binder.createAndBindUi(this));
    }

    public void setItem(Geoname item) {
        body.setHTML(item.getName());
    }
}
