package fr.generali.ccj.sample.gwt.client.dispatch;

import net.customware.gwt.dispatch.client.AbstractDispatchAsync;
import net.customware.gwt.dispatch.client.ExceptionHandler;
import net.customware.gwt.dispatch.client.standard.StandardDispatchService;
import net.customware.gwt.dispatch.client.standard.StandardDispatchServiceAsync;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 * Cette implémentation permet de modifier l'URL de dispatcher afin que celle-ci
 * soit filtrable.
 * 
 * @see {@link DispatchUrlBuilder}
 */
public class MyDispatchAsync extends AbstractDispatchAsync {

    private static final StandardDispatchServiceAsync realService = GWT.create(StandardDispatchService.class);

    private final DispatchUrlBuilder urlBuilder;

    public MyDispatchAsync(ExceptionHandler exceptionHandler, DispatchUrlBuilder urlBuilder) {
        super(exceptionHandler);
        this.urlBuilder = urlBuilder;
    }

    public <A extends Action<R>, R extends Result> void execute(final A action, final AsyncCallback<R> callback) {
        ((ServiceDefTarget ) realService).setServiceEntryPoint(urlBuilder.buildURL(action));

        realService.execute(action, new AsyncCallback<Result>() {

            public void onFailure(Throwable caught) {
                MyDispatchAsync.this.onFailure(action, caught, callback);
            }

            @SuppressWarnings("unchecked")
            public void onSuccess(Result result) {
                MyDispatchAsync.this.onSuccess(action, (R ) result, callback);
            }
        });
    }

}
