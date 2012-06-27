package fr.generali.ccj.sample.gwt.client.view;

import java.util.ArrayList;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.view.client.AsyncDataProvider;

import fr.generali.ccj.sample.gwt.shared.dispatch.SearchAction;
import fr.generali.ccj.sample.gwt.shared.dto.GeonameDto;

public interface GeonameListView extends IsWidget {

    void setPresenter(Presenter presenter);

    void setPageIndex(int pageIndex);

    int getPageIndex();

    void setTotalHits(long totalHits);

    ArrayList<GeonameDto> getCurrentList();
    
    AsyncDataProvider<GeonameDto> getDataProvider();

    void setCurrentList(ArrayList<GeonameDto> list);

    void update();

    public interface Presenter {
        void goTo(Place place);

    }

    void setAction(SearchAction action);


}
