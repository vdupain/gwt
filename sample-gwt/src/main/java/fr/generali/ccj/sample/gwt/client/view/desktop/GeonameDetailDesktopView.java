package fr.generali.ccj.sample.gwt.client.view.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;
import com.google.maps.gwt.client.Circle;
import com.google.maps.gwt.client.CircleOptions;
import com.google.maps.gwt.client.GoogleMap;
import com.google.maps.gwt.client.GoogleMap.ZoomChangedHandler;
import com.google.maps.gwt.client.InfoWindow;
import com.google.maps.gwt.client.InfoWindowOptions;
import com.google.maps.gwt.client.LatLng;
import com.google.maps.gwt.client.MapOptions;
import com.google.maps.gwt.client.MapTypeId;

import fr.generali.ccj.sample.gwt.client.view.GeonameDetailView;
import fr.generali.ccj.sample.gwt.shared.dto.GeonameDto;

@Singleton
public class GeonameDetailDesktopView extends ResizeComposite implements GeonameDetailView {

    interface Binder extends UiBinder<Widget, GeonameDetailDesktopView> {
    }

    private static final Binder binder = GWT.create(Binder.class);

    private GoogleMap map;

    private Presenter presenter;

    public GeonameDetailDesktopView() {
        initWidget(binder.createAndBindUi(this));
    }

    public void setGeonameDto(final GeonameDto geonameDto) {
        final LatLng myLatLng = LatLng.create(geonameDto.getLatitude(), geonameDto.getLongitude());
        MapOptions myOptions = MapOptions.create();
        myOptions.setZoom(8.0);
        myOptions.setCenter(myLatLng);
        myOptions.setMapTypeId(MapTypeId.ROADMAP);
        myOptions.setScaleControl(true);
        map = GoogleMap.create(Document.get().getElementById("map_canvas"), myOptions);

        final InfoWindow coordInfoWindow = InfoWindow.create();
        coordInfoWindow.setContent(createInfoWindowContent(geonameDto));
        coordInfoWindow.setPosition(myLatLng);
        InfoWindowOptions options = InfoWindowOptions.create();
        options.setMaxWidth(320);
        coordInfoWindow.setOptions(options);
        coordInfoWindow.open(map);

        map.addZoomChangedListener(new ZoomChangedHandler() {
            public void handle() {
                coordInfoWindow.setContent(createInfoWindowContent(geonameDto));
                coordInfoWindow.open(map);
            }
        });

     // Construct the circle for each value in citymap. Scale population by 20.
        CircleOptions populationOptions = CircleOptions.create();
        populationOptions.setStrokeColor("#ff0000");
        populationOptions.setStrokeOpacity(0.8);
        populationOptions.setStrokeWeight(2);
        populationOptions.setFillColor("#ff0000");
        populationOptions.setFillOpacity(0.35);
        populationOptions.setMap(map);
        final LatLng center = LatLng.create(40.0, -70.0);
        populationOptions.setCenter(myLatLng);
        populationOptions.setRadius(geonameDto.getPopulation() / 20);
        //populationOptions.setRadius(100*1000);
        Circle.create(populationOptions);
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    private String createInfoWindowContent(GeonameDto geonameDto) {
        SafeHtmlBuilder returnString = new SafeHtmlBuilder();
        returnString.appendHtmlConstant(geonameDto.getName() + " - " + geonameDto.getCountryCode() + "<br>");
        returnString.appendHtmlConstant("Asciiname:" + geonameDto.getAsciiname() + "<br>");
        returnString.appendHtmlConstant("Alternatename:" + geonameDto.getAlternatenames() + "<br>");
        returnString.appendHtmlConstant("Population:" + geonameDto.getPopulation() + "<br>");
        returnString.appendHtmlConstant("LatLng: ");
        returnString.append(geonameDto.getLatitude());
        returnString.appendHtmlConstant(", ");
        returnString.append(geonameDto.getLongitude());
        returnString.appendHtmlConstant("<br />");

        returnString.appendHtmlConstant("at Zoom Level: ");
        returnString.append(map.getZoom());
        return returnString.toSafeHtml().asString();
    }

}
