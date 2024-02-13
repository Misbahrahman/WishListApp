package com.example.wishlistApp.exception;

//Just Created One Custom Exception to be able to modify and throw messages Accordingly
public class CustomException extends RuntimeException{
    public CustomException(String message){
        super(message);
    }

}
