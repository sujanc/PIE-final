package com.stackroute.pie.exceptions;

public class PolicyAlreadyExistsException extends Exception{

    public PolicyAlreadyExistsException(String message){
        super(message);
    }

    public PolicyAlreadyExistsException() {
    }
}
