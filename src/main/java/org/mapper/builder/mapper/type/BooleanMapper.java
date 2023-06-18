package org.mapper.builder.mapper.type;

import org.mapper.builder.helper.ConditionalHelper;
import org.mapper.builder.mapper.Mapper;

import java.util.Map;


public class BooleanMapper extends Mapper<Boolean> {


    public BooleanMapper(String inputPath, Object value, Map<String, Object> inputMap, Map<String, Object> outputMap) {
        super(inputPath, Boolean.valueOf((String) value),  inputMap, outputMap);
    }



    public BooleanMapper isNotNull() {
        if (ConditionalHelper.isNotNull(getValue())) {
            return this;
        } else {
            throw new NullPointerException(String.format("Value with path: [%s] is null", getInputPath()));
        }
    }

    public BooleanMapper isTrue() throws Exception {
        if (Boolean.TRUE.equals(getValue())) {
            return this;
        } else {
            throw new Exception(String.format("Value: [%s] is not true", getValue()));
        }
    }

    public BooleanMapper isFalse() throws Exception {
        if (Boolean.FALSE.equals(getValue())) {
            return this;
        } else {
            throw new Exception(String.format("Value: [%s] is not false", getValue()));
        }
    }

    public BooleanMapper toggle() throws Exception {
        Boolean bool = getValue();
        if (bool == null){
            throw new Exception("Cannot toggle null value");
        }
        setValue(!bool);
        return this;
    }
}
