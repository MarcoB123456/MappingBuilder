package org.mapper.builder.mapper.type;

import org.mapper.builder.mapper.Mapper;

import java.util.Map;

public class NullMapper extends Mapper<Object> {

    public NullMapper(String inputPath, Map<String, Object> inputMap, Map<String, Object> outputMap) {
        super(inputPath, null, inputMap, outputMap);
    }

    public void isNotNull() throws Exception {
        throw new Exception(String.format("Value with path: [%s] is null", getInputPath()));
    }

    @Override
    public void insert(String outputPath, Class outputType) throws Exception {
        throw new Exception(String.format("Value with path: [%s] is null and cannot be inserted as [%s]", getInputPath(), outputType.getTypeName()));
    }
}
