package fr.generali.ccj.sample.gwt.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class PageChangedEvent extends GwtEvent<PageChangedEvent.Handler> {

    public interface Handler extends EventHandler {

        void onPageChanged(PageChangedEvent event);

    }

    public enum PageChangeType {
        FIRST, PREVIOUS, NEXT, LAST
    }

    public static final Type<Handler> TYPE = new Type<Handler>();

    private final PageChangeType pageChangeType;

    public PageChangedEvent(PageChangeType pageChangeType) {
        this.pageChangeType = pageChangeType;
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<PageChangedEvent.Handler> getAssociatedType() {
        return this.TYPE;
    }

    @Override
    protected void dispatch(Handler handler) {
        handler.onPageChanged(this);
    }

    public PageChangeType getPageChangeType() {
        return pageChangeType;
    }
}
