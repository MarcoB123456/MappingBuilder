package org.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mapper.builder.MappingBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ActionTest {

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


    @Nested
    class split {

        @Test
        void testSplit() throws Exception {
            mappingBuilder.startDataMap().setValue("address/street").split(" ")
                    .insertValue("address/street", 0, null)
                    .insertValue("address/number", 1, null);
            String result = build();

            assertEquals(normalize("""
                    {
                      "address" : {
                        "number" : "104B",
                        "street" : "Rijksstraatweg"
                      }
                    }"""), normalize(result));


            System.out.println("================== RESULT ==================");
            System.out.println(result);
        }

        @Test
        void testOutOfBoundSplit() {
            String message = assertThrows(Exception.class,
                    () -> mappingBuilder.startDataMap().setValue("address/street").split(" ")
                            .insertValue("address/street", 0, null)
                            .insertValue("address/number", 1, null)
                            .insertValue("address/additional", 2, null)).getMessage();

            assertEquals("Index [2] does not exist. Size after split is: [2]", message);
        }
    }


    @Nested
    class SplitNotNull {

        @Test
        void testNonNull() throws Exception {
            mappingBuilder.startDataMap().setValue("address/street").split(" ")
                    .notNull(0)
                    .notNull(1)
                    .insertValue("address/street", 0, null)
                    .insertValue("address/number", 1, null);
            String result = build();

            assertEquals(normalize("""
                    {
                      "address" : {
                        "number" : "104B",
                        "street" : "Rijksstraatweg"
                      }
                    }"""), normalize(result));


            System.out.println("================== RESULT ==================");
            System.out.println(result);
        }

        @Test
        void testNull() {
            String message = assertThrows(NullPointerException.class,
                    () -> mappingBuilder.startDataMap().setValue("nullValue").split(" ")
                            .notNull(0)).getMessage();

            assertEquals("Value with path: [nullValue] is null", message);
        }
    }

    private String normalize(String input) {
        return input.replaceAll("[\\r\\n]+", "");
    }

    private String build() throws JsonProcessingException {
        return this.mappingBuilder.build(this.objectMapper);
    }
}