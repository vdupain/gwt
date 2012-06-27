package fr.generali.ccj.sample.gwt.shared.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class FacetsDto implements Serializable {
    private ArrayList<FacetDto> facets = new ArrayList<FacetDto>();

    public void add(FacetDto facetDto) {
        facets.add(facetDto);
    }

    public ArrayList<FacetDto> getFacets() {
        return facets;
    }
}
