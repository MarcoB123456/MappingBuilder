package org.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeSpec;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapper.builder.MappingBuilder;
import org.mapper.config.builder.*;
import org.mapper.config.builder.BuilderPredicateStatement.StatementComparator;
import org.mapper.config.builder.step.*;
import org.mapper.config.builder.step.BuilderStep.StepComparator;

import javax.lang.model.element.Modifier;
import java.nio.file.Path;
import java.util.*;

import static org.mapper.Helper.isString;


public class MappingClassBuilder {

    private static final Logger LOGGER = LogManager.getLogger(MappingClassBuilder.class);

    public void build(BuilderConfiguration configuration) throws Exception {
        LOGGER.info("Started building Mapping class with name [{}]", configuration.getClassName());
        JavaFile javaFile = JavaFile.builder("org.mapper.output", buildClass(configuration.getClassName(), buildMainMethod(configuration)))
                .build();

//        javaFile.writeTo(System.out);
        javaFile.writeTo(Path.of("src/main/java"));
        LOGGER.info("Mapping class with name [{}] was exported to [{}]", configuration.getClassName(), "src/main/java");
    }

    private String buildPredicateControlFlow(List<BuilderPredicateGroup> groups) {
        LOGGER.debug("Started building new PredicateControlFlow");
        CodeBlock.Builder predicateBuilder = CodeBlock.builder();

        predicateBuilder.add("if (");
        LOGGER.debug("PredicateControlFlow contains [{}] groups", groups.size());
        for (int i = 0; i < groups.size(); i++) {
            BuilderPredicateGroup group = groups.get(i);
            LOGGER.debug("Started building group [{}/{}] with operatorType: [{}]", i + 1, groups.size(), group.getOperatorType());

            predicateBuilder.add("(");

            List<BuilderPredicateStatement> statements = group.getStatements();
            statements.sort(new StatementComparator());
            for (int j = 0; j < statements.size(); j++) {
                BuilderPredicateStatement statement = statements.get(j);
                LOGGER.debug("Adding statement with type: [{}]", statement.getType());
                switch (statement.getType()) {
                    case NOTNULL ->
                            predicateBuilder.add("mappingBuilder.startPredicateMapper($S).notNull()", statement.getInputPath());
                    case EQUALS ->
                            predicateBuilder.add("mappingBuilder.startPredicateMapper($S).isEqualTo($L)", statement.getInputPath(), isString(statement.getValue()));
                }

                if (j + 1 < statements.size()) {
                    predicateBuilder.add(" $L ", group.getOperator());
                }
            }

            predicateBuilder.add(")");
        }

        return predicateBuilder.add(")").build().toString();
    }


    private CodeBlock buildPredicates(BuilderPredicate builderPredicate) {
        LOGGER.debug("Started building Predicate with; PositiveMappings size [{}], NegativeMappings size [{}]", builderPredicate.getPositiveMappings().size(), builderPredicate.getNegativeMappings().size());

        CodeBlock.Builder builder = CodeBlock.builder();

        if (!builderPredicate.getPositiveMappings().isEmpty()) {
            builder.add("\n");
            builder.beginControlFlow(buildPredicateControlFlow(builderPredicate.getGroups()));

            for (BuilderMapping positiveMapping : builderPredicate.getPositiveMappings()) {
                builder.addStatement(buildMap(positiveMapping));
            }

            if (!builderPredicate.getNegativeMappings().isEmpty()) {
                builder.nextControlFlow("else");

                for (BuilderMapping negativeMapping : builderPredicate.getNegativeMappings()) {
                    builder.addStatement(buildMap(negativeMapping));
                }
            }

            builder.endControlFlow();
        }

        return builder.build();
    }

