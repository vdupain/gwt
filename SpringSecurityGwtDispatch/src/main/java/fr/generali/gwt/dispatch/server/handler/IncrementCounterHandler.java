package fr.generali.gwt.dispatch.server.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;
import fr.generali.gwt.dispatch.server.service.CounterService;
import fr.generali.gwt.dispatch.shared.IncrementCounter;
import fr.generali.gwt.dispatch.shared.IncrementCounterResult;

@Component
public class IncrementCounterHandler implements ActionHandler<IncrementCounter, IncrementCounterResult> {
	
	private final CounterService counterService;

	@Autowired
	public IncrementCounterHandler(CounterService counterService) {
		this.counterService = counterService; 
	}

	public Class<IncrementCounter> getActionType() {
		return IncrementCounter.class;
	}

	public IncrementCounterResult execute( IncrementCounter action, ExecutionContext context ) throws ActionException {
		int amount = action.getAmount();
		return new IncrementCounterResult(amount, counterService.increment(amount) );
	}

	public void rollback(IncrementCounter action, IncrementCounterResult result, ExecutionContext context ) throws ActionException {
		counterService.decrement(result.getAmount());
	}
}