package org.mapper.builder.mapper;

import org.mapper.builder.helper.IOHelper;

import java.util.Map;

public abstract class Mapper<T> {

    private final String inputPath;
    private Object value;

    private final Map<String, Object> inputMap;
    private final Map<String, Object> outputMap;

    public Mapper(String inputPath, Object value, Map<String, Object> inputMap, Map<String, Object> outputMap) {
        this.inputPath = inputPath;
        this.inputMap = inputMap;
        this.outputMap = outputMap;

        this.value = value;
    }

    public void insert(String outputPath) throws Exception {
        IOHelper.insertValue(this.outputMap, outputPath, this.value, null);
    }

    public void insert(String outputPath, Class outputType) throws Exception {
        IOHelper.insertValue(this.outputMap, outputPath, this.value, outputType);
    }

    public void insert(String outputPath, Object outputValue, Class outputType) throws Exception {
        IOHelper.insertValue(getOutputMap(), outputPath, outputValue, outputType);
    }

    public String getInputPath() {
        return inputPath;
    }

    public <T> T getValue() {
        return (T) value;
    }

    public Map<String, Object> getInputMap() {
        return inputMap;
    }

    public Map<String, Object> getOutputMap() {
        return outputMap;
    }


    public void setValue(T value) {
        this.value = value;
    }
}
