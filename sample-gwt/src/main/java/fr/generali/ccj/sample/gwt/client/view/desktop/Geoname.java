package fr.generali.ccj.sample.gwt.client.view.desktop;

import java.util.ArrayList;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import fr.generali.ccj.sample.gwt.client.event.SearchEvent;
import fr.generali.ccj.sample.gwt.shared.dispatch.FacetsAction;
import fr.generali.ccj.sample.gwt.shared.dispatch.FacetsResult;
import fr.generali.ccj.sample.gwt.shared.dto.FacetDto;
import fr.generali.ccj.sample.gwt.shared.dto.FacetEntryDto;
import fr.generali.ccj.sample.gwt.shared.dto.FacetsDto;

public class Geoname extends Composite implements AsyncCallback<FacetsResult>, ClickHandler {

    private static GeonameUiBinder uiBinder = GWT.create(GeonameUiBinder.class);

    @UiField
    Button searchButton;

    @UiField
    VerticalPanel facetsPanel;

    @UiField
    TextBox autocomplete;

    private final EventBus eventBus;

    private FacetsDto facets;

    interface GeonameUiBinder extends UiBinder<Widget, Geoname> {
    }

    @Inject
    public Geoname(EventBus eventBus, DispatchAsync dispatch) {
        this.eventBus = eventBus;
        initWidget(uiBinder.createAndBindUi(this));

        dispatch.execute(new FacetsAction(), this);
    }

    @UiHandler("searchButton")
    void onSearchButtonClick(ClickEvent event) {
        this.eventBus.fireEvent(new SearchEvent(facets));
    }

    public void onFailure(Throwable caught) {
        Window.alert(caught.toString());
    }

    public void onSuccess(FacetsResult result) {
        facets = result.getFacets();
        for (FacetDto facetDto : facets.getFacets()) {
            facetsPanel.add(new Label(facetDto.getName()));
            ArrayList<FacetEntryDto> entries = facetDto.getEntries();
            for (FacetEntryDto entry : entries) {
                facetsPanel.add(new FacetEntryCheckbox(entry));
            }
        }
    }

    class FacetEntryCheckbox extends CheckBox {
        private final FacetEntryDto facetEntryDto;

        public FacetEntryCheckbox(FacetEntryDto facetEntryDto) {
            super();
            this.facetEntryDto = facetEntryDto;
            NumberFormat formatter = NumberFormat.getDecimalFormat();
            setText(formatter.format(facetEntryDto.getFrom()) + " - " + formatter.format(facetEntryDto.getTo()) + " ("
                            + formatter.format(facetEntryDto.getCount()) + ")");
            addClickHandler(Geoname.this);
        }

        public FacetEntryDto getFacetEntryDto() {
            return facetEntryDto;
        }
    }

    public void onClick(ClickEvent event) {
        FacetEntryCheckbox source = (FacetEntryCheckbox ) event.getSource();
        source.getFacetEntryDto().setSelected(source.getValue());
    }

    Timer timer = new Timer() {

        @Override
        public void run() {
            eventBus.fireEvent(new SearchEvent(autocomplete.getText()));
        }

    };

    @UiHandler("autocomplete")
    void onAutocompleteKeyUp(KeyUpEvent event) {
        timer.cancel();
        if (autocomplete.getText().trim().length() > 0)
            timer.schedule(200);
    }
}
