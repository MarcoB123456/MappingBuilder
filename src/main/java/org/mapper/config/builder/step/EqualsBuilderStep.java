package org.mapper.config.builder.step;

import static org.mapper.config.builder.type.BuilderType.EQUALS;

public class EqualsBuilderStep extends BuilderStep {

    private Object value;

    public EqualsBuilderStep(int index, Object value) {
        super(index, EQUALS);
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
