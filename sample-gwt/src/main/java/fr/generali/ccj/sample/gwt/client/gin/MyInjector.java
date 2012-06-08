package fr.generali.ccj.sample.gwt.client.gin;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

import fr.generali.ccj.sample.gwt.client.MyMainPanel;

@GinModules(MyClientModule.class)
public interface MyInjector extends Ginjector {
    MyMainPanel getMainPanel();
}
