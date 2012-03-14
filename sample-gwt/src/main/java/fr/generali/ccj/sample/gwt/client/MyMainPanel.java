package fr.generali.ccj.sample.gwt.client;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.inject.Inject;

import fr.generali.ccj.sample.gwt.shared.dispatch.ErrorAction;
import fr.generali.ccj.sample.gwt.shared.dispatch.FooAction;
import fr.generali.ccj.sample.gwt.shared.dispatch.FooResult;
import fr.generali.ccj.sample.gwt.shared.dispatch.NoResult;
import fr.generali.ccj.sample.gwt.shared.dto.FooDto;

public class MyMainPanel extends Composite {

    // private final DispatchAsync dispatch = new MySecureDispatchAsync(new
    // DefaultExceptionHandler(),
    // new DispatchUrlBuilder(), new CookieSecureSessionAccessor("JSESSIONID"));

    @Inject
    public MyMainPanel(final DispatchAsync dispatch) {
        VerticalPanel all = new VerticalPanel();
        all.add(new HTML("<h1>Sample GWT </h1>"));
        final Label result1Label = new Label("No results yet");
        final Label result11Label = new Label("No results yet");
        final Label result12Label = new Label("No results yet");
        result1Label.addStyleName("Result");
        result11Label.addStyleName("Result");
        result12Label.addStyleName("Result");
        Button gwtDispatchButton = new Button("Appel via GWT-dispatch");
        gwtDispatchButton.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                result1Label.setText("Processing...");
                FooDto foo = new FooDto();
                foo.setProperty1("fuck" + com.google.gwt.user.client.Random.nextInt(100));
                dispatch.execute(new FooAction(foo), new AsyncCallback<FooResult>() {

                    public void onFailure(Throwable caught) {
                        result1Label.setText("Processing error : " + caught.getMessage());
                    }

                    public void onSuccess(FooResult result) {
                        result1Label.setText("Value is now " + result.getFooDto().getId() + " "
                                        + result.getFooDto().getProperty1());
                    }
                });

                dispatch.execute(new ErrorAction(true), new AsyncCallback<NoResult>() {

                    public void onFailure(Throwable caught) {
                        result11Label.setText("Processing error : " + caught.getMessage());
                    }

                    public void onSuccess(NoResult result) {
                        result11Label.setText("Processing result : " + result);
                    }
                });

                dispatch.execute(new ErrorAction(false), new AsyncCallback<NoResult>() {

                    public void onFailure(Throwable caught) {
                        result12Label.setText("Processing error : " + caught.getMessage());
                    }

                    public void onSuccess(NoResult result) {
                        result12Label.setText("Processing result : " + result);
                    }
                });

            }
        });

        FlowPanel panel = new FlowPanel();
        panel.add(gwtDispatchButton);
        panel.add(result1Label);
        panel.add(result11Label);
        panel.add(result12Label);
        all.add(panel);
        initWidget(all);
    }
}
