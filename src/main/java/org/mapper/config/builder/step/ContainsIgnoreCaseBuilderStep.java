package org.mapper.config.builder.step;

import static org.mapper.config.builder.type.BuilderType.CONTAINS_IGNORE_CASE;

public class ContainsIgnoreCaseBuilderStep extends BuilderStep {

    private String value;


    public ContainsIgnoreCaseBuilderStep(int index, String value) {
        super(index, CONTAINS_IGNORE_CASE);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
