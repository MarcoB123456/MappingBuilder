package org.mapper.config.builder.step;

import org.mapper.config.builder.type.BuilderType;

import java.util.Comparator;

public class BuilderStep {

    private int index;

    private BuilderType type;

    public BuilderStep(int index, BuilderType type) {
        this.index = index;
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public BuilderType getType() {
        return type;
    }

    public void setType(BuilderType type) {
        this.type = type;
    }

    public static class StepComparator implements Comparator<BuilderStep> {

        @Override
        public int compare(BuilderStep step1, BuilderStep step2) {
            return step1.getIndex() - step2.getIndex();
        }
    }
}
