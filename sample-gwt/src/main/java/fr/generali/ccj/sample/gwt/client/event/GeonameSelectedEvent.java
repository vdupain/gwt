package fr.generali.ccj.sample.gwt.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import fr.generali.ccj.sample.gwt.shared.dto.GeonameDto;

public class GeonameSelectedEvent extends GwtEvent<GeonameSelectedEvent.Handler> {

    public interface Handler extends EventHandler {

        void onGeonameSelected(GeonameSelectedEvent event);

    }

    public static final Type<Handler> TYPE = new Type<Handler>();

    private final GeonameDto item;

    public GeonameSelectedEvent(GeonameDto item) {
        this.item = item;
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<GeonameSelectedEvent.Handler> getAssociatedType() {
        return this.TYPE;
    }

    @Override
    protected void dispatch(Handler handler) {
        handler.onGeonameSelected(this);
    }

    public GeonameDto getItem() {
        return item;
    }

}
