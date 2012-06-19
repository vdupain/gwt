package fr.generali.ccj.sample.gwt.client.view;

import java.util.ArrayList;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import fr.generali.ccj.sample.gwt.client.event.GeonameSelectedEvent;
import fr.generali.ccj.sample.gwt.client.event.PageChangedEvent;
import fr.generali.ccj.sample.gwt.client.view.GeonameMainContentView.Presenter;
import fr.generali.ccj.sample.gwt.client.view.desktop.GeonameListDesktopView;
import fr.generali.ccj.sample.gwt.shared.dispatch.GeonameListAction;
import fr.generali.ccj.sample.gwt.shared.dispatch.GeonameListResult;
import fr.generali.ccj.sample.gwt.shared.dto.GeonameDto;

@Singleton
public class GeonameMainContentPresenter extends AbstractActivity implements Presenter, GeonameSelectedEvent.Handler,
                PageChangedEvent.Handler, AsyncCallback<GeonameListResult> {

    private final PlaceController placeController;

    private final GeonameMainContentView view;

    private final GeonameListView geonameListView;

    private final GeonameDetailView geonameDetailView;

    private ArrayList<HandlerRegistration> handlerRegistrations = new ArrayList<HandlerRegistration>();

    private final DispatchAsync dispatch;

    private String geonameId;

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
        if ("".equals(place.getToken()))
            return this;
        String sPageIndex = place.getToken().substring(0, place.getToken().indexOf("_"));
        String sGeonameId = place.getToken().substring(place.getToken().indexOf("_") + 1, place.getToken().length());
        int pageIndex = Integer.valueOf(sPageIndex);
        update(pageIndex, sGeonameId);
        return this;
    }

    private void update(int pageIndex, final String geonameId) {
        this.geonameId = geonameId;
        geonameListView.setPageIndex(pageIndex);
        dispatch.execute(new GeonameListAction(geonameListView.getPageIndex(), GeonameListDesktopView.PAGE_SIZE), this);
    }

    public void onPageChanged(PageChangedEvent event) {
        if (event.getPageChangeType().equals(PageChangedEvent.PageChangeType.FIRST)) {
            update(geonameListView.getPageIndex(), null);
        } else if (event.getPageChangeType().equals(PageChangedEvent.PageChangeType.PREVIOUS)) {
            update(geonameListView.getPageIndex(), null);
        } else if (event.getPageChangeType().equals(PageChangedEvent.PageChangeType.NEXT)) {
            update(geonameListView.getPageIndex(), null);
        } else if (event.getPageChangeType().equals(PageChangedEvent.PageChangeType.LAST)) {
            update(geonameListView.getPageIndex(), null);
        }

    }

    public void onFailure(Throwable caught) {
        Window.alert(caught.toString());
    }

    public void onSuccess(GeonameListResult result) {
        ArrayList<GeonameDto> list = result.getList();
        geonameListView.setTotalHits(result.getTotalHits());
        geonameListView.setCurrentList(list);
        geonameListView.update();
        updateSelectedItem(geonameId);
    }

    private void updateSelectedItem(final String sGeonameId) {
        for (GeonameDto geonameDto : geonameListView.getCurrentList()) {
            if (Integer.toString(geonameDto.getGeonameId()).equals(sGeonameId)) {
                geonameDetailView.setGeonameDto(geonameDto);
                break;
            }
        }
    }

    public void onGeonameSelected(GeonameSelectedEvent event) {
        goTo(new GeonameMainContentPlace(geonameListView.getPageIndex() + "_" + event.getItem().getGeonameId()));
    }

}
