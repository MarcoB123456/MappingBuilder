package org.mapper.config.builder;

import java.util.List;

public class BuilderConfiguration {


    private String className;

    private List<BuilderPredicate> predicates;

    private List<BuilderMapping> mappings;

    public BuilderConfiguration() {
    }

    public BuilderConfiguration(String className, List<BuilderPredicate> predicates, List<BuilderMapping> mappings) {
        this.className = className;
        this.predicates = predicates;
        this.mappings = mappings;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<BuilderPredicate> getPredicates() {
        return predicates;
    }

    public void setPredicates(List<BuilderPredicate> predicates) {
        this.predicates = predicates;
    }

    public List<BuilderMapping> getMappings() {
        return mappings;
    }

    public void setMappings(List<BuilderMapping> mappings) {
        this.mappings = mappings;
    }
}
