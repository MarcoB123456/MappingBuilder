package org.mapper.config.builder.step;

import org.mapper.config.builder.type.BuilderOutputType;

import java.util.Objects;

import static org.mapper.config.builder.type.BuilderType.INSERT;

public class InsertBuilderStep extends BuilderStep {

    private String outputPath;

    private BuilderOutputType outputType;

    private Integer splitIndex;
    private boolean constant;
    private Object value;


    public InsertBuilderStep(int index, String outputPath, BuilderOutputType outputType, Integer splitIndex, Boolean constant, Object value) {
        super(index, INSERT);
        this.outputPath = outputPath;
        this.outputType = outputType;
        this.splitIndex = splitIndex;
        this.constant = Objects.requireNonNullElse(constant, false);
        this.value = value;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public BuilderOutputType getOutputType() {
        return outputType;
    }

    public String getOutputTypeString() {
        return switch (outputType) {
            case STRING -> "STRING";
            case NULL -> null;
            case BOOLEAN -> "BOOLEAN";
            case INTEGER -> "INTEGER";
        };
    }

    public void setOutputType(BuilderOutputType outputType) {
        this.outputType = outputType;
    }

    public Integer getSplitIndex() {
        return splitIndex;
    }

    public void setSplitIndex(Integer splitIndex) {
        this.splitIndex = splitIndex;
    }

    public boolean isConstant() {
        return constant;
    }

    public void setConstant(boolean constant) {
        this.constant = constant;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
