package fr.generali.ccj.sample.gwt.client.view.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import fr.generali.ccj.sample.gwt.client.event.SearchEvent;

public class Geoname extends Composite {

    private static GeonameUiBinder uiBinder = GWT.create(GeonameUiBinder.class);

    @UiField
    Button searchButton;

    @UiField
    CheckBox distance0a100;

    @UiField
    CheckBox distance100a500;

    @UiField
    CheckBox distance500;

    @UiField
    CheckBox population0a1000;

    @UiField
    CheckBox population1000a10000;

    @UiField
    CheckBox population10000a100000;

    @UiField
    CheckBox population100000a100000;

    @UiField
    CheckBox population1000000;

    private final EventBus eventBus;

    interface GeonameUiBinder extends UiBinder<Widget, Geoname> {
    }

    @Inject
    public Geoname(EventBus eventBus) {
        this.eventBus = eventBus;
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiHandler("searchButton")
    void onSearchButtonClick(ClickEvent event) {
        this.eventBus.fireEvent(new SearchEvent(SearchEvent.SearchType.FACETS));
    }
}
