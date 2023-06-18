package org.mapper.builder.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.mapper.builder.helper.IOHelper;
import org.mapper.builder.mapper.type.*;

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
        Object value = IOHelper.getValue(this.inputMap, inputPath);
        return new DataMapper(inputPath, value, this.inputMap, this.outputMap);
    }

    public StringMapper readString(String inputPath) throws Exception {
        Object value = IOHelper.getValue(this.inputMap, inputPath);
        return new StringMapper(inputPath, IOHelper.convertValueTo(value, String.class), this.inputMap, this.outputMap);
    }

    public IntegerMapper readInteger(String inputPath) throws Exception {
        Object value = IOHelper.getValue(this.inputMap, inputPath);
        return new IntegerMapper(inputPath, IOHelper.convertValueTo(value, String.class), this.inputMap, this.outputMap);
    }

    public ListMapper readList(String inputPath) throws Exception {
        Object value = IOHelper.getValue(this.inputMap, inputPath);
        return new ListMapper(inputPath, IOHelper.convertValueTo(value, String.class), this.inputMap, this.outputMap);
    }

    public BooleanMapper readBoolean(String inputPath) throws Exception {
        Object value = IOHelper.getValue(this.inputMap, inputPath);
        return new BooleanMapper(inputPath, IOHelper.convertValueTo(value, String.class), this.inputMap, this.outputMap);
    }


    public void insert(String outputPath, String outputValue, Class outputType) throws Exception {
        IOHelper.insertValue(this.outputMap, outputPath, outputValue, outputType);
    }

    public void insert(String outputPath, String outputValue) throws Exception {
        insert(outputPath, outputValue, null);
    }

    public PredicateMapper predicate(String inputPath) {
        return new PredicateMapper(IOHelper.getValue(this.inputMap, inputPath));
    }

    public String build() throws JsonProcessingException {
        return this.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.outputMap);
    }
}
