package com.params_manager;

import java.util.ArrayList;

public class Param {

    String name="";	//used for link creation
    String alias="";	//used within block only, unic value within block
    ArrayList<Object> values;
    String comment;
    
    public Param() {
	values = new ArrayList<Object>();	
    }
      
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getComment() {
        return comment.equals("")?"no comments for param":comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    /***
     * возвращает первое значение из списка
     * @return
     */
    public Object getValue() {
        return this.values.get(0);
    }
    
    public Object getValue(int index) {
        return this.values.get(index);
    }
    
    public  ArrayList<Object> getValues() {
        return this.values;
    }

    public void setValue(Object value) {	
        this.values.add(value);
    } 
}
