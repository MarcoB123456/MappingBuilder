
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
        "outputPath",
        "splitPattern",
        "splitIndex",
        "constant",
        "value",
        "outputType",
        "beginIndex",
        "endIndex",
        "conditionalName",
        "conditionalResult"
})
@Generated("jsonschema2pojo")
public class Step {

    @JsonProperty("index")
    private Integer index;
    @JsonProperty("type")
    private String type;
    @JsonProperty("inputPath")
    private String inputPath;
    @JsonProperty("outputPath")
    private String outputPath;
    @JsonProperty("splitPattern")
    private String splitPattern;
    @JsonProperty("splitIndex")
    private Integer splitIndex;
    @JsonProperty("constant")
    private Boolean constant;
    @JsonProperty("value")
    private String value;
    @JsonProperty("outputType")
    private String outputType;
    @JsonProperty("beginIndex")
    private Integer beginIndex;
    @JsonProperty("endIndex")
    private Integer endIndex;
    @JsonProperty("conditionalName")
    private String conditionalName;
    @JsonProperty("conditionalResult")
    private Boolean conditionalResult;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public Step() {
    }

    /**
     * @param constant
     * @param index
     * @param outputType
     * @param conditionalResult
     * @param type
     * @param inputPath
     * @param splitPattern
     * @param outputPath
     * @param endIndex
     * @param beginIndex
     * @param conditionalName
     * @param splitIndex
     * @param value
     */
    public Step(Integer index, String type, String inputPath, String outputPath, String splitPattern, Integer splitIndex, Boolean constant, String value, String outputType, Integer beginIndex, Integer endIndex, String conditionalName, Boolean conditionalResult) {
        super();
        this.index = index;
        this.type = type;
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.splitPattern = splitPattern;
        this.splitIndex = splitIndex;
        this.constant = constant;
        this.value = value;
        this.outputType = outputType;
        this.beginIndex = beginIndex;
        this.endIndex = endIndex;
        this.conditionalName = conditionalName;
        this.conditionalResult = conditionalResult;
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

    @JsonProperty("outputPath")
    public String getOutputPath() {
        return outputPath;
    }

    @JsonProperty("outputPath")
    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    @JsonProperty("splitPattern")
    public String getSplitPattern() {
        return splitPattern;
    }

    @JsonProperty("splitPattern")
    public void setSplitPattern(String splitPattern) {
        this.splitPattern = splitPattern;
    }

    @JsonProperty("splitIndex")
    public Integer getSplitIndex() {
        return splitIndex;
    }

    @JsonProperty("splitIndex")
    public void setSplitIndex(Integer splitIndex) {
        this.splitIndex = splitIndex;
    }

    @JsonProperty("constant")
    public Boolean getConstant() {
        return constant;
    }

    @JsonProperty("constant")
    public void setConstant(Boolean constant) {
        this.constant = constant;
    }

    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
    }

    @JsonProperty("outputType")
    public String getOutputType() {
        return outputType;
    }

    @JsonProperty("outputType")
    public void setOutputType(String outputType) {
        this.outputType = outputType;
    }

    @JsonProperty("beginIndex")
    public Integer getBeginIndex() {
        return beginIndex;
    }

    @JsonProperty("beginIndex")
    public void setBeginIndex(Integer beginIndex) {
        this.beginIndex = beginIndex;
    }

    @JsonProperty("endIndex")
    public Integer getEndIndex() {
        return endIndex;
    }

    @JsonProperty("endIndex")
    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }

    @JsonProperty("conditionalName")
    public String getConditionalName() {
        return conditionalName;
    }

    @JsonProperty("conditionalName")
    public void setConditionalName(String conditionalName) {
        this.conditionalName = conditionalName;
    }

    @JsonProperty("conditionalResult")
    public Boolean getConditionalResult() {
        return conditionalResult;
    }

    @JsonProperty("conditionalResult")
    public void setConditionalResult(Boolean conditionalResult) {
        this.conditionalResult = conditionalResult;
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
