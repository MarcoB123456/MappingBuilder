package org.mapper.builder.helper;

public class ConditionalHelper {

    public static boolean isNotNull(Object input) {
        return input != null;
    }

    public static boolean contains(Object value, String contains) {
        return String.valueOf(value).contains(contains);
    }

    public static boolean containsIgnoreCase(Object value, String contains) {
        return String.valueOf(value).toLowerCase().contains(contains.toLowerCase());
    }

    public static boolean isEqualTo(Object value, Object equals) {
        return value.equals(equals);
    }

    public static boolean isEqualToIgnoreCase(Object value, String equals) {
        return String.valueOf(value).equalsIgnoreCase(equals);
    }

    public static boolean isType(Object value, Class clazz) {
        return clazz.isInstance(value);
    }
}
