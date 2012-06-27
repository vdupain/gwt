package fr.generali.ccj.sample.gwt.client.view.desktop;

import java.util.ArrayList;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import fr.generali.ccj.sample.gwt.client.event.GeonameSelectedEvent;
import fr.generali.ccj.sample.gwt.client.event.PageChangedEvent;
import fr.generali.ccj.sample.gwt.client.view.GeonameListView;
import fr.generali.ccj.sample.gwt.shared.dispatch.GeonameListResult;
import fr.generali.ccj.sample.gwt.shared.dispatch.SearchAction;
import fr.generali.ccj.sample.gwt.shared.dto.GeonameDto;

@Singleton
public class GeonameListDesktopView extends ResizeComposite implements GeonameListView,
                com.google.gwt.view.client.SelectionChangeEvent.Handler {

    private ArrayList<GeonameDto> currentList = new ArrayList<GeonameDto>();

    // Create a data provider.
    AsyncDataProvider<GeonameDto> dataProvider = new AsyncDataProvider<GeonameDto>() {

        @Override
        protected void onRangeChanged(HasData<GeonameDto> display) {
            if (action==null) return;
           final  int start = display.getVisibleRange().getStart();
            int end = start + display.getVisibleRange().getLength();
            action.setFrom(start);
            action.setSize(end);        
            AsyncCallback<GeonameListResult> callback = new AsyncCallback<GeonameListResult>() {
                
                public void onSuccess(GeonameListResult result) {
                    dataProvider.updateRowData(start, result.getList());
                }
                
                public void onFailure(Throwable caught) {
                    Window.alert(caught.getMessage());
                }
            };
            dispatch.execute(action, callback );
            
        }};

    interface Binder extends UiBinder<Widget, GeonameListDesktopView> {
    }

    private static final Binder binder = GWT.create(Binder.class);

    public static final int PAGE_SIZE = 20;

    // Create name column.
    TextColumn<GeonameDto> geonameIdColumn = new TextColumn<GeonameDto>() {
        @Override
        public String getValue(GeonameDto geonameDto) {
            return "" + geonameDto.getGeonameId();
        }
    };

    TextColumn<GeonameDto> nameColumn = new TextColumn<GeonameDto>() {
        @Override
        public String getValue(GeonameDto geonameDto) {
            return "" + geonameDto.getName();
        }
    };

    TextColumn<GeonameDto> countryCodeColumn = new TextColumn<GeonameDto>() {
        @Override
        public String getValue(GeonameDto geonameDto) {
            return "" + geonameDto.getCountryCode();
        }
    };

    TextColumn<GeonameDto> longitudeColumn = new TextColumn<GeonameDto>() {
        @Override
        public String getValue(GeonameDto geonameDto) {
            return "" + geonameDto.getLongitude();
        }
    };

    TextColumn<GeonameDto> latituteColumn = new TextColumn<GeonameDto>() {
        @Override
        public String getValue(GeonameDto geonameDto) {
            return "" + geonameDto.getLatitude();
        }
    };


    @UiField
    CellTable table;
    
    @UiField
    SimplePager pager;


//    @UiField
//    SelectionStyle selectionStyle;

    private int pageIndex;

    private Presenter presenter;

    private long totalHits = 0;

    private final EventBus eventBus;

    private final DispatchAsync dispatch;

    private SearchAction action;

    @Inject
    public GeonameListDesktopView(EventBus eventBus, DispatchAsync dispatch) {
        this.eventBus = eventBus;
        this.dispatch = dispatch;
        initWidget(binder.createAndBindUi(this));
        initTable();
    }

    void newer() {
        // Move back a page.
        pageIndex -= PAGE_SIZE;
        if (pageIndex < 0) {
            pageIndex = 0;
        } else {
//            styleRow(selectedRow, false);
//            selectedRow = -1;
            this.eventBus.fireEvent(new PageChangedEvent(PageChangedEvent.PageChangeType.NEXT));
        }
    }

    void older() {
        // Move forward a page.
        pageIndex += PAGE_SIZE;
        if (pageIndex >= totalHits) {
            pageIndex -= PAGE_SIZE;
        } else {
//            styleRow(selectedRow, false);
//            selectedRow = -1;
            this.eventBus.fireEvent(new PageChangedEvent(PageChangedEvent.PageChangeType.PREVIOUS));
        }
    }

    void first() {
        pageIndex = 0;
//        styleRow(selectedRow, false);
//        selectedRow = -1;
        this.eventBus.fireEvent(new PageChangedEvent(PageChangedEvent.PageChangeType.FIRST));
    }

    void last() {
        pageIndex = (int ) (totalHits / PAGE_SIZE);
//        styleRow(selectedRow, false);
//        selectedRow = -1;
        this.eventBus.fireEvent(new PageChangedEvent(PageChangedEvent.PageChangeType.LAST));
    }

    public void onSelectionChange(SelectionChangeEvent event) {
        GeonameDto selectedObject = ((SingleSelectionModel<GeonameDto> ) event.getSource()).getSelectedObject();
        this.eventBus.fireEvent(new GeonameSelectedEvent(selectedObject));
    }

    /**
     * Initializes the table so that it contains enough rows for a full page of
     * emails. Also creates the images that will be used as 'read' flags.
     */
    private void initTable() {
        // Initialize the table.
        table.addColumn(geonameIdColumn, "GeonameId");
        table.addColumn(nameColumn, "Name");
        table.addColumn(countryCodeColumn, "CountryCode");
        table.addColumn(latituteColumn, "Latitude");
        table.addColumn(longitudeColumn, "Longitude");

        // Connect the list to the data provider.
        dataProvider.addDataDisplay(table);
        
        SelectionModel selectionModel = new SingleSelectionModel<GeonameDto>();
        selectionModel.addSelectionChangeHandler(this);
        table.setSelectionModel(selectionModel);
        
        //dataProvider.updateRowCount((int ) totalHits, true);
        
        pager.setRangeLimited(false);
        pager.setDisplay(table);

    }


    public void update() {
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    public ArrayList<GeonameDto> getCurrentList() {
        return currentList;
    }

    public void setCurrentList(ArrayList<GeonameDto> list) {
        this.currentList = list;
        this.dataProvider.updateRowData(0, this.currentList);
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageIndex() {
        return this.pageIndex;
    }

    public void setTotalHits(long totalHits) {
        this.totalHits = totalHits;
    }
    
    public AsyncDataProvider<GeonameDto> getDataProvider() {
        return this.dataProvider;
    }

    public void setAction(SearchAction action) {
        this.action = action;
    }

}
