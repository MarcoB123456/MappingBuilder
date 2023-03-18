package org.mapper.builder;

import org.mapper.Helper;

import java.util.Map;

public class DataMapper extends Conditional implements Action {

    private String inputPath;
    private Object value;

    private final Map<String, Object> inputMap;
    private final Map<String, Object> outputMap;

    public DataMapper(Map<String, Object> inputMap, Map<String, Object> outputMap) {
        this.inputMap = inputMap;
        this.outputMap = outputMap;
    }

    public DataMapper notNull() {
        if (notNull(this.value)) {
            return this;
        } else {
            throw new NullPointerException(String.format("Value with path: [%s] is null", this.inputPath));
        }
    }

    public DataMapper contains(String contains) throws Exception {
        if (contains(this.value, contains)) {
            return this;
        } else {
            throw new Exception(String.format("Value: [%s] from path: [%s] does not contain: [%s]", this.value, this.inputPath, contains));
        }
    }

    public DataMapper containsIgnoreCase(String contains) throws Exception {
        if (containsIgnoreCase(this.value, contains)) {
            return this;
        } else {
            throw new Exception(String.format("Value: [%s] from path: [%s] does not containIgnoreCase: [%s]", this.value, this.inputPath, contains));
        }
    }

    public DataMapper isEqualTo(String equals) throws Exception {
        if (isEqualTo(this.value, equals)) {
            return this;
        } else {
            throw new Exception(String.format("Value: [%s] does not equal: [%s]", this.value, equals));
        }
    }

    public DataMapper isEqualToIgnoreCase(String equals) throws Exception {
        if (isEqualToIgnoreCase(this.value, equals)) {
            return this;
        } else {
            throw new Exception(String.format("Value: [%s] does not equalIgnoreCase: [%s]", this.value, equals));
        }
    }

    public DataMapper isType(Class clazz) throws Exception {
        if (isType(this.value, clazz)) {
            return this;
        } else {
            throw new Exception(String.format("Value: [%s] from path: [%s] is not of type: [%s]", this.value, this.inputPath, clazz.getTypeName()));
        }
    }

    public DataMapper setValue(String inputPath) {
        this.inputPath = inputPath;
        this.value = Helper.getValue(inputMap, inputPath);
        return this;
    }

    @Override
    public DataMapper subString(int beginIndex, int endIndex) {
        this.value = String.valueOf(this.value).substring(beginIndex, Math.min(endIndex, String.valueOf(this.value).length()));
        return this;
    }

    @Override
    public SplitMapper split(String split) {
        return new SplitMapper(split, this).split(this.value);
    }



    public DataMapper insertValue(String outputPath, String outputType) throws Exception {
        Helper.insertValue(this.outputMap, outputPath, this.value, outputType);
        return this;
    }

    public DataMapper insertValue(String outputPath, Object outputValue, String outputType) throws Exception {
        Helper.insertValue(this.outputMap, outputPath, outputValue, outputType);
        return this;
    }

    protected Object getValue() {
        return this.value;
    }

    protected Object getInputPath() {
        return this.inputPath;
    }
}
