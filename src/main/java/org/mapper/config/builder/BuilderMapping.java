package org.mapper.config.builder;

import org.mapper.config.builder.step.BuilderStep;

import java.util.List;

public class BuilderMapping {

    private List<BuilderStep> steps;

    public List<BuilderStep> getSteps() {
        return steps;
    }

    public void setSteps(List<BuilderStep> steps) {
        this.steps = steps;
    }
}
