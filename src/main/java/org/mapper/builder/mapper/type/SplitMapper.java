package org.mapper.builder.mapper.type;

import org.mapper.builder.helper.IOHelper;
import org.mapper.builder.helper.ConditionalHelper;
import org.mapper.builder.mapper.Mapper;

import java.util.Map;

public class SplitMapper extends Mapper<String> {

    private String[] splitValue;
    private final String splitPattern;

    public SplitMapper(String inputPath, String value,  Map<String, Object> inputMap, Map<String, Object> outputMap, String splitPattern) {
        super(inputPath, value, inputMap, outputMap);
        this.splitPattern = splitPattern;

        split(getValue());
    }

    private void split(String input) {
        if (ConditionalHelper.isNotNull(input)) {
            splitValue = input.split(this.splitPattern);
        } else {
            throw new NullPointerException(String.format("Value with path: [%s] is null", getInputPath()));
        }
    }

    @Override
    public void insert(String outputPath, Class outputType) throws Exception {
        throw new Exception("Insertion failed. Please indicate the splitIndex");
    }

    public SplitMapper insert(String outputPath, int splitIndex, Class outputType) throws Exception {
        IOHelper.insertValue(getOutputMap(), outputPath,  getValueByIndex(splitIndex), outputType);
        return this;
    }

    public SplitMapper insert(String outputPath, int splitIndex) throws Exception {
        IOHelper.insertValue(getOutputMap(), outputPath,  getValueByIndex(splitIndex), null);
        return this;
    }

    /**
     * @return String value before split
     */
    public StringMapper endSplit() {
        return new StringMapper(getInputPath(), getValue(), getInputMap(), getOutputMap());
    }

    public SplitMapper isNotNull(int splitIndex) {
        if (ConditionalHelper.isNotNull(getValueByIndex(splitIndex))) {
            return this;
        } else {
            throw new NullPointerException(String.format("Value with path: [%s] is null after split on [%s] with index [%s]", getInputPath(), this.splitPattern, splitIndex));
        }
    }

    public SplitMapper contains(int splitIndex, String contains) throws Exception {
        if (ConditionalHelper.contains(getValueByIndex(splitIndex), contains)) {
            return this;
        } else {
            throw new Exception(String.format("Value: [%s] from path: [%s] does not contain: [%s]", this.splitValue[splitIndex], getInputPath(), contains));
        }
    }

    public SplitMapper containsIgnoreCase(int splitIndex, String contains) throws Exception {
        if (ConditionalHelper.containsIgnoreCase(getValueByIndex(splitIndex), contains)) {
            return this;
        } else {
            throw new Exception(String.format("Value: [%s] from path: [%s] does not containIgnoreCase: [%s]", this.splitValue[splitIndex], getInputPath(), contains));
        }
    }

    public SplitMapper isEqualTo(int splitIndex, String equals) throws Exception {
        if (ConditionalHelper.isEqualTo(getValueByIndex(splitIndex), equals)) {
            return this;
        } else {
            throw new Exception(String.format("Value: [%s] does not equal: [%s]", this.splitValue[splitIndex], equals));
        }
    }

    public SplitMapper isEqualToIgnoreCase(int splitIndex, String equals) throws Exception {
        if (ConditionalHelper.isEqualToIgnoreCase(getValueByIndex(splitIndex), equals)) {
            return this;
        } else {
            throw new Exception(String.format("Value: [%s] does not equalIgnoreCase: [%s]", this.splitValue[splitIndex], equals));
        }
    }

    public SplitMapper isType(int splitIndex, Class clazz) throws Exception {
        if (ConditionalHelper.isType(getValueByIndex(splitIndex), clazz)) {
            return this;
        } else {
            throw new Exception(String.format("Value: [%s] from path: [%s] is not of type: [%s]", this.splitValue[splitIndex], getInputPath(), clazz.getTypeName()));
        }
    }

    private Object getValueByIndex(int splitIndex) {
        try {
            return this.splitValue[splitIndex];
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new ArrayIndexOutOfBoundsException(String.format("Index [%s] does not exist. Size after split is: [%s]", splitIndex, this.splitValue.length));
        }
    }
}
