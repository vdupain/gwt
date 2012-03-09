package fr.generali.gwt.dispatch.client;

import net.customware.gwt.dispatch.client.DefaultExceptionHandler;
import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.dispatch.client.secure.CookieSecureSessionAccessor;
import net.customware.gwt.dispatch.shared.Result;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.StatusCodeException;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.generali.gwt.dispatch.client.fwk.DefaultDispatchUrlBuilder;
import fr.generali.gwt.dispatch.client.fwk.GeneraliSecureDispatchAsync;
import fr.generali.gwt.dispatch.shared.DecrementCounter;
import fr.generali.gwt.dispatch.shared.IncrementCounter;
import fr.generali.gwt.dispatch.shared.IncrementCounterResult;
import fr.generali.gwt.dispatch.shared.ResetCounter;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SSGDEntryPoint implements EntryPoint {

//	private final DispatchAsync dispatch = new GeneraliDispatchAsync(new DefaultExceptionHandler(), new DefaultDispatchUrlBuilder());
	
	private final DispatchAsync dispatch = new GeneraliSecureDispatchAsync(new DefaultExceptionHandler(), new DefaultDispatchUrlBuilder(), new CookieSecureSessionAccessor("JSESSIONID"));

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		VerticalPanel all = new VerticalPanel();

		all.add(new HTML("<h1>Démo GWT Dispatch + Spring Security</h1>"));

		final Label resultLabel = new Label("No results yet");
		
		resultLabel.addStyleName("counterResult");

		Button incrementButton = new Button("+");
		Button decrementButton = new Button("-");
		Button resetButton = new Button("Reset");
		
		incrementButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dispatch.execute(new IncrementCounter(1), new CallBack(resultLabel));
			}
		});
		
		decrementButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				dispatch.execute(new DecrementCounter(), new CallBack(resultLabel));
				
			}
		});
		
		resetButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {

				dispatch.execute(new ResetCounter(), new AsyncCallback<Result>() {

					@Override
					public void onFailure(Throwable caught) {
						
						if (caught instanceof StatusCodeException){
							if (((StatusCodeException)caught).getStatusCode() == 403){
								Window.alert("Vous n'êtes pas autorisé à appeler cette ressource.");
							}
						}

					}

					@Override
					public void onSuccess(Result result) {	
						resultLabel.setText("Counter value is now 0 .");
					}
				});
				
			}
		});
		

		FlowPanel panel = new FlowPanel();

		panel.add(incrementButton);
		panel.add(decrementButton);
		panel.add(resetButton);
		panel.add(resultLabel);

		all.add(panel);

		RootPanel.get().add(all);
	}
	
	private final class CallBack implements AsyncCallback<IncrementCounterResult> {
		private final Label resultLabel;

		private CallBack(Label resultLabel) {
			this.resultLabel = resultLabel;
		}

		public void onFailure(Throwable caught) {
			resultLabel.setText("Processing error : " + caught.getMessage() + " .");
		}

		public void onSuccess(IncrementCounterResult result) {
			resultLabel.setText("Counter value is now " + result.getCurrent() + " .");

		}
	}
}
