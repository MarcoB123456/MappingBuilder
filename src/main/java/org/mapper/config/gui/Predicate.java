
package org.mapper.config.gui;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "groups"
})
@Generated("jsonschema2pojo")
public class Predicate {

    @JsonProperty("name")
    private String name;
    @JsonProperty("groups")
    private List<Group> groups;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Predicate() {
    }

    /**
     * 
     * @param name
     * @param groups
     */
    public Predicate(String name, List<Group> groups) {
        super();
        this.name = name;
        this.groups = groups;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("groups")
    public List<Group> getGroups() {
        return groups;
    }

    @JsonProperty("groups")
    public void setGroups(List<Group> groups) {
        this.groups = groups;
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
