package org.mapper.config.builder.step;

import static org.mapper.config.builder.type.BuilderType.CONTAINS;

public class ContainsBuilderStep extends BuilderStep {

    private String value;


    public ContainsBuilderStep(int index, String value) {
        super(index, CONTAINS);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
