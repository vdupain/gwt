package fr.generali.ccj.sample.gwt.shared.dispatch;

import net.customware.gwt.dispatch.shared.Action;
import fr.generali.ccj.sample.gwt.shared.dto.FacetsDto;

public class SearchAction implements Action<GeonameListResult> {

    private int from;

    private int size;

    private FacetsDto facets;

    private String pattern;

    public SearchAction() {
    }

    public SearchAction(FacetsDto facets) {
        this.facets = facets;
    }

    public SearchAction(String pattern) {
        this.pattern = pattern;
    }

    public FacetsDto getFacets() {
        return facets;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

}
