package fr.generali.ccj.sample.gwt.server.dispatch;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.generali.ccj.sample.gwt.server.domain.FooException;
import fr.generali.ccj.sample.gwt.server.service.IFooService;
import fr.generali.ccj.sample.gwt.shared.dispatch.ErrorAction;
import fr.generali.ccj.sample.gwt.shared.dispatch.NoResult;

@Component
public class ErrorActionHandler implements ActionHandler<ErrorAction, NoResult> {

    @Autowired
    private IFooService fooService;

    public Class<ErrorAction> getActionType() {
        return ErrorAction.class;
    }

    public NoResult execute(ErrorAction action, ExecutionContext context) throws DispatchException {
        if (action.isCheckedException()) {
            try {
                fooService.methodThatThrowsCheckedException();
            } catch (FooException ex) {
                ex.printStackTrace();
                throw new DispatchException(ex) {
                };
            }
        } else {
            fooService.methodThatThrowsUncheckedException();
        }
        return new NoResult();
    }

    public void rollback(ErrorAction action, NoResult result, ExecutionContext context) throws DispatchException {
        System.out.println("ErrorActionHandler.rollback()");
    }

}
