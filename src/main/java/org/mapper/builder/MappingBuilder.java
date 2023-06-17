package org.mapper.builder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.mapper.Helper;

import java.util.HashMap;
import java.util.Map;

public abstract class MappingBuilder {

    private Map<String, Object> inputMap;
    private Map<String, Object> outputMap;

    private ObjectMapper objectMapper;


    public abstract String map(String payload) throws Exception;

    public void init(String payload) throws JsonProcessingException {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
        this.inputMap = this.objectMapper.readValue(payload, HashMap.class);
        this.outputMap = new HashMap<>();
    }

    public DataMapper read(String inputPath) {
        return new DataMapper(this.inputMap, this.outputMap, inputPath);
    }

    public void insert(String outputPath, String outputValue, Class outputType) throws Exception {
        Helper.insertValue(this.outputMap, outputPath, outputValue, outputType);
    }

    public void insert(String outputPath, String outputValue) throws Exception {
        insert(outputPath, outputValue, null);
    }

    public PredicateMapper predicate(String inputPath) {
        return new PredicateMapper(this.inputMap, inputPath);
    }

    public String build() throws JsonProcessingException {
        return this.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.outputMap);
    }
}
