package com.zlp.dairy.design.dao;

public class CustomerException extends Exception{

    private static final long serialVersionUID = 7305852159998956456L;

    public CustomerException(){

    }

    public CustomerException(String message){
        super(message);
    }

    public CustomerException(String message, Throwable cause){
        super(message, cause);
    }
}
