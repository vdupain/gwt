package fr.generali.ccj.sample.gwt.client.dispatch;

import java.util.HashMap;
import java.util.Map;

import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import com.google.gwt.core.client.GWT;

public class DefaultDispatchUrlBuilder implements DispatchUrlBuilder {

    Map<Action< ? extends Result>, String> urlMapping = new HashMap<Action< ? extends Result>, String>();

    /**
     * Le nom par défaut du chemin relatif (au module) de la servlet RPC du
     * dispatcher.
     * 
     * @see net.customware.gwt.dispatch.client.standard.StandardDispatchService
     */
    private static final String DEFAULT_DISPATCHER_REMOTE_SERVICE_RELATIVE_PATH = "dispatch";

    /**
     * C'est le nom du RPC dispatcher définit dans web.xml. Il est en générale à
     * "dispatch".
     * 
     * <pre>
     * 	<servlet>
     * 	<servlet-name>dispatch</servlet-name>
     * 	<servlet-class>net.customware.gwt.dispatch.server.spring.SpringStandardDispatchServlet</servlet-class>
     * 	<load-on-startup>1</load-on-startup>
     * </servlet>
     * </pre>
     */
    private String dispatchServletName = DEFAULT_DISPATCHER_REMOTE_SERVICE_RELATIVE_PATH;

    public DefaultDispatchUrlBuilder() {
    }

    public DefaultDispatchUrlBuilder(final String dispatchServletName) {
        this.dispatchServletName = dispatchServletName;
    }

    /*
     * Cette implémentation donne une URL de ce type :
     * http://127.0.0.1:8080/${context
     * -web}/${nom-module-gwt}/dispatch/${nom-class-action}
     * http://127.0.0.1:8080
     * /ssgd/SSGD/dispatch/fr.generali.gwt.dispatch.shared.IncrementCounter
     */
    public <R extends Result> String buildURL(final Action<R> action) {
        String url = urlMapping.get(action);
        if (url == null) {
            StringBuffer sb = new StringBuffer(GWT.getHostPageBaseURL());
            sb.append(dispatchServletName);
            sb.append("/");
            sb.append(action.getClass().getName());
            url = sb.toString();
            urlMapping.put(action, url);
        }
        return url;
    }

}
