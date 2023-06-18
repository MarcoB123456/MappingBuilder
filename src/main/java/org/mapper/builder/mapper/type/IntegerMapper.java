package org.mapper.builder.mapper.type;

import org.mapper.builder.helper.ConditionalHelper;
import org.mapper.builder.mapper.Mapper;

import java.util.Map;


public class IntegerMapper extends Mapper<Integer> {


    public IntegerMapper(String inputPath, Object value, Map<String, Object> inputMap, Map<String, Object> outputMap) {
        super(inputPath, Integer.valueOf((String) value),  inputMap, outputMap);
    }

    public IntegerMapper isNotNull() {
        if (ConditionalHelper.isNotNull(getValue())) {
            return this;
        } else {
            throw new NullPointerException(String.format("Value with path: [%s] is null", getInputPath()));
        }
    }

    public IntegerMapper isEqualTo(Integer equals) throws Exception {
        if (ConditionalHelper.isEqualTo(getValue(), equals)) {
            return this;
        } else {
            throw new Exception(String.format("Value: [%s] does not equal: [%s]", getValue(), equals));
        }
    }

    public IntegerMapper isGreaterThen(Integer greater) throws Exception {
        if ((Integer) getValue() > greater) {
            return this;
        } else {
            throw new Exception(String.format("Value: [%s] is not greater then: [%s]", getValue(), greater));
        }
    }

    public IntegerMapper isLesserThen(Integer lesser) throws Exception {
        if ((Integer) getValue() < lesser) {
            return this;
        } else {
            throw new Exception(String.format("Value: [%s] is not lesser then: [%s]", getValue(), lesser));
        }
    }

    public IntegerMapper isBetween(Integer lesser, Integer greater) throws Exception {
        if (lesser < (Integer) getValue() && (Integer) getValue() < greater) {
            return this;
        } else {
            throw new Exception(String.format("Value: [%s] is not lesser then: [%s]", getValue(), lesser));
        }
    }
}
