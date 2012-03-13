package fr.generali.ccj.sample.gwt.server.domain;

public class FooRuntimeException extends RuntimeException {

    public FooRuntimeException() {
    }

    public FooRuntimeException(String message) {
        super(message);
    }

    public FooRuntimeException(Throwable throwable) {
        super(throwable);
    }

}
