
package org.mapper.config.gui;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "className",
    "predicates",
    "mappings"
})
@Generated("jsonschema2pojo")
public class Configuration {

    @JsonProperty("className")
    private String className;
    @JsonProperty("predicates")
    private List<Predicate> predicates;
    @JsonProperty("mappings")
    private List<Mapping> mappings;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Configuration() {
    }

    /**
     * 
     * @param predicates
     * @param mappings
     * @param className
     */
    public Configuration(String className, List<Predicate> predicates, List<Mapping> mappings) {
        super();
        this.className = className;
        this.predicates = predicates;
        this.mappings = mappings;
    }

    @JsonProperty("className")
    public String getClassName() {
        return className;
    }

    @JsonProperty("className")
    public void setClassName(String className) {
        this.className = className;
    }

    @JsonProperty("predicates")
    public List<Predicate> getPredicates() {
        return predicates;
    }

    @JsonProperty("predicates")
    public void setPredicates(List<Predicate> predicates) {
        this.predicates = predicates;
    }

    @JsonProperty("mappings")
    public List<Mapping> getMappings() {
        return mappings;
    }

    @JsonProperty("mappings")
    public void setMappings(List<Mapping> mappings) {
        this.mappings = mappings;
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
