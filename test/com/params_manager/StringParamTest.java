package com.params_manager;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

public class StringParamTest {

    @Test
    public void testParse() throws Exception {
	DataBlocks db = new DataBlocks();
	File f = new File("test_data.xml");
	if(!f.exists())throw new FileNotFoundException();
	db.loadParams(f, null);
		
	for (DataBlock d : db.getBlocks()) {
	    System.out.println(d.getName() + " params");
	    for (Param p : d.getParams()) {
		System.out.println(p.getName());
	    }
	    System.out.println();
	    for (ParamLink p : d.getParam_links()) {
		System.out.println(p.getBlock_name()+" " +p.getName());
	    }
	    System.out.println();
	}	
	
	System.out.println(db.GetBlock("data").getString("p6"));
	System.out.println(((User)db.GetBlock("data").getParam("p3")).getLogin());
	System.out.println(((User)db.GetBlock("data2").getParam("p3")).getLogin());
	System.out.println(db.GetBlock("data").getString("p1"));
	System.out.println(((User)db.GetBlock("data2").getParam("alias")).getLogin());
    }
}
