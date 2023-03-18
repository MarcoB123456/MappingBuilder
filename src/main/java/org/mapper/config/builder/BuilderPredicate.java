package org.mapper.config.builder;

import java.util.ArrayList;
import java.util.List;

public class BuilderPredicate {

    private String predicateName;
    private List<BuilderPredicateGroup> groups;
    private List<BuilderMapping> positiveMappings;
    private List<BuilderMapping> negativeMappings;

    public BuilderPredicate(String predicateName) {
        this.predicateName = predicateName;
        this.positiveMappings = new ArrayList<>();
        this.negativeMappings = new ArrayList<>();
    }

    public String getPredicateName() {
        return predicateName;
    }

    public void setPredicateName(String predicateName) {
        this.predicateName = predicateName;
    }

    public List<BuilderPredicateGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<BuilderPredicateGroup> groups) {
        this.groups = groups;
    }

    public void addPositiveMapping(BuilderMapping positiveMapping) {
        this.positiveMappings.add(positiveMapping);
    }

    public List<BuilderMapping> getPositiveMappings() {
        return positiveMappings;
    }

    public void addNegativeMapping(BuilderMapping negativeMapping) {
        this.negativeMappings.add(negativeMapping);
    }

    public List<BuilderMapping> getNegativeMappings() {
        return negativeMappings;
    }

    public void setPositiveMappings(List<BuilderMapping> positiveMappings) {
        this.positiveMappings = positiveMappings;
    }

    public void setNegativeMappings(List<BuilderMapping> negativeMappings) {
        this.negativeMappings = negativeMappings;
    }
}
