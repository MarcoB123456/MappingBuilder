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

class InsertTest {

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
    class Default {

        @Test
        void testInsert() throws Exception {
            mappingBuilder.startDataMap().setValue("firstName").insertValue("first_name", null);
            mappingBuilder.startDataMap().setValue("lastName").insertValue("last_name", null);
            String result = build();

            assertEquals(normalize("""
                    {
                      "first_name" : "Marco",
                      "last_name" : "Bergsma"
                    }"""), normalize(result));


            System.out.println("================== RESULT ==================");
            System.out.println(result);
        }

        @Test
        void testInsertConstant() throws Exception {
            mappingBuilder.startDataMap().setValue("firstName").insertValue("first_name", null);
            mappingBuilder.startDataMap().setValue("lastName").insertValue("last_name", null);
            mappingBuilder.startDataMap().insertValue("middle_name", "Jeffrey", null);
            String result = build();

            assertEquals(normalize("""
                    {
                      "first_name" : "Marco",
                      "last_name" : "Bergsma",
                      "middle_name" : "Jeffrey"
                    }"""), normalize(result));


            System.out.println("================== RESULT ==================");
            System.out.println(result);
        }

        @Test
        void testNullInputInsert() throws Exception {
            mappingBuilder.startDataMap().setValue("middleName").insertValue("middle_name", null);
            String result = build();

            assertEquals(normalize("""
                    {
                      "middle_name" : null
                    }"""), normalize(result));


            System.out.println("================== RESULT ==================");
            System.out.println(result);
        }

        @Test
        void testNumericInsert() throws Exception {
            mappingBuilder.startDataMap().setValue("age").insertValue("age", null);
            String result = build();

            assertEquals(normalize("""
                    {
                      "age" : 23
                    }"""), normalize(result));


            System.out.println("================== RESULT ==================");
            System.out.println(result);
        }

        @Test
        void testBooleanInsert() throws Exception {
            mappingBuilder.startDataMap().setValue("processed").insertValue("processed", null);
            String result = build();

            assertEquals(normalize("""
                    {
                      "processed" : false
                    }"""), normalize(result));


            System.out.println("================== RESULT ==================");
            System.out.println(result);
        }

        @Test
        void testMultiPathInsert() throws Exception {
            mappingBuilder.startDataMap().setValue("address/city").insertValue("address/city", null);
            String result = build();

            assertEquals(normalize("""
                    {
                      "address" : {
                        "city" : "Haarlem"
                      }
                    }"""), normalize(result));


            System.out.println("================== RESULT ==================");
            System.out.println(result);
        }

    }

    @Nested
    class OutputType {

        @Test
        void testStringInsert() throws Exception {
            mappingBuilder.startDataMap().setValue("processed").insertValue("processed", "STRING");
            String result = build();

            assertEquals(normalize("""
                    {
                      "processed" : "false"
                    }"""), normalize(result));


            System.out.println("================== RESULT ==================");
            System.out.println(result);
        }

        @Test
        void testIntegerInsert() throws Exception {
            mappingBuilder.startDataMap().setValue("value").insertValue("value", "INTEGER");
            String result = build();

            assertEquals(normalize("""
                    {
                      "value" : 100
                    }"""), normalize(result));


            System.out.println("================== RESULT ==================");
            System.out.println(result);
        }

        @Test
        void testBooleanInsert() throws Exception {
            mappingBuilder.startDataMap().setValue("boolValue").insertValue("boolValue", "BOOLEAN");
            String result = build();

            assertEquals(normalize("""
                    {
                      "boolValue" : false
                    }"""), normalize(result));


            System.out.println("================== RESULT ==================");
            System.out.println(result);
        }

    }

    private String normalize(String input) {
        return input.replaceAll("[\\r\\n]+", "");
    }

    private String build() throws JsonProcessingException {
        return this.mappingBuilder.build(this.objectMapper);
    }
}