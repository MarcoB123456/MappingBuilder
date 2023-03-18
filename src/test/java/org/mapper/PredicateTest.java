package org.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapper.builder.MappingBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PredicateTest {

    String json = """
            {
              "firstName": "Marco",
              "lastName": "Bergsma",
              "age": 23,
              "value": "100",
              "title": "The esteemed venerable buddy guy",
              "processed": false,
              "boolValue": "False",
              "address": {
                "street": "Rijksstraatweg 104B",
                "city": "Haarlem",
                "country": "The Netherlands"
              }
            }""";

    private ObjectMapper objectMapper;
    private MappingBuilder mappingBuilder;


    @BeforeEach
    void setup() throws JsonProcessingException {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
        Map<String, Object> inputMap = this.objectMapper.readValue(json, HashMap.class);
        this.mappingBuilder = new MappingBuilder(inputMap, new HashMap<>());
    }


    @Test
    void test() {
        boolean result = mappingBuilder.startPredicateMapper("firstName").equals("Marco");

        assertEquals(true, result);
    }



    private String normalize(String input) {
        return input.replaceAll("[\\r\\n]+", "");
    }

    private String build() throws JsonProcessingException {
        return this.mappingBuilder.build(this.objectMapper);
    }
}