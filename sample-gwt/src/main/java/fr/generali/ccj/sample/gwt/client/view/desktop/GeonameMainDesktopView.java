package fr.generali.ccj.sample.gwt.client.view.desktop;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import fr.generali.ccj.sample.gwt.client.mvp.AppActivityMapper;

@Singleton
public class GeonameMainDesktopView extends Composite {

    interface Binder extends UiBinder<Widget, GeonameMainDesktopView> {
    }

    private static final Binder binder = GWT.create(Binder.class);

    @UiField
    TopPanel topPanel;

    @UiField(provided = true)
    ShortcutsDesktopView shortcutsDesktopView;

    // @UiField(provided = true)
    @UiField
    SimpleLayoutPanel geonameMainContentDesktopView;

    @Inject
    public GeonameMainDesktopView(ShortcutsDesktopView shortcutsDesktopView,
                    AppActivityMapper mainContentActivityMapper, EventBus eventBus,
                    PlaceHistoryHandler placeHistoryHandler) {
        this.shortcutsDesktopView = shortcutsDesktopView;
        initWidget(binder.createAndBindUi(this));

        // Get rid of scrollbars, and clear out the window's built-in margin,
        // because we want to take advantage of the entire client area.
        Window.enableScrolling(false);
        Window.setMargin("0px");

        // Start ActivityManager for the main widget with our ActivityMapper
        ActivityManager leftActivityManager = new ActivityManager(mainContentActivityMapper, eventBus);
        leftActivityManager.setDisplay(geonameMainContentDesktopView);

        // Goes to the place represented on URL else default place
        placeHistoryHandler.handleCurrentHistory();
    }

}
