package org.mapper.builder.mapper.type;

import org.mapper.builder.mapper.Mapper;

import java.util.List;
import java.util.Map;

public class ListMapper extends Mapper<List> {
    public ListMapper(String inputPath, Object value, Map<String, Object> inputMap, Map<String, Object> outputMap) {
        super(inputPath, value, inputMap, outputMap);
    }
}
