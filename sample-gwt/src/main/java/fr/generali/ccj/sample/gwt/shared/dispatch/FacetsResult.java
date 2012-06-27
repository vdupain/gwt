package fr.generali.ccj.sample.gwt.shared.dispatch;

import net.customware.gwt.dispatch.shared.Result;
import fr.generali.ccj.sample.gwt.shared.dto.FacetsDto;

public class FacetsResult implements Result {

    private FacetsDto facets;

    public FacetsResult() {

    }

    public FacetsDto getFacets() {
        return facets;
    }

    public void setFacets(FacetsDto facets) {
        this.facets = facets;
    }

}
