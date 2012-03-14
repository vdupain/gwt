package fr.generali.ccj.sample.gwt.server.service;

import java.io.FileNotFoundException;
import java.util.List;

import javax.annotation.security.DenyAll;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import fr.generali.ccj.sample.gwt.server.domain.Foo;
import fr.generali.ccj.sample.gwt.server.domain.FooException;
import fr.generali.ccj.sample.gwt.server.domain.FooRuntimeException;

@Service
public class DefaultFooService implements IFooService {

    @PersistenceContext
    private EntityManager em;

    public List<Foo> findAllFoos() {
        try {
            return em.createQuery("select o from Foo o").getResultList();
        } finally {
            em.close();
        }
    }

    public Foo findFoo(Long id) {
        Assert.notNull(id);
        try {
            return em.find(Foo.class, id);
        } finally {
            em.close();
        }
    }

    @Secured("ROLE_FOO")
    public Foo persist(Foo foo) {
        Assert.notNull(foo);
        try {
            return em.merge(foo);
        } finally {
            em.close();
        }
    }

    public void remove(Long id) {
        Assert.notNull(id);
        try {
            Foo attached = em.find(Foo.class, id);
            em.remove(attached);
        } finally {
            em.close();
        }
    }

    @Secured("ROLE_FOO")
    public void methodThatThrowsUncheckedException() {
        System.out.println("DefaultFooService.methodThatThrowsUncheckedException()");
        throw new FooRuntimeException("Test remontée d'exception unchecked");
    }

    // @RolesAllowed("ROLE_ERROR")
    @DenyAll
    public void methodThatThrowsCheckedException() throws FooException {
        System.out.println("DefaultFooService.methodThatThrowsCheckedException()");
        throw new FooException(new FileNotFoundException("Test remontée d'exception checked"));
    }

}
