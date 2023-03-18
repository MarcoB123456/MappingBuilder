package org.mapper.config.builder.step;

import static org.mapper.config.builder.type.BuilderType.NOTNULL;

public class NotNullBuilderStep extends BuilderStep {

    public NotNullBuilderStep(int index) {
        super(index, NOTNULL);
    }


}
