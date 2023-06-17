package org.mapper.builder;


import org.mapper.Helper;

import java.util.Map;

public class PredicateMapper extends Conditional {

    private Map<String, Object> inputMap;
    private String inputPath;

    private Object value;

    public PredicateMapper(Map<String, Object> inputMap, String inputPath) {
        this.value = Helper.getValue(inputMap, inputPath);

        this.inputMap = inputMap;
        this.inputPath = inputPath;
    }

    public boolean isEqualTo(Object equals) {
        return isEqualTo(this.value, equals);
    }

    public boolean contains(String contains) {
        return contains(this.value, contains);
    }

    public boolean notNull() {
        return notNull(this.value);
    }
}
