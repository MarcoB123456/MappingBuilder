package org.mapper.builder;

public abstract class Conditional {

    protected boolean notNull(Object input) {
        return input != null;
    }

    protected boolean contains(Object value, String contains) {
        return String.valueOf(value).contains(contains);
    }

    protected boolean containsIgnoreCase(Object value, String contains) {
        return String.valueOf(value).toLowerCase().contains(contains.toLowerCase());
    }

    protected boolean isEqualTo(Object value, Object equals) {
        return value.equals(equals);
    }

    protected boolean isEqualToIgnoreCase(Object value, String equals) {
        return String.valueOf(value).equalsIgnoreCase(equals);
    }

    protected boolean isType(Object value, Class clazz) {
        return clazz.isInstance(value);
    }
}
