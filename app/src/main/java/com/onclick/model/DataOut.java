package com.onclick.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Holds data coming back from a call to Background worker.
 *
 * If the call failed then this class holds error information.
 *
 * Multiple objects may be passed back so they are held in a collection in this object.
 *
 * If the call failed then returnedData will be empty or null and there will be an
 * errorMessage or error object (or both).
 *
 */

public class DataOut {

    private Map<String, Object> containedData = new HashMap<String, Object>();

    private String errorMessage;
    private Exception exception;

    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public Exception getException() {
        return exception;
    }
    public void setException(Exception exception) {
        this.exception = exception;
    }

    public void put(String key, Object value) {
        containedData.put(key, value);
    }

    public <T> T get(String key) {
        return (T)containedData.get(key);
    }

}