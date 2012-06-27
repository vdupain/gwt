package fr.generali.ccj.sample.gwt.shared.dto;

import java.io.Serializable;

public class FacetEntryDto implements Serializable {
    private double from, to, count;

    private boolean selected = false;

    public FacetEntryDto() {
    }

    public FacetEntryDto(double from, double to, double count) {
        this.from = from;
        this.to = to;
        this.count = count;
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public double getCount() {
        return count;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
