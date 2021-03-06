package com.udemy.spring.springbasics.kelvin.scope;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.SimpleThreadScope;

import java.util.Objects;

public class BrowserScope extends SimpleThreadScope {

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Object o =  super.get(name, objectFactory);
        //If session ID is not null -->
        SessionId sessionId = ((RemoteWebDriver)o).getSessionId();

        //If session ID is null -->
        if(Objects.isNull(sessionId)){
            super.remove(name);
            o = super.get(name,objectFactory);
        }
        return o;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }
}
