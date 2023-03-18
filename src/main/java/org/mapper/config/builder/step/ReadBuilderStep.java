package org.mapper.config.builder.step;

import static org.mapper.config.builder.type.BuilderType.READ;

public class ReadBuilderStep extends BuilderStep {

    private String inputPath;


    public ReadBuilderStep(int index, String inputPath) {
        super(index, READ);
        this.inputPath = inputPath;
    }

    public String getInputPath() {
        return inputPath;
    }

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }
}
