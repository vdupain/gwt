package fr.generali.ccj.sample.gwt.shared.dispatch;

import java.util.ArrayList;

import net.customware.gwt.dispatch.shared.Result;
import fr.generali.ccj.sample.gwt.shared.dto.GeonameDto;

public class GeonameListResult implements Result {
    private ArrayList<GeonameDto> list = new ArrayList<GeonameDto>();

    private long totalHits;

    public GeonameListResult() {
    }

    public void add(GeonameDto geonameDto) {
        list.add(geonameDto);
    }

    public ArrayList<GeonameDto> getList() {
        return this.list;
    }

    public void setTotalHits(long totalHits) {
        this.totalHits = totalHits;
    }

    public long getTotalHits() {
        return totalHits;
    }

}
