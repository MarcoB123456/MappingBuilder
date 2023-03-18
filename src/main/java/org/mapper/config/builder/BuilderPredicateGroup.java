package org.mapper.config.builder;

import org.mapper.config.builder.type.BuilderOperatorType;

import java.util.List;

public class BuilderPredicateGroup {

    private BuilderOperatorType operatorType;

    private List<BuilderPredicateStatement> statements;

    public String getOperator() {
        return switch (operatorType) {
            case AND -> "&&";
            case OR -> "||";
        };
    }

    public BuilderOperatorType getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(BuilderOperatorType operatorType) {
        this.operatorType = operatorType;
    }

    public List<BuilderPredicateStatement> getStatements() {
        return statements;
    }

    public void setStatements(List<BuilderPredicateStatement> statements) {
        this.statements = statements;
    }
}
