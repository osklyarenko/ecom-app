package com.ncherkas.ecom.exception;

/**
 * Created by nazariycherkas on 12/1/14.
 */
public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(String msg, Object... args) {
        super(args != null && args.length > 0 ? String.format(msg, args) : msg);
    }
}
