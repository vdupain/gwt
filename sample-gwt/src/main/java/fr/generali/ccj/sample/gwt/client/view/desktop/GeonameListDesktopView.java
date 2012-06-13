package fr.generali.ccj.sample.gwt.client.view.desktop;

import java.util.ArrayList;

import net.customware.gwt.dispatch.client.DefaultExceptionHandler;
import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.dispatch.client.standard.StandardDispatchAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;

import fr.generali.ccj.sample.gwt.client.view.GeonameListView;
import fr.generali.ccj.sample.gwt.shared.dispatch.GeonameListAction;
import fr.generali.ccj.sample.gwt.shared.dispatch.GeonameListResult;
import fr.generali.ccj.sample.gwt.shared.dto.GeonameDto;

public class GeonameListDesktopView extends ResizeComposite implements GeonameListView, AcceptsOneWidget {

    private ArrayList<GeonameDto> currentResult = new ArrayList<GeonameDto>();

    /**
     * Callback when items are selected.
     */
    public interface Listener {
        void onItemSelected(GeonameDto item);
    }

    interface Binder extends UiBinder<Widget, GeonameListDesktopView> {
    }

    interface SelectionStyle extends CssResource {
        String selectedRow();

        String alternateRow();
    }

    private static final Binder binder = GWT.create(Binder.class);

    static final int PAGE_SIZE = 20;

    @UiField
    FlexTable header;

    @UiField
    FlexTable table;

    @UiField
    SelectionStyle selectionStyle;

    private Listener listener;

    private int startIndex, selectedRow = -1;

    private NavBar navBar;

    private DispatchAsync dispatch;

    private Presenter presenter;

    private long totalHits;

    public GeonameListDesktopView() {
        dispatch = new StandardDispatchAsync(new DefaultExceptionHandler());
        initWidget(binder.createAndBindUi(this));
        navBar = new NavBar(this);

        initTable();
        update();
    }

    /**
     * Sets the listener that will be notified when an item is selected.
     */

    public void setListener(Listener listener) {
        this.listener = listener;
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
        startIndex -= PAGE_SIZE;
        if (startIndex < 0) {
            startIndex = 0;
        } else {
            styleRow(selectedRow, false);
            selectedRow = -1;
            update();
        }
    }

    void older() {
        // Move forward a page.
        startIndex += PAGE_SIZE;
        if (startIndex >= totalHits) {
            startIndex -= PAGE_SIZE;
        } else {
            styleRow(selectedRow, false);
            selectedRow = -1;
            update();
        }
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
        header.getColumnFormatter().setWidth(4, "80px");

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
        if (row < currentResult.size()) {
            item = currentResult.get(row);
        }
        if (item == null) {
            return;
        }

        styleRow(selectedRow, false);
        styleRow(row, true);

        selectedRow = row;

        if (listener != null) {
            listener.onItemSelected(item);
        }

        // presenter.onGeonameSelected(new GeonameSelectedEvent(item));
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

    private void update() {
        AsyncCallback<GeonameListResult> callback = new AsyncCallback<GeonameListResult>() {

            public void onSuccess(GeonameListResult result) {
                // Update the older/newer buttons & label.
                totalHits = result.getTotalHits();
                long max = startIndex + PAGE_SIZE;
                if (max > totalHits) {
                    max = totalHits;
                }

                // Update the nav bar.
                navBar.update(startIndex, totalHits, max);

                int i = 0;
                currentResult = result.getList();
                for (GeonameDto geonameDto : currentResult) {
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

            public void onFailure(Throwable caught) {
                Window.alert(caught.toString());
            }
        };
        dispatch.execute(new GeonameListAction(startIndex, PAGE_SIZE), callback);
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    public void setWidget(IsWidget w) {
    }
}
