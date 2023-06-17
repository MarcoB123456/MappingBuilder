package org.mapper.builder;

public class SplitMapper extends Conditional {

    private String[] inputValue;
    private final String splitPattern;
    private final DataMapper dataMapper;


    public SplitMapper(String splitPattern, DataMapper dataMapper) {
        this.splitPattern = splitPattern;
        this.dataMapper = dataMapper;
    }

    public SplitMapper split(Object input) {
        if (notNull(input)) {
            inputValue = String.valueOf(input).split(this.splitPattern);
            return this;
        } else {
            throw new NullPointerException(String.format("Value with path: [%s] is null", this.dataMapper.getInputPath()));
        }
    }

    public SplitMapper insert(String outputPath, int splitIndex, Class outputType) throws Exception {
        dataMapper.insert(outputPath, getValueByIndex(splitIndex), outputType);
        return this;
    }

    public SplitMapper insert(String outputPath, int splitIndex) throws Exception {
        dataMapper.insert(outputPath, getValueByIndex(splitIndex), null);
        return this;
    }

    public DataMapper endSplit() {
        return this.dataMapper;
    }

    public SplitMapper notNull(int splitIndex) {
        if (notNull(getValueByIndex(splitIndex))) {
            return this;
        } else {
            throw new NullPointerException(String.format("Value with path: [%s] is null after split on [%s] with index [%s]", this.dataMapper.getInputPath(), this.splitPattern, splitIndex));
        }
    }

    public SplitMapper contains(int splitIndex, String contains) throws Exception {
        if (contains(getValueByIndex(splitIndex), contains)) {
            return this;
        } else {
            throw new Exception(String.format("Value: [%s] from path: [%s] does not contain: [%s]", this.inputValue[splitIndex], this.dataMapper.getInputPath(), contains));
        }
    }

    public SplitMapper containsIgnoreCase(int splitIndex, String contains) throws Exception {
        if (containsIgnoreCase(getValueByIndex(splitIndex), contains)) {
            return this;
        } else {
            throw new Exception(String.format("Value: [%s] from path: [%s] does not containIgnoreCase: [%s]", this.inputValue[splitIndex], this.dataMapper.getInputPath(), contains));
        }
    }

    public SplitMapper equals(int splitIndex, String equals) throws Exception {
        if (isEqualTo(getValueByIndex(splitIndex), equals)) {
            return this;
        } else {
            throw new Exception(String.format("Value: [%s] does not equal: [%s]", this.inputValue[splitIndex], equals));
        }
    }

    public SplitMapper equalsIgnoreCase(int splitIndex, String equals) throws Exception {
        if (isEqualToIgnoreCase(getValueByIndex(splitIndex), equals)) {
            return this;
        } else {
            throw new Exception(String.format("Value: [%s] does not equalIgnoreCase: [%s]", this.inputValue[splitIndex], equals));
        }
    }

    public SplitMapper isType(int splitIndex, Class clazz) throws Exception {
        if (isType(getValueByIndex(splitIndex), clazz)) {
            return this;
        } else {
            throw new Exception(String.format("Value: [%s] from path: [%s] is not of type: [%s]", this.inputValue[splitIndex], this.dataMapper.getInputPath(), clazz.getTypeName()));
        }
    }

    private Object getValueByIndex(int splitIndex) {
        try {
            return this.inputValue[splitIndex];
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new ArrayIndexOutOfBoundsException(String.format("Index [%s] does not exist. Size after split is: [%s]", splitIndex, this.inputValue.length));
        }
    }
}
