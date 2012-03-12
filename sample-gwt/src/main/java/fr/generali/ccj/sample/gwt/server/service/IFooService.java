package fr.generali.ccj.sample.gwt.server.service;

import java.util.List;

import fr.generali.ccj.sample.gwt.server.domain.Foo;
import fr.generali.ccj.sample.gwt.server.domain.FooException;

public interface IFooService {

    List<Foo> findAllFoos();

    Foo findFoo(Long id);

    Foo persist(Foo foo);

    void remove(Long id);

    void methodThatThrowsUncheckedException();

    void methodThatThrowsCheckedException() throws FooException;

}
