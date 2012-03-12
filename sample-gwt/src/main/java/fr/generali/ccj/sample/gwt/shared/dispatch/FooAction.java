package fr.generali.ccj.sample.gwt.shared.dispatch;

import net.customware.gwt.dispatch.shared.Action;
import fr.generali.ccj.sample.gwt.shared.dto.FooDto;

public class FooAction implements Action<FooResult> {
    private FooDto fooDto;

    /** For serialization only. */
    FooAction() {
    }

    public FooAction(FooDto fooDto) {
        this.fooDto = fooDto;
    }

    public FooDto getFooDto() {
        return this.fooDto;
    }

}
