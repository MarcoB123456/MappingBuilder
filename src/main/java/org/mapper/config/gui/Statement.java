
package org.mapper.config.gui;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "index",
    "type",
    "inputPath",
    "value"
})
@Generated("jsonschema2pojo")
public class Statement {

    @JsonProperty("index")
    private Integer index;
    @JsonProperty("type")
    private String type;
    @JsonProperty("inputPath")
    private String inputPath;
    @JsonProperty("value")
    private Object value;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Statement() {
    }

    /**
     * 
     * @param inputPath
     * @param index
     * @param type
     * @param value
     */
    public Statement(Integer index, String type, String inputPath, String value) {
        super();
        this.index = index;
        this.type = type;
        this.inputPath = inputPath;
        this.value = value;
    }

    @JsonProperty("index")
    public Integer getIndex() {
        return index;
    }

    @JsonProperty("index")
    public void setIndex(Integer index) {
        this.index = index;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("inputPath")
    public String getInputPath() {
        return inputPath;
    }

    @JsonProperty("inputPath")
    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    @JsonProperty("value")
    public Object getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(Object value) {
        this.value = value;
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
