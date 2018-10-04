package com.params_manager;

import org.apache.commons.digester3.binder.AbstractRulesModule;
import org.apache.commons.digester3.binder.RulesModule;

public class UserRuleModule extends AbstractRulesModule implements RulesModule {
    
    public UserRuleModule() {
    }

    @Override
    protected void configure() {
	forPattern("User").createObject().ofType(User.class).then().setNext("setValue").then().setProperties();
    }

}
