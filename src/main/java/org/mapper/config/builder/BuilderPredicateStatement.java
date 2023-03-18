package org.mapper.config.builder;

import org.mapper.config.builder.type.BuilderType;

import java.util.Comparator;

public class BuilderPredicateStatement {

    private int index;

    private BuilderType type;

    private String inputPath;

    private Object value;

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

    public String getInputPath() {
        return inputPath;
    }

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public static class StatementComparator implements Comparator<BuilderPredicateStatement> {

        @Override
        public int compare(BuilderPredicateStatement statement1, BuilderPredicateStatement statement2) {
            return statement1.getIndex() - statement2.getIndex();
        }
    }
}
