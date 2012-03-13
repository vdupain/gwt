package fr.generali.ccj.sample.gwt.server.domain;

public class FooException extends Exception {

    public FooException() {
    }

    public FooException(String message) {
        super(message);
    }

    public FooException(Exception exception) {
        super(exception);
    }

}
