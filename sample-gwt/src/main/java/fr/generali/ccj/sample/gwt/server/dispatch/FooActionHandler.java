package fr.generali.ccj.sample.gwt.server.dispatch;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import fr.generali.ccj.sample.gwt.server.domain.Foo;
import fr.generali.ccj.sample.gwt.server.domain.FooConverterHelper;
import fr.generali.ccj.sample.gwt.server.service.DefaultFooService;
import fr.generali.ccj.sample.gwt.shared.dispatch.FooAction;
import fr.generali.ccj.sample.gwt.shared.dispatch.FooResult;
import fr.generali.ccj.sample.gwt.shared.dto.FooDto;

public class FooActionHandler implements ActionHandler<FooAction, FooResult>, ApplicationContextAware {

    private ApplicationContext applicationContext;

    public Class<FooAction> getActionType() {
        return FooAction.class;
    }

    public FooResult execute(FooAction action, ExecutionContext context) throws DispatchException {
        FooDto fooDto = action.getFooDto();
        DefaultFooService service = applicationContext.getBean(DefaultFooService.class);
        Foo foo = FooConverterHelper.asEntity(fooDto);
        service.persist(foo);
        return new FooResult(FooConverterHelper.asDto(foo));
    }

    public void rollback(FooAction action, FooResult result, ExecutionContext context) throws DispatchException {
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