    private CodeBlock buildMap(BuilderMapping mapping) {
        CodeBlock.Builder builder = CodeBlock.builder();

        if (mapping.getSteps().isEmpty()) {
            LOGGER.warn("Mapping contains no steps");
            builder.add("// There would've been a mapping here, but it had no steps");
            return builder.build();
        }

        LOGGER.debug("Started building Mapping with [{}] steps", mapping.getSteps().size());

        builder.add("mappingBuilder.startDataMap()");

        List<BuilderStep> steps = mapping.getSteps();
        steps.sort(new StepComparator());
        for (BuilderStep step : steps) {
            LOGGER.debug("Adding step with type [{}]", step.getType());
            switch (step.getType()) {
                case READ -> builder.add(".setValue($S)", ((ReadBuilderStep) step).getInputPath());
                case INSERT -> {
                    InsertBuilderStep insertBuilderStep = (InsertBuilderStep) step;
                    if (insertBuilderStep.isConstant()) {
                        builder.add(".insertValue($S, $L, $L)", insertBuilderStep.getOutputPath(), isString(insertBuilderStep.getValue()), isString(insertBuilderStep.getOutputTypeString()));
                    } else if (insertBuilderStep.getSplitIndex() != null) {
                        builder.add(".insertValue($S, $L, $L)", insertBuilderStep.getOutputPath(), insertBuilderStep.getSplitIndex(), isString(insertBuilderStep.getOutputTypeString()));
                    } else {
                        builder.add(".insertValue($S, $L)", insertBuilderStep.getOutputPath(), isString(insertBuilderStep.getOutputTypeString()));
                    }
                }
                case NOTNULL -> builder.add(".notNull()");
                case EQUALS -> builder.add(".isEqualTo($L)", isString(((EqualsBuilderStep) step).getValue()));
                case EQUALS_IGNORE_CASE ->
                        builder.add(".isEqualToIgnoreCase($L)", isString(((EqualsIgnoreCaseBuilderStep) step).getValue()));
                case CONTAINS -> builder.add(".contains($S)", ((ContainsBuilderStep) step).getValue());
                case CONTAINS_IGNORE_CASE ->
                        builder.add(".containsIgnoreCase($S)", ((ContainsIgnoreCaseBuilderStep) step).getValue());
                case SPLIT -> builder.add(".split($S)", ((SplitBuilderStep) step).getSplitPattern());
                case SUBSTRING -> {
                    SubStringBuilderStep subStringBuilderStep = (SubStringBuilderStep) step;
                    builder.add(".subString($L, $L)", subStringBuilderStep.getBeginIndex(), subStringBuilderStep.getEndIndex());
                }
            }
        }

        return builder.build();
    }


    private MethodSpec buildMainMethod(BuilderConfiguration configuration) {
        Builder methodBuilder = MethodSpec.methodBuilder("map")
                .addModifiers(Modifier.PUBLIC)
                .addException(Exception.class)
                .addParameter(String.class, "payload")
                .addStatement("$T objectMapper = new $T()", ObjectMapper.class, ObjectMapper.class)
                .addStatement("objectMapper.enable($T.ORDER_MAP_ENTRIES_BY_KEYS)", SerializationFeature.class)
                .addStatement("$T<String, Object> inputMap = objectMapper.readValue(payload, $T.class)", Map.class, HashMap.class)
                .addCode("\n")
                .addStatement("$T mappingBuilder = new $T(inputMap, new $T<>())", MappingBuilder.class, MappingBuilder.class, HashMap.class)
                .returns(String.class);


        LOGGER.debug("Started building [{}] mappings", configuration.getMappings().size());
        for (BuilderMapping mapping : configuration.getMappings()) {
            methodBuilder.addStatement(buildMap(mapping));
        }

        LOGGER.debug("Started building [{}] predicates", configuration.getPredicates().size());
        for (BuilderPredicate predicate : configuration.getPredicates()) {
            methodBuilder.addCode(buildPredicates(predicate));
        }

        methodBuilder
                .addCode("\n")
                .addStatement("return mappingBuilder.build(objectMapper)");

        return methodBuilder.build();
    }

    private TypeSpec buildClass(String className, MethodSpec... methods) {
        return TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC)
                .addMethods(Arrays.asList(methods))
                .build();
    }
}
