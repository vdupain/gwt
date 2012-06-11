package fr.generali.ccj.sample.gwt.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;

import fr.generali.ccj.sample.gwt.shared.dto.GeonameDto;

public class CountryDetail extends ResizeComposite {

    interface Binder extends UiBinder<Widget, CountryDetail> {
    }

    private static final Binder binder = GWT.create(Binder.class);

    @UiField
    HTML body;

    public CountryDetail() {
        initWidget(binder.createAndBindUi(this));
    }

    public void setItem(GeonameDto item) {
        SafeHtml safeHtml = new SafeHtmlBuilder()
            .append(item.getGeonameId()).appendHtmlConstant("<br>")            
            .appendEscaped(item.getName()).appendHtmlConstant("<br>")
            .appendEscaped(item.getAsciiname()).appendHtmlConstant("<br>")
            .appendEscaped(item.getAlternatenames()).appendHtmlConstant("<br>")
            .appendEscaped(item.getCountryCode()).appendHtmlConstant("<br>")
                        .toSafeHtml();
        
        body.setHTML(safeHtml);
    }
}
