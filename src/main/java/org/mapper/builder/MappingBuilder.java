package org.mapper.builder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class MappingBuilder {

    private final Map<String, Object> inputMap;
    private final Map<String, Object> outputMap;

    public MappingBuilder(Map<String, Object> inputMap, Map<String, Object> outputMap) {
        this.inputMap = inputMap;
        this.outputMap = outputMap;
    }

    public PredicateMapper startPredicateMapper(String inputPath) {
        return new PredicateMapper(this.inputMap, inputPath);
    }

    public DataMapper startDataMap() {
        return new DataMapper(this.inputMap, this.outputMap);
    }

    public String build(ObjectMapper objectMapper) throws JsonProcessingException {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.outputMap);
    }
}
