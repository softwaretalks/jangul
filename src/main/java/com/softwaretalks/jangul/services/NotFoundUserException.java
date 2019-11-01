package com.softwaretalks.jangul.services;

public class NotFoundUserException extends Exception {
    public NotFoundUserException() {
        super("not found user");
    }
}
