package fr.generali.ccj.sample.gwt.server.dispatch;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import fr.generali.ccj.sample.gwt.server.domain.FooException;
import fr.generali.ccj.sample.gwt.server.service.DefaultFooService;
import fr.generali.ccj.sample.gwt.shared.dispatch.ErrorAction;
import fr.generali.ccj.sample.gwt.shared.dispatch.NoResult;

public class ErrorActionHandler implements ActionHandler<ErrorAction, NoResult>, ApplicationContextAware {

    private ApplicationContext applicationContext;

    public Class<ErrorAction> getActionType() {
        return ErrorAction.class;
    }

    public NoResult execute(ErrorAction action, ExecutionContext context) throws DispatchException {
        DefaultFooService service = applicationContext.getBean(DefaultFooService.class);
        if (action.isCheckedException()) {
            try {
                service.methodThatThrowsCheckedException();
            } catch (FooException ex) {
                ex.printStackTrace();
                throw new DispatchException(ex) {
                };
            }
        } else {
            service.methodThatThrowsUncheckedException();
        }
        return new NoResult();
    }

    public void rollback(ErrorAction action, NoResult result, ExecutionContext context) throws DispatchException {
        System.out.println("ErrorActionHandler.rollback()");
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
