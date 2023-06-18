package org.mapper.builder.mapper.type;

import org.mapper.builder.helper.ConditionalHelper;
import org.mapper.builder.mapper.Mapper;

import java.util.Map;

public class DataMapper extends Mapper<Object> {
    public DataMapper(String inputPath, Object value, Map inputMap, Map outputMap) {
        super(inputPath, value, inputMap, outputMap);
    }

    public DataMapper isType(Class clazz) throws Exception {
        if (ConditionalHelper.isType(getValue(), clazz)) {
            return this;
        } else {
            throw new Exception(String.format("Value: [%s] from path: [%s] is not of type: [%s]", getValue(), getInputPath(), clazz.getTypeName()));
        }
    }

    public DataMapper isNotNull() {
        if (ConditionalHelper.isNotNull(getValue())) {
            return this;
        } else {
            throw new NullPointerException(String.format("Value with path: [%s] is null", getInputPath()));
        }
    }

    public DataMapper isEqualTo(Object equals) throws Exception {
        if (ConditionalHelper.isEqualTo(getValue(), equals)) {
            return this;
        } else {
            throw new Exception(String.format("Value: [%s] does not equal: [%s]", getValue(), equals));
        }
    }
}
