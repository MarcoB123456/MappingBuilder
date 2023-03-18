package org.mapper.config.builder.step;

import static org.mapper.config.builder.type.BuilderType.EQUALS_IGNORE_CASE;

public class EqualsIgnoreCaseBuilderStep extends BuilderStep {

    private Object value;

    public EqualsIgnoreCaseBuilderStep(int index, Object value) {
        super(index, EQUALS_IGNORE_CASE);
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
