package com.params_manager;

public class ParamNotFoundException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ParamNotFoundException() {
	
    }

    public ParamNotFoundException(String message) {
	super(message);
    }

    public ParamNotFoundException(Throwable cause) {
	super(cause);
    }

    public ParamNotFoundException(String message, Throwable cause) {
	super(message, cause);
    }

    public ParamNotFoundException(String message, Throwable cause,
	    boolean enableSuppression, boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);

    }
}