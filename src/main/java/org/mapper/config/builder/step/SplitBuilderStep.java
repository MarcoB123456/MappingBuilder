package org.mapper.config.builder.step;

import static org.mapper.config.builder.type.BuilderType.SPLIT;

public class SplitBuilderStep extends BuilderStep {

    private String splitPattern;


    public SplitBuilderStep(int index, String splitPattern) {
        super(index, SPLIT);
        this.splitPattern = splitPattern;
    }

    public String getSplitPattern() {
        return splitPattern;
    }

    public void setSplitPattern(String splitPattern) {
        this.splitPattern = splitPattern;
    }
}
