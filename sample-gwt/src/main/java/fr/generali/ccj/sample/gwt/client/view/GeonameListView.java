package fr.generali.ccj.sample.gwt.client.view;

import java.util.ArrayList;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

import fr.generali.ccj.sample.gwt.shared.dto.GeonameDto;

public interface GeonameListView extends IsWidget {

    void setPresenter(Presenter presenter);

    void setPageIndex(int pageIndex);

    ArrayList<GeonameDto> getCurrentList();

    void update();

    public interface Presenter {
        void goTo(Place place);

    }


}
