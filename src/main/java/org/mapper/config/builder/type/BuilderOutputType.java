package org.mapper.config.builder.type;

public enum BuilderOutputType {

    STRING, INTEGER, BOOLEAN, NULL;

    public static BuilderOutputType byName(String name) {
        if (name == null) {
            return NULL;
        } else {
            return BuilderOutputType.valueOf(name);
        }
    }

}
