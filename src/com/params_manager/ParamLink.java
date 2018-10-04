package com.params_manager;

public class ParamLink extends Param {

    String block_name;
    
    public ParamLink() {}

    public String getBlock_name() {
        return block_name;
    }

    public void setParam_name(String param_name) {
        super.setName(param_name);
    }
    
    public void setBlock_name(String block_name) {
        this.block_name = block_name;
    }
}
