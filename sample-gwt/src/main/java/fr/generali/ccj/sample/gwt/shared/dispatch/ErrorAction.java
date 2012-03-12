package fr.generali.ccj.sample.gwt.shared.dispatch;

import net.customware.gwt.dispatch.shared.Action;

public class ErrorAction implements Action<NoResult> {

    private boolean checkedException;

    /** For serialization only. */
    public ErrorAction() {
    }

    public ErrorAction(boolean checkedException) {
        this.setCheckedException(checkedException);
    }

    public boolean isCheckedException() {
        return checkedException;
    }

    public void setCheckedException(boolean checkedException) {
        this.checkedException = checkedException;
    }

}
