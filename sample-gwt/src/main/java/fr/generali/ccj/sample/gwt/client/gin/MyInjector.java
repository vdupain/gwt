package fr.generali.ccj.sample.gwt.client.gin;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceHistoryHandler;

import fr.generali.ccj.sample.gwt.client.MyMainPanel;
import fr.generali.ccj.sample.gwt.client.view.desktop.GeonameListDesktopView;
import fr.generali.ccj.sample.gwt.client.view.desktop.GeonameMainDesktopView;

@GinModules(MyClientModule.class)
public interface MyInjector extends Ginjector {
    PlaceHistoryHandler getPlaceHistoryHandler();  
    
    MyMainPanel getMainPanel();
    
    GeonameListDesktopView getCountryList();
    
    GeonameMainDesktopView getGeonameMainView();
}
