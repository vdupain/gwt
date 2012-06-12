package fr.generali.ccj.sample.gwt.client.view.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import fr.generali.ccj.sample.gwt.client.view.GeonameDetailView;
import fr.generali.ccj.sample.gwt.shared.dto.GeonameDto;

public class GeonameDetailDesktopView extends ResizeComposite implements GeonameDetailView {

    interface Binder extends UiBinder<Widget, GeonameDetailDesktopView> {
    }

    private static final Binder binder = GWT.create(Binder.class);

    @UiField
    TextBox name;
    @UiField
    TextBox asciiname;
    @UiField
    TextArea alternatenames;
    @UiField
    TextBox countryCode;
    @UiField
    TextBox longitude;
    @UiField
    TextBox latitude;
    
    
    private Presenter presenter;

    public GeonameDetailDesktopView() {
        initWidget(binder.createAndBindUi(this));
    }

    public void setGeonameDto(GeonameDto geonameDto) {
        name.setText(geonameDto.getName());
        asciiname.setText(geonameDto.getAsciiname());
        alternatenames.setText(geonameDto.getAlternatenames());
        countryCode.setText(geonameDto.getCountryCode());
        longitude.setText(Double.toString(geonameDto.getLongitude()));
        latitude.setText(Double.toString(geonameDto.getLatitude()));
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
}
