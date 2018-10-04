package com.params_manager;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.digester3.xmlrules.FromXmlRulesModule;

public class MyRulesModule extends FromXmlRulesModule {

    File rules_file = null;
    
    public MyRulesModule() {}
        
    public MyRulesModule(File rules_file) {
	super();
	this.rules_file = rules_file;
    }

    @Override
    protected void loadRules() {
	if(rules_file != null ){
	    if(rules_file.exists()){
		loadXMLRules(rules_file);
	    }else{
		System.err.println("File not found "+ rules_file.getAbsolutePath());
	    }	    
	}else{	    
	    InputStream in = getClass().getResourceAsStream("/digester-rules.xml");
	    loadXMLRules(in);
	}
    }
}
