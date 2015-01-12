package com.onclick.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ronald T on 12/27/2014.
 */
public class DataIn {

    private Map<String, Object> containedData = new HashMap<String, Object>();

    public void put(String key, Object value) {
        containedData.put(key, value);
    }

    public <T> T get(String key) {
        return (T)containedData.get(key);
    }

}
