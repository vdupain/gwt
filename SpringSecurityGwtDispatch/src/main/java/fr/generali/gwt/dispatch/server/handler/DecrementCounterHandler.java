package fr.generali.gwt.dispatch.server.handler;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.generali.gwt.dispatch.server.service.CounterService;
import fr.generali.gwt.dispatch.shared.DecrementCounter;
import fr.generali.gwt.dispatch.shared.IncrementCounterResult;

@Component
public class DecrementCounterHandler implements ActionHandler<DecrementCounter, IncrementCounterResult> {

	private final CounterService counterService;

	@Autowired
	public DecrementCounterHandler(CounterService counterService) {
		this.counterService = counterService; 
	}

	@Override
    public IncrementCounterResult execute(DecrementCounter action, ExecutionContext context) throws ActionException {
		return new IncrementCounterResult(1, counterService.decrement(1) );
    }
	
	@Override
    public void rollback( DecrementCounter action, IncrementCounterResult result, ExecutionContext context ) throws ActionException {
    	counterService.increment(1);
    }
    
    public Class<DecrementCounter> getActionType() {
        return DecrementCounter.class;
    }
}