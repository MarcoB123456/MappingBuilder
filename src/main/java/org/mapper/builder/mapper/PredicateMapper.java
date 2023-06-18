package org.mapper.builder.mapper;


import org.mapper.builder.helper.ConditionalHelper;

public class PredicateMapper {

    private Object value;

    public PredicateMapper(Object value) {
        this.value = value;
    }

    public boolean isEqualTo(Object equals) {
        return ConditionalHelper.isEqualTo(this.value, equals);
    }

    public boolean contains(String contains) {
        return ConditionalHelper.contains(this.value, contains);
    }

    public boolean notNull() {
        return ConditionalHelper.isNotNull(this.value);
    }
}
