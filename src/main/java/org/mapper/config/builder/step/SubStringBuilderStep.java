package org.mapper.config.builder.step;

import static org.mapper.config.builder.type.BuilderType.SUBSTRING;

public class SubStringBuilderStep extends BuilderStep {

    private int beginIndex;
    private int endIndex;


    public SubStringBuilderStep(int index, int beginIndex, int endIndex) {
        super(index, SUBSTRING);
        this.beginIndex = beginIndex;
        this.endIndex = endIndex;
    }

    public int getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(int beginIndex) {
        this.beginIndex = beginIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }
}
