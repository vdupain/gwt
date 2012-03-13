package fr.generali.ccj.sample.gwt.server.dispatch;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.generali.ccj.sample.gwt.server.domain.Foo;
import fr.generali.ccj.sample.gwt.server.domain.FooConverterHelper;
import fr.generali.ccj.sample.gwt.server.service.IFooService;
import fr.generali.ccj.sample.gwt.shared.dispatch.FooAction;
import fr.generali.ccj.sample.gwt.shared.dispatch.FooResult;
import fr.generali.ccj.sample.gwt.shared.dto.FooDto;

@Component
public class FooActionHandler implements ActionHandler<FooAction, FooResult> {

    @Autowired
    private IFooService fooService;

    public Class<FooAction> getActionType() {
        return FooAction.class;
    }

    public FooResult execute(FooAction action, ExecutionContext context) throws DispatchException {
        FooDto fooDto = action.getFooDto();
        Foo foo = FooConverterHelper.asEntity(fooDto);
        fooService.persist(foo);
        return new FooResult(FooConverterHelper.asDto(foo));
    }

    public void rollback(FooAction action, FooResult result, ExecutionContext context) throws DispatchException {
    }

}
