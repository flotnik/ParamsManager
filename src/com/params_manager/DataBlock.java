package com.params_manager;

import java.util.ArrayList;

public class DataBlock {

    String name;
    String comment;
    ArrayList<Param> params;
    ArrayList<ParamLink> param_links;

    public DataBlock() {
	params = new ArrayList<>();
	param_links = new ArrayList<>();
    }

    public void AddParam(Param param) throws Exception {
	isParamNameBusy(param.getName());
	isParamAliasBusy(param.getAlias());
	params.add(param);
    }
    
    private void isParamNameBusy(String param_name) throws Exception{	
	String exception_msg = "You trying to add another param with the same name " + param_name + " in block " + name;
	for(Param p:params){
	    if(p.getName().equals(param_name)){		
		throw new Exception(exception_msg);
	    }
	}
	for(ParamLink p:param_links){
	    if(p.getName().equals(param_name)){
		throw new Exception(exception_msg);
	    }
	}	
    }
    
    private void isParamAliasBusy(String param_alias) throws Exception{	
	String exception_msg = "You trying to add another param with the same alias " + param_alias + " in block " + name;
	for(Param p:params){
	    if(!p.getAlias().isEmpty() && p.getAlias().equals(param_alias)){		
		throw new Exception(exception_msg);
	    }
	}
	for(ParamLink p:param_links){
	    if(!p.getAlias().isEmpty() && p.getAlias().equals(param_alias)){
		throw new Exception(exception_msg);
	    }
	}	
    }
    
    public void AddParamLink(ParamLink link) throws Exception {
	isParamNameBusy(link.getName());
	param_links.add(link);
    }
    
    public String getString(String param_name) throws Exception {
	return (String) (findParam(param_name).getValue());
    }
    
    public Double getDouble(String param_name) throws Exception {
	return (Double) (findParam(param_name).getValue());
    }
    
    public Integer getInteger(String param_name) throws Exception {
	return (Integer) (findParam(param_name).getValue());
    }
    
    public Object getParam(String param_name) throws Exception {
	return findParam(param_name).getValue();
    }
    
    public Param findParam(String name_or_alias) throws ParamNotFoundException{
	try{
	    return findParamByAlias(name_or_alias);//try to find by alias
	}catch(ParamNotFoundException e){
	    return findParamByname(name_or_alias);//try to find by name, if not than Exception
	}
    }
    
    public Param findParamByname(String param_name) throws ParamNotFoundException {
	for (Param p : params) {
	    if (p.getName().equals(param_name)) {
		return p;
	    }
	}
	for (ParamLink p : param_links) {
	    if (p.getName().equals(param_name)) {
		return p;
	    }
	}
	throw new ParamNotFoundException("Param with name " + param_name + " wasn't found");
    }
    
    public Param findParamByAlias(String alias) throws ParamNotFoundException {
	for (Param p : params) {
	    if (p.getAlias().equals(alias)) {
		return p;
	    }
	}
	for (ParamLink p : param_links) {
	    if (p.getAlias().equals(alias)) {
		return p;
	    }
	}
	throw new ParamNotFoundException("Param with name alias=" + alias + " wasn't found");
    }

    // ==========================================
    
    public ArrayList<Param> getParams() {
	return params;
    }
    
    public String getName() {
	return name;
    }
        
    public void setName(String block_name) {
	this.name = block_name;
    }
        
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setParams(ArrayList<Param> params) {
	this.params = params;
    }
    
    public ArrayList<ParamLink> getParam_links() {
        return param_links;
    }
    
    public void setParam_links(ArrayList<ParamLink> param_links) {
        this.param_links = param_links;
    }
}
