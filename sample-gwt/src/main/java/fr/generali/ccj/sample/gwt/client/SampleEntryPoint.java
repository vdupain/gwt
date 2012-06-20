package fr.generali.ccj.sample.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

import fr.generali.ccj.sample.gwt.client.gin.MyInjector;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SampleEntryPoint implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        MyInjector injector = GWT.create(MyInjector.class);
        RootPanel.get().add(injector.getGeonameMainView());
    }

}
