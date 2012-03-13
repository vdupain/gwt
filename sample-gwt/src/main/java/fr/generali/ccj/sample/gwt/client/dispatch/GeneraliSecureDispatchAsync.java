package fr.generali.ccj.sample.gwt.client.dispatch;

import net.customware.gwt.dispatch.client.AbstractDispatchAsync;
import net.customware.gwt.dispatch.client.ExceptionHandler;
import net.customware.gwt.dispatch.client.secure.SecureDispatchService;
import net.customware.gwt.dispatch.client.secure.SecureDispatchServiceAsync;
import net.customware.gwt.dispatch.client.secure.SecureSessionAccessor;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;
import net.customware.gwt.dispatch.shared.secure.InvalidSessionException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class GeneraliSecureDispatchAsync extends AbstractDispatchAsync {

    private static final SecureDispatchServiceAsync realService = GWT.create(SecureDispatchService.class);

    private final SecureSessionAccessor secureSessionAccessor;

    private final DispatchUrlBuilder urlBuilder;

    public GeneraliSecureDispatchAsync(ExceptionHandler exceptionHandler, DispatchUrlBuilder urlBuilder,
                    SecureSessionAccessor secureSessionAccessor) {
        super(exceptionHandler);
        this.secureSessionAccessor = secureSessionAccessor;
        this.urlBuilder = urlBuilder;
    }

    public <A extends Action<R>, R extends Result> void execute(final A action, final AsyncCallback<R> callback) {

        String sessionId = secureSessionAccessor.getSessionId();

        ((ServiceDefTarget ) realService).setServiceEntryPoint(urlBuilder.buildURL(action));

        realService.execute(sessionId, action, new AsyncCallback<Result>() {

            public void onFailure(Throwable caught) {
                GeneraliSecureDispatchAsync.this.onFailure(action, caught, callback);

            }

            @SuppressWarnings("unchecked")
            public void onSuccess(Result result) {
                GeneraliSecureDispatchAsync.this.onSuccess(action, (R ) result, callback);

            }
        });
    }

    @Override
    protected <A extends Action<R>, R extends Result> void onFailure(A action, Throwable caught,
                    final AsyncCallback<R> callback) {
        if (caught instanceof InvalidSessionException) {
            secureSessionAccessor.clearSessionId();
        }

        super.onFailure(action, caught, callback);

    }
}
