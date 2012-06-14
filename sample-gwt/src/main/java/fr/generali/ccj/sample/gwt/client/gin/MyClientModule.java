package fr.generali.ccj.sample.gwt.client.gin;

import net.customware.gwt.dispatch.client.DefaultExceptionHandler;
import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.dispatch.client.secure.CookieSecureSessionAccessor;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import fr.generali.ccj.sample.gwt.client.dispatch.DispatchUrlBuilder;
import fr.generali.ccj.sample.gwt.client.dispatch.MySecureDispatchAsync;
import fr.generali.ccj.sample.gwt.client.mvp.ActivityModule;
import fr.generali.ccj.sample.gwt.client.mvp.MvpModule;
import fr.generali.ccj.sample.gwt.client.view.ViewModule;

public class MyClientModule extends AbstractGinModule {

    @Override
    protected void configure() {
        // install the Gin module used to setup the GWT MVP framework classes.
        install(new MvpModule());
        install(new ViewModule());
        install(new ActivityModule());
    }

    @Provides
    @Singleton
    DispatchAsync provideDispatchAsync() {
        return new MySecureDispatchAsync(new DefaultExceptionHandler(), new DispatchUrlBuilder(),
                        new CookieSecureSessionAccessor("JSESSIONID"));
    }
}
