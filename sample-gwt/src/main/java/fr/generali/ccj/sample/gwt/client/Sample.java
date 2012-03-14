package fr.generali.ccj.sample.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

import fr.generali.ccj.sample.gwt.client.gin.MyInjector;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Sample implements EntryPoint {
    private final MyInjector injector = GWT.create(MyInjector.class);

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        MyMainPanel mainPanel = injector.getMainPanel();
        RootPanel.get().add(mainPanel);
    }
}
