package fr.generali.ccj.sample.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

import fr.generali.ccj.sample.gwt.client.gin.MyInjector;
import fr.generali.ccj.sample.gwt.client.view.GeonameMainContentPlace;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SampleEntryPoint implements EntryPoint {
   
    private Place defaultPlace = new GeonameMainContentPlace("");

    private SimplePanel appWidget = new SimplePanel();

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {      
        MyInjector injector = GWT.create(MyInjector.class);
        
        /*
        ClientFactory clientFactory = GWT.create(ClientFactory.class);
        EventBus eventBus = clientFactory.getEventBus();
        PlaceController placeController = clientFactory.getPlaceControler();

        // Start ActivityManager for the main widget with our ActivityMapper
        ActivityMapper activityMapper = new AppActivityMapper(clientFactory);
        ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
        activityManager.setDisplay(appWidget);

        // Start PlaceHistoryHandler with our PlaceHistoryMapper
        AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController, eventBus, defaultPlace);

        RootPanel.get().add(appWidget);

        // Goes to the place represented on URL else default place
        historyHandler.handleCurrentHistory();
        */
        
        RootPanel.get().add(injector.getGeonameMainView());
        // Goes to place represented on URL or default place
        //injector.getPlaceHistoryHandler().handleCurrentHistory();
    }

}
