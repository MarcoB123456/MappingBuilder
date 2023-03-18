
package org.mapper.config.gui;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "operator",
    "statements"
})
@Generated("jsonschema2pojo")
public class Group {

    @JsonProperty("operator")
    private String operator;
    @JsonProperty("statements")
    private List<Statement> statements;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Group() {
    }

    /**
     * 
     * @param statements
     * @param operator
     */
    public Group(String operator, List<Statement> statements) {
        super();
        this.operator = operator;
        this.statements = statements;
    }

    @JsonProperty("operator")
    public String getOperator() {
        return operator;
    }

    @JsonProperty("operator")
    public void setOperator(String operator) {
        this.operator = operator;
    }

    @JsonProperty("statements")
    public List<Statement> getStatements() {
        return statements;
    }

    @JsonProperty("statements")
    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
