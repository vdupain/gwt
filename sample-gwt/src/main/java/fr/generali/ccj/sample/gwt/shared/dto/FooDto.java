package fr.generali.ccj.sample.gwt.shared.dto;

import java.io.Serializable;

public class FooDto implements Serializable {
    private Long id;

    private String property1;

    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProperty1() {
        return property1;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

}
