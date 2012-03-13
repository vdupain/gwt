package fr.generali.ccj.sample.gwt.client.dispatch;

import java.util.HashMap;
import java.util.Map;

import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;

public class DispatchUrlBuilder {

    Map<Action< ? extends Result>, String> urlMapping = new HashMap<Action< ? extends Result>, String>();

    private static final String DEFAULT_DISPATCHER_REMOTE_SERVICE_RELATIVE_PATH = "dispatch";

    private String dispatchServletName = DEFAULT_DISPATCHER_REMOTE_SERVICE_RELATIVE_PATH;

    public DispatchUrlBuilder() {
    }

    public <R extends Result> String buildURL(final Action<R> action) {
        String url = urlMapping.get(action);
        if (url == null) {
            StringBuffer sb = new StringBuffer(GWT.getHostPageBaseURL());
            sb.append(GWT.getModuleName()).append("/").append(dispatchServletName).append("/")
                            .append(action.getClass().getName());
            url = sb.toString();
            urlMapping.put(action, url);
        }
        return url;
    }

}
