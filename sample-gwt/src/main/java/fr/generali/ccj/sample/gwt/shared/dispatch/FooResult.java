package fr.generali.ccj.sample.gwt.shared.dispatch;

import net.customware.gwt.dispatch.shared.Result;
import fr.generali.ccj.sample.gwt.shared.dto.FooDto;

public class FooResult implements Result {
    private FooDto fooDto;

    /** For serialization only. */
    FooResult() {
    }

    public FooResult(FooDto fooDto) {
        this.fooDto = fooDto;
    }

    public FooDto getFooDto() {
        return fooDto;
    }

}
