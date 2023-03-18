package org.mapper.config;

import org.mapper.config.builder.*;
import org.mapper.config.builder.step.*;
import org.mapper.config.builder.type.BuilderOperatorType;
import org.mapper.config.builder.type.BuilderOutputType;
import org.mapper.config.builder.type.BuilderType;
import org.mapper.config.gui.*;

import java.util.ArrayList;
import java.util.List;

import static org.mapper.config.builder.type.BuilderType.READ;

public class GuiToBuilder {


    public static BuilderConfiguration toBuilderConfiguration(Configuration configuration) {
        BuilderConfiguration builderConfiguration = new BuilderConfiguration();

        builderConfiguration.setClassName(configuration.getClassName());
        builderConfiguration.setPredicates(toPredicates(configuration.getPredicates(), configuration.getMappings()));

        // Filter out all the mappings that are not part of predicate logic
        List<BuilderMapping> mappings = configuration.getMappings().stream()
                .filter(mapping -> mapping.getSteps().get(0).getType().equals(READ.toString()))
                .filter(mapping -> mapping.getSteps().get(0).getConditionalName() == null)
                .map(GuiToBuilder::toMapping)
                .toList();

        builderConfiguration.setMappings(mappings);


        return builderConfiguration;
    }

    private static List<BuilderPredicate> toPredicates(List<Predicate> predicates, List<Mapping> mappings) {
        List<BuilderPredicate> builderPredicates = new ArrayList<>();

        // Create all predicates
        for (Predicate predicate : predicates) {
            BuilderPredicate builderPredicate = new BuilderPredicate(predicate.getName());
            builderPredicate.setGroups(toPredicateGroup(predicate.getGroups()));

            // Get all mappings for the positive side of the predicate
            List<BuilderMapping> positivePredicateMappings = mappings.stream()
                    .filter(mapping -> mapping.getSteps().get(0).getType().equals(READ.toString()))
                    .filter(mapping -> mapping.getSteps().get(0).getConditionalName() != null)
                    .filter(mapping -> mapping.getSteps().get(0).getConditionalName().equals(predicate.getName()))
                    .filter(mapping -> mapping.getSteps().get(0).getConditionalResult().equals(true))
                    .map(GuiToBuilder::toMapping)
                    .toList();

            builderPredicate.setPositiveMappings(positivePredicateMappings);

            // Get all mappings for the negative side of the predicate
            List<BuilderMapping> negativePredicateMappings = mappings.stream()
                    .filter(mapping -> mapping.getSteps().get(0).getType().equals(READ.toString()))
                    .filter(mapping -> mapping.getSteps().get(0).getConditionalName() != null)
                    .filter(mapping -> mapping.getSteps().get(0).getConditionalName().equals(predicate.getName()))
                    .filter(mapping -> mapping.getSteps().get(0).getConditionalResult().equals(false))
                    .map(GuiToBuilder::toMapping)
                    .toList();

            builderPredicate.setNegativeMappings(negativePredicateMappings);

            builderPredicates.add(builderPredicate);
        }

        return builderPredicates;
    }

    private static BuilderMapping toMapping(Mapping mapping) {
        BuilderMapping builderMapping = new BuilderMapping();
        builderMapping.setSteps(toSteps(mapping.getSteps()));

        return builderMapping;
    }

    private static List<BuilderStep> toSteps(List<Step> steps) {
        List<BuilderStep> builderSteps = new ArrayList<>();

        for (Step step : steps) {
            switch (BuilderType.valueOf(step.getType())) {
                case READ -> builderSteps.add(new ReadBuilderStep(step.getIndex(), step.getInputPath()));
                case SPLIT -> builderSteps.add(new SplitBuilderStep(step.getIndex(), step.getSplitPattern()));
                case EQUALS -> builderSteps.add(new EqualsBuilderStep(step.getIndex(), step.getValue()));
                case INSERT ->
                        builderSteps.add(new InsertBuilderStep(step.getIndex(), step.getOutputPath(), BuilderOutputType.byName(step.getOutputType()), step.getSplitIndex(), step.getConstant(), step.getValue()));
                case NOTNULL -> builderSteps.add(new NotNullBuilderStep(step.getIndex()));
                case CONTAINS -> builderSteps.add(new ContainsBuilderStep(step.getIndex(), step.getValue()));
                case SUBSTRING ->
                        builderSteps.add(new SubStringBuilderStep(step.getIndex(), step.getBeginIndex(), step.getEndIndex()));
                case EQUALS_IGNORE_CASE ->
                        builderSteps.add(new EqualsIgnoreCaseBuilderStep(step.getIndex(), step.getValue()));
                case CONTAINS_IGNORE_CASE ->
                        builderSteps.add(new ContainsIgnoreCaseBuilderStep(step.getIndex(), step.getValue()));
                default ->
                        throw new IllegalArgumentException(String.format("Step type with name: [%s] does not exist", step.getType()));
            }
        }

        return builderSteps;
    }


    private static List<BuilderPredicateGroup> toPredicateGroup(List<Group> groups) {
        List<BuilderPredicateGroup> builderPredicateGroups = new ArrayList<>();

        for (Group group : groups) {
            BuilderPredicateGroup builderPredicateGroup = new BuilderPredicateGroup();
            builderPredicateGroup.setOperatorType(BuilderOperatorType.valueOf(group.getOperator()));
            builderPredicateGroup.setStatements(toPredicateStatement(group.getStatements()));

            builderPredicateGroups.add(builderPredicateGroup);
        }

        return builderPredicateGroups;
    }

    private static List<BuilderPredicateStatement> toPredicateStatement(List<Statement> statements) {
        List<BuilderPredicateStatement> builderPredicateStatements = new ArrayList<>();
        for (Statement statement : statements) {
            BuilderPredicateStatement builderPredicateStatement = new BuilderPredicateStatement();
            builderPredicateStatement.setIndex(statement.getIndex());
            builderPredicateStatement.setType(BuilderType.valueOf(statement.getType()));
            builderPredicateStatement.setInputPath(statement.getInputPath());
            builderPredicateStatement.setValue(statement.getValue());

            builderPredicateStatements.add(builderPredicateStatement);
        }

        return builderPredicateStatements;
    }
}
