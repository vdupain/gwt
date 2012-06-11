package fr.generali.ccj.sample.gwt.shared.dispatch;

import net.customware.gwt.dispatch.shared.Action;

public class GeonameListAction implements Action<GeonameListResult> {

    private int from;

    private int size;

    /** For serialization only. */
    GeonameListAction() {
    }

    public GeonameListAction(int from, int size) {
        this.from = from;
        this.size = size;
    }

    public int getFrom() {
        return from;
    }

    public int getSize() {
        return size;
    }
}
