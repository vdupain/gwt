package fr.generali.ccj.sample.gwt.shared.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class FacetDto implements Serializable {
    private ArrayList<FacetEntryDto> entries = new ArrayList<FacetEntryDto>();

    private String name;

    public FacetDto() {
    }

    public FacetDto(String name) {
        this.name = name;
    }

    public void addEntry(double from, double to, double count) {
        entries.add(new FacetEntryDto(from, to, count));
    }

    public ArrayList<FacetEntryDto> getEntries() {
        return entries;
    }

    public String getName() {
        return name;
    }
}
