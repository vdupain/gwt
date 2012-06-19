package fr.generali.ccj.sample.gwt.client.view.desktop;

import java.util.ArrayList;

import net.customware.gwt.dispatch.client.DefaultExceptionHandler;
import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.dispatch.client.standard.StandardDispatchAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import fr.generali.ccj.sample.gwt.client.event.GeonameSelectedEvent;
import fr.generali.ccj.sample.gwt.client.event.PageChangedEvent;
import fr.generali.ccj.sample.gwt.client.view.GeonameListView;
import fr.generali.ccj.sample.gwt.shared.dto.GeonameDto;

@Singleton
public class GeonameListDesktopView extends ResizeComposite implements GeonameListView {

    private ArrayList<GeonameDto> currentList = new ArrayList<GeonameDto>();

    interface Binder extends UiBinder<Widget, GeonameListDesktopView> {
    }

    interface SelectionStyle extends CssResource {
        String selectedRow();

        String alternateRow();
    }

    private static final Binder binder = GWT.create(Binder.class);

    public static final int PAGE_SIZE = 20;

    @UiField
    FlexTable header;

    @UiField
    FlexTable table;

    @UiField
    SelectionStyle selectionStyle;

    private int pageIndex, selectedRow = -1;

    private NavBar navBar;

    private DispatchAsync dispatch;

    private Presenter presenter;

    private long totalHits = 0;

    private final EventBus eventBus;

    @Inject
    public GeonameListDesktopView(EventBus eventBus) {
        this.eventBus = eventBus;
        dispatch = new StandardDispatchAsync(new DefaultExceptionHandler());
        initWidget(binder.createAndBindUi(this));
        navBar = new NavBar(this);

        initTable();
    }

    @Override
    protected void onLoad() {
        // Select the first row if none is selected.
        if (selectedRow == -1) {
            selectRow(0);
        }
    }

    void newer() {
        // Move back a page.
        pageIndex -= PAGE_SIZE;
        if (pageIndex < 0) {
            pageIndex = 0;
        } else {
            styleRow(selectedRow, false);
            selectedRow = -1;
            this.eventBus.fireEvent(new PageChangedEvent(PageChangedEvent.PageChangeType.NEXT));
        }
    }

    void older() {
        // Move forward a page.
        pageIndex += PAGE_SIZE;
        if (pageIndex >= totalHits) {
            pageIndex -= PAGE_SIZE;
        } else {
            styleRow(selectedRow, false);
            selectedRow = -1;
            this.eventBus.fireEvent(new PageChangedEvent(PageChangedEvent.PageChangeType.PREVIOUS));
        }
    }

    void first() {
        pageIndex = 0;
        styleRow(selectedRow, false);
        selectedRow = -1;
        this.eventBus.fireEvent(new PageChangedEvent(PageChangedEvent.PageChangeType.FIRST));
    }

    void last() {
        pageIndex = (int ) (totalHits/PAGE_SIZE);
        styleRow(selectedRow, false);
        selectedRow = -1;
        this.eventBus.fireEvent(new PageChangedEvent(PageChangedEvent.PageChangeType.LAST));
    }

    @UiHandler("table")
    void onTableClicked(ClickEvent event) {
        // Select the row that was clicked (-1 to account for header row).
        Cell cell = table.getCellForEvent(event);
        if (cell != null) {
            int row = cell.getRowIndex();
            selectRow(row);
        }
    }

    /**
     * Initializes the table so that it contains enough rows for a full page of
     * emails. Also creates the images that will be used as 'read' flags.
     */
    private void initTable() {
        // Initialize the header.
        header.getColumnFormatter().setWidth(0, "80px");
        header.getColumnFormatter().setWidth(1, "80px");
        header.getColumnFormatter().setWidth(2, "80px");
        header.getColumnFormatter().setWidth(3, "80px");
        header.getColumnFormatter().setWidth(4, "80px");
        header.getColumnFormatter().setWidth(5, "80px");

        header.setText(0, 0, "GeonameId");
        header.setText(0, 1, "Name");
        header.setText(0, 2, "CountryCode");
        header.setText(0, 3, "Longitude");
        header.setText(0, 4, "Latitude");
        header.setWidget(0, 5, navBar);
        header.getCellFormatter().setHorizontalAlignment(0, 5, HasHorizontalAlignment.ALIGN_RIGHT);

        // Initialize the table.
        table.getColumnFormatter().setWidth(0, "80px");
        table.getColumnFormatter().setWidth(1, "80px");
        table.getColumnFormatter().setWidth(2, "80px");
        table.getColumnFormatter().setWidth(3, "80px");
        table.getColumnFormatter().setWidth(4, "80px");
        table.getColumnFormatter().setWidth(5, "80px");
    }

    /**
     * Selects the given row (relative to the current page).
     * 
     * @param row the row to be selected
     */
    private void selectRow(int row) {
        // When a row (other than the first one, which is used as a header) is
        // selected, display its associated iItem.
        GeonameDto item = null;
        if (row < currentList.size()) {
            item = currentList.get(row);
        }
        if (item == null) {
            return;
        }

        styleRow(selectedRow, false);
        styleRow(row, true);
        selectedRow = row;
        this.eventBus.fireEvent(new GeonameSelectedEvent(item));
    }

    private void styleRow(int row, boolean selected) {
        if (row != -1) {
            String style = selectionStyle.selectedRow();

            if (selected) {
                table.getRowFormatter().addStyleName(row, style);
            } else {
                table.getRowFormatter().removeStyleName(row, style);
            }
        }
    }

    public void update() {
        long max = pageIndex + PAGE_SIZE;
        if (max > totalHits) {
            max = totalHits;
        }
        // Update the nav bar.
        navBar.update(pageIndex, totalHits, max);
        int i = 0;
        for (GeonameDto geonameDto : currentList) {
            if (i % 2 != 0) {
                table.getRowFormatter().addStyleName(i, selectionStyle.alternateRow());
            }
            table.setText(i, 0, Integer.toString(geonameDto.getGeonameId()));
            table.setText(i, 1, geonameDto.getName());
            table.setText(i, 2, geonameDto.getCountryCode());
            table.setText(i, 3, Double.toString(geonameDto.getLongitude()));
            table.setText(i, 4, Double.toString(geonameDto.getLatitude()));
            i++;
        }
        // Clear any remaining slots.
        for (; i < PAGE_SIZE; ++i) {
            table.removeRow(table.getRowCount() - 1);
        }
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    public ArrayList<GeonameDto> getCurrentList() {
        return currentList;
    }

    public void setCurrentList(ArrayList<GeonameDto> list) {
        this.currentList = list;
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
}
