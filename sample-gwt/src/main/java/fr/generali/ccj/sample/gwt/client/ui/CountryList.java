package fr.generali.ccj.sample.gwt.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;

public class CountryList extends ResizeComposite {

    /**
     * Callback when items are selected.
     */
    public interface Listener {
        void onItemSelected(Geoname item);
    }

    interface Binder extends UiBinder<Widget, CountryList> {
    }

    interface SelectionStyle extends CssResource {
        String selectedRow();
    }

    private static final Binder binder = GWT.create(Binder.class);

    static final int VISIBLE_COUNTRY_COUNT = 20;

    @UiField
    FlexTable header;

    @UiField
    FlexTable table;

    @UiField
    SelectionStyle selectionStyle;

    private Listener listener;

    private int startIndex, selectedRow = -1;

    private NavBar navBar;

    public CountryList() {
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
        startIndex -= VISIBLE_COUNTRY_COUNT;
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
        startIndex += VISIBLE_COUNTRY_COUNT;
        if (startIndex >= CountryItems.getGeonameCount()) {
            startIndex -= VISIBLE_COUNTRY_COUNT;
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

        header.setText(0, 0, "Name");
        header.setText(0, 1, "Asciiname");
        header.setText(0, 2, "CountryCode");
        header.setText(0, 3, "Longitude");
        header.setText(0, 4, "Latitude");
        header.setWidget(0, 5, navBar);
        // header.getCellFormatter().setHorizontalAlignment(0, 3,
        // HasHorizontalAlignment.ALIGN_RIGHT);

        // Initialize the table.
        table.getColumnFormatter().setWidth(0, "80px");
        table.getColumnFormatter().setWidth(1, "80px");
        table.getColumnFormatter().setWidth(2, "80px");
        table.getColumnFormatter().setWidth(3, "80px");
        table.getColumnFormatter().setWidth(4, "80px");
    }

    /**
     * Selects the given row (relative to the current page).
     * 
     * @param row the row to be selected
     */
    private void selectRow(int row) {
        // When a row (other than the first one, which is used as a header) is
        // selected, display its associated MailItem.
        Geoname item = CountryItems.getGeoname(startIndex + row);
        if (item == null) {
            return;
        }

        styleRow(selectedRow, false);
        styleRow(row, true);

        selectedRow = row;

        if (listener != null) {
            listener.onItemSelected(item);
        }
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
        // Update the older/newer buttons & label.
        int count = CountryItems.getGeonameCount();
        int max = startIndex + VISIBLE_COUNTRY_COUNT;
        if (max > count) {
            max = count;
        }

        // Update the nav bar.
        navBar.update(startIndex, count, max);

        // Show the selected emails.
        int i = 0;
        for (; i < VISIBLE_COUNTRY_COUNT; ++i) {
            // Don't read past the end.
            if (startIndex + i >= CountryItems.getGeonameCount()) {
                break;
            }

            Geoname item = CountryItems.getGeoname(startIndex + i);

            // Add a new row to the table, then set each of its columns to the
            // email's sender and subject values.
            table.setText(i, 0, item.getName());
            table.setText(i, 1, item.getAsciiname());
            table.setText(i, 2, item.getCountryCode());
            table.setText(i, 3, Double.toString(item.getLongitude()));
            table.setText(i, 4, Double.toString(item.getLatitude()));
        }

        // Clear any remaining slots.
        for (; i < VISIBLE_COUNTRY_COUNT; ++i) {
            table.removeRow(table.getRowCount() - 1);
        }
    }
}
