package org.mapper.builder.mapper.type;

import org.mapper.builder.helper.ConditionalHelper;
import org.mapper.builder.mapper.Mapper;

import java.util.Map;


public class StringMapper extends Mapper<String> {


    public StringMapper(String inputPath, Object value,  Map<String, Object> inputMap, Map<String, Object> outputMap) {
        super(inputPath, value, inputMap, outputMap);
    }

    public StringMapper contains(String contains) throws Exception {
        if (ConditionalHelper.contains(getValue(), contains)) {
            return this;
        } else {
            throw new Exception(String.format("Value: [%s] from path: [%s] does not contain: [%s]", getValue(), getInputPath(), contains));
        }
    }

    public StringMapper containsIgnoreCase(String contains) throws Exception {
        if (ConditionalHelper.containsIgnoreCase(getValue(), contains)) {
            return this;
        } else {
            throw new Exception(String.format("Value: [%s] from path: [%s] does not containIgnoreCase: [%s]", getValue(), getInputPath(), contains));
        }
    }

    public StringMapper isEqualTo(String equals) throws Exception {
        if (ConditionalHelper.isEqualTo(getValue(), equals)) {
            return this;
        } else {
            throw new Exception(String.format("Value: [%s] does not equal: [%s]", getValue(), equals));
        }
    }

    public StringMapper isEqualToIgnoreCase(String equals) throws Exception {
        if (ConditionalHelper.isEqualToIgnoreCase(getValue(), equals)) {
            return this;
        } else {
            throw new Exception(String.format("Value: [%s] does not equalIgnoreCase: [%s]", getValue(), equals));
        }
    }

    public SplitMapper split(String splitPattern) {
        return new SplitMapper(getInputPath(), getValue(), getInputMap(), getOutputMap(), splitPattern);
    }

    public StringMapper subString(int beginIndex, int endIndex) {
        return new StringMapper(getInputPath(), ((String) getValue()).substring(beginIndex, Math.min(endIndex, String.valueOf(getValue()).length())), getInputMap(), getOutputMap());
    }
}
