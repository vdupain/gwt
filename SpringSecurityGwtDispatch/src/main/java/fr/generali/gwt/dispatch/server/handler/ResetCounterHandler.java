package fr.generali.gwt.dispatch.server.handler;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.generali.gwt.dispatch.server.service.CounterService;
import fr.generali.gwt.dispatch.shared.ResetCounter;

@Component
public class ResetCounterHandler implements ActionHandler<ResetCounter, Result> {

	private final CounterService counterService;

	@Autowired
	public ResetCounterHandler(CounterService counterService) {
		this.counterService = counterService; 
	}

    public Class<ResetCounter> getActionType() {
        return ResetCounter.class;
    }

    public Result execute(ResetCounter action, ExecutionContext context ) throws ActionException {
    	counterService.reset();
    	return null;
    }

    public void rollback(ResetCounter action, Result result, ExecutionContext context ) throws ActionException {

    }
}