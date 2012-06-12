package fr.generali.ccj.sample.gwt.client.view.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;


/**
 * A simple widget representing prev/next page navigation.
 */
class NavBar extends Composite {
    @UiTemplate("NavBar.ui.xml")
    interface Binder extends UiBinder<Widget, NavBar> {
    }

    private static final Binder binder = GWT.create(Binder.class);

    @UiField
    Element countLabel;

    @UiField
    Anchor newerButton;

    @UiField
    Anchor olderButton;

    private final GeonameListDesktopView outer;

    public NavBar(GeonameListDesktopView outer) {
        initWidget(binder.createAndBindUi(this));
        this.outer = outer;
    }

    public void update(long startIndex, long count, long max) {
        setVisibility(newerButton, startIndex != 0);
        setVisibility(olderButton, startIndex + GeonameListDesktopView.PAGE_SIZE < count);
        countLabel.setInnerText("" + (startIndex + 1) + " - " + max  + " of " + count);
    }

    @UiHandler("newerButton")
    void onNewerClicked(ClickEvent event) {
        outer.newer();
    }

    @UiHandler("olderButton")
    void onOlderClicked(ClickEvent event) {
        outer.older();
    }

    private void setVisibility(Widget widget, boolean visible) {
        widget.getElement().getStyle().setVisibility(visible ? Visibility.VISIBLE : Visibility.HIDDEN);
    }
}
