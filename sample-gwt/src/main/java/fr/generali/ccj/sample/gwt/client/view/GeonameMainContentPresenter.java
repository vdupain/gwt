package fr.generali.ccj.sample.gwt.client.view;

import java.util.ArrayList;
import java.util.Set;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.view.client.HasData;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import fr.generali.ccj.sample.gwt.client.event.GeonameSelectedEvent;
import fr.generali.ccj.sample.gwt.client.event.PageChangedEvent;
import fr.generali.ccj.sample.gwt.client.event.SearchEvent;
import fr.generali.ccj.sample.gwt.client.view.GeonameMainContentView.Presenter;
import fr.generali.ccj.sample.gwt.client.view.desktop.GeonameListDesktopView;
import fr.generali.ccj.sample.gwt.shared.dispatch.GeonameListResult;
import fr.generali.ccj.sample.gwt.shared.dispatch.SearchAction;
import fr.generali.ccj.sample.gwt.shared.dto.FacetsDto;
import fr.generali.ccj.sample.gwt.shared.dto.GeonameDto;

@Singleton
public class GeonameMainContentPresenter extends AbstractActivity implements Presenter, GeonameSelectedEvent.Handler,
                PageChangedEvent.Handler, AsyncCallback<GeonameListResult>, SearchEvent.Handler {

    private final PlaceController placeController;

    private final GeonameMainContentView view;

    private final GeonameListView geonameListView;

    private final GeonameDetailView geonameDetailView;

    private ArrayList<HandlerRegistration> handlerRegistrations = new ArrayList<HandlerRegistration>();

    private final DispatchAsync dispatch;

    private FacetsDto facets;

    @Inject
    public GeonameMainContentPresenter(GeonameMainContentView geonameMainView, GeonameListView geonameListView,
                    GeonameDetailView geonameDetailView, PlaceController placeController, EventBus eventBus,
                    DispatchAsync dispatch) {
        this.view = geonameMainView;
        this.geonameListView = geonameListView;
        this.geonameDetailView = geonameDetailView;
        this.placeController = placeController;
        this.dispatch = dispatch;
    }

    @Override
    public void onStop() {
        super.onStop();
        removeHandlers();
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        removeHandlers();
        HandlerRegistration handlerRegistration =
                        eventBus.addHandler(GeonameSelectedEvent.TYPE, (GeonameSelectedEvent.Handler ) this);
        handlerRegistrations.add(handlerRegistration);
        handlerRegistration = eventBus.addHandler(PageChangedEvent.TYPE, (PageChangedEvent.Handler ) this);
        handlerRegistrations.add(handlerRegistration);
        handlerRegistration = eventBus.addHandler(SearchEvent.TYPE, (SearchEvent.Handler ) this);
        handlerRegistrations.add(handlerRegistration);
        view.setPresenter(this);
        panel.setWidget(view.asWidget());
    }

    private void removeHandlers() {
        for (HandlerRegistration handlerRegistration : handlerRegistrations) {
            handlerRegistration.removeHandler();
        }
    }

    /**
     * Navigate to a new Place in the browser
     */
    public void goTo(Place place) {
        placeController.goTo(place);
    }

    public GeonameMainContentPresenter withPlace(GeonameMainContentPlace place) {
        // if ("".equals(place.getToken()))
        // return this;
        // String sPageIndex = place.getToken().substring(0,
        // place.getToken().indexOf("_"));
        // String sGeonameId =
        // place.getToken().substring(place.getToken().indexOf("_") + 1,
        // place.getToken().length());
        // int pageIndex = Integer.valueOf(sPageIndex);
        // update(pageIndex, sGeonameId);
        return this;
    }

    private void update(int pageIndex) {
        SearchAction action = new SearchAction(facets);
        action.setFrom(geonameListView.getPageIndex());
        action.setSize(GeonameListDesktopView.PAGE_SIZE);
        dispatch.execute(action, this);
    }

    public void onPageChanged(PageChangedEvent event) {
        if (event.getPageChangeType().equals(PageChangedEvent.PageChangeType.FIRST)) {
            update(geonameListView.getPageIndex());
        } else if (event.getPageChangeType().equals(PageChangedEvent.PageChangeType.PREVIOUS)) {
            update(geonameListView.getPageIndex());
        } else if (event.getPageChangeType().equals(PageChangedEvent.PageChangeType.NEXT)) {
            update(geonameListView.getPageIndex());
        } else if (event.getPageChangeType().equals(PageChangedEvent.PageChangeType.LAST)) {
            update(geonameListView.getPageIndex());
        }

    }

    public void onFailure(Throwable caught) {
        Window.alert(caught.toString());
    }

    public void onSuccess(GeonameListResult result) {
        geonameListView.setTotalHits(result.getTotalHits());
        geonameListView.setCurrentList(result.getList());
        geonameListView.update();
    }

    private void updateSelectedItem(final String sGeonameId) {
        Set<HasData<GeonameDto>> dataDisplays = geonameListView.getDataProvider().getDataDisplays();
        for (HasData<GeonameDto> hasData : dataDisplays) {
            Iterable<GeonameDto> visibleItems = hasData.getVisibleItems();
            for (GeonameDto geonameDto : visibleItems) {
                if (Integer.toString(geonameDto.getGeonameId()).equals(sGeonameId)) {
                    geonameDetailView.setGeonameDto(geonameDto);
                    break;
                }
            }
        }
    }

    public void onGeonameSelected(GeonameSelectedEvent event) {
        updateSelectedItem("" + event.getItem().getGeonameId());
    }

    public void onSearch(SearchEvent event) {
        SearchAction action = new SearchAction();
        if (event.getSearchType().equals(SearchEvent.SearchType.FACETS)) {
            facets = event.getFacets();
            action = new SearchAction(facets);
            action.setFrom(0);
            action.setSize(GeonameListDesktopView.PAGE_SIZE);
        } else if (event.getSearchType().equals(SearchEvent.SearchType.AUTOCOMPLETE)) {
            facets = null;
            action = new SearchAction(event.getPattern());
            action.setFrom(0);
            action.setSize(GeonameListDesktopView.PAGE_SIZE);
        }
        geonameListView.setAction(action);
        dispatch.execute(action, this);
    }

}
