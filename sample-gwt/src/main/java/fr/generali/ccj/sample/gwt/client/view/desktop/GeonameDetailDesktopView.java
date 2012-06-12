package fr.generali.ccj.sample.gwt.client.view.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;

import fr.generali.ccj.sample.gwt.client.view.GeonameDetailView;
import fr.generali.ccj.sample.gwt.shared.dto.GeonameDto;

public class GeonameDetailDesktopView extends ResizeComposite implements GeonameDetailView {

    interface Binder extends UiBinder<Widget, GeonameDetailDesktopView> {
    }

    private static final Binder binder = GWT.create(Binder.class);

    @UiField
    HTML body;

    private Presenter presenter;

    public GeonameDetailDesktopView() {
        initWidget(binder.createAndBindUi(this));
    }

    public void setGeonameDto(GeonameDto geonameDto) {
        SafeHtml safeHtml =
                        new SafeHtmlBuilder().append(geonameDto.getGeonameId()).appendHtmlConstant("<br>")
                                        .appendEscaped(geonameDto.getName()).appendHtmlConstant("<br>")
                                        .appendEscaped(geonameDto.getAsciiname()).appendHtmlConstant("<br>")
                                        .appendEscaped(geonameDto.getAlternatenames()).appendHtmlConstant("<br>")
                                        .appendEscaped(geonameDto.getCountryCode()).appendHtmlConstant("<br>")
                                        .toSafeHtml();

        body.setHTML(safeHtml);
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
}
