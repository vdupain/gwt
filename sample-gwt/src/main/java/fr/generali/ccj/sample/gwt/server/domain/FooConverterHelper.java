package fr.generali.ccj.sample.gwt.server.domain;

import fr.generali.ccj.sample.gwt.shared.dto.FooDto;

public class FooConverterHelper {

    public static FooDto asDto(Foo foo) {
        FooDto fooDto = new FooDto();
        fooDto.setId(foo.getId());
        fooDto.setVersion(foo.getVersion());
        fooDto.setProperty1(foo.getProperty1());
        return fooDto;
    }

    public static Foo asEntity(FooDto fooDto) {
        Foo foo = new Foo();
        foo.setId(fooDto.getId());
        foo.setVersion(fooDto.getVersion());
        foo.setProperty1(fooDto.getProperty1());
        return foo;
    }

}
