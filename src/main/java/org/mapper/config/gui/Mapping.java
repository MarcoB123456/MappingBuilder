
package org.mapper.config.gui;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "steps"
})
@Generated("jsonschema2pojo")
public class Mapping {

    @JsonProperty("steps")
    private List<Step> steps;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Mapping() {
    }

    /**
     * 
     * @param steps
     */
    public Mapping(List<Step> steps) {
        super();
        this.steps = steps;
    }

    @JsonProperty("steps")
    public List<Step> getSteps() {
        return steps;
    }

    @JsonProperty("steps")
    public void setSteps(List<Step> steps) {
        this.steps = steps;
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
