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

class ConditionalTest {

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
    class notNull {

        @Test
        void testNonNull() throws Exception {
            mappingBuilder.startDataMap().setValue("firstName").notNull().insertValue("first_name", null);
            String result = build();

            assertEquals(normalize("""
                    {
                      "first_name" : "Marco"
                    }"""), normalize(result));


            System.out.println("================== RESULT ==================");
            System.out.println(result);
        }

        @Test
        void testNull() {
            String message = assertThrows(Exception.class,
                    () -> mappingBuilder.startDataMap().setValue("nullField").notNull().insertValue("null_field", null)).getMessage();

            assertEquals("Value with path: [nullField] is null", message);
        }
    }

    @Nested
    class contains {

        @Test
        void testContains() throws Exception {
            mappingBuilder.startDataMap().setValue("title").contains("The esteemed").insertValue("title", null);
            String result = build();

            assertEquals(normalize("""
                    {
                      "title" : "The esteemed venerable buddy guy"
                    }"""), normalize(result));


            System.out.println("================== RESULT ==================");
            System.out.println(result);
        }

        @Test
        void testContainsIgnoreCase() throws Exception {
            mappingBuilder.startDataMap().setValue("title").containsIgnoreCase("the esteemed").insertValue("title", null);
            String result = build();

            assertEquals(normalize("""
                    {
                      "title" : "The esteemed venerable buddy guy"
                    }"""), normalize(result));


            System.out.println("================== RESULT ==================");
            System.out.println(result);
        }

        @Test
        void testNotContains() throws Exception {
            String message = assertThrows(Exception.class,
                    () -> mappingBuilder.startDataMap().setValue("title").contains("Hello world").insertValue("title", null)).getMessage();

            assertEquals("Value: [The esteemed venerable buddy guy] from path: [title] does not contain: [Hello world]", message);
        }

        @Test
        void testNotContainsIgnoreCase() throws Exception {
            String message = assertThrows(Exception.class,
                    () -> mappingBuilder.startDataMap().setValue("title").containsIgnoreCase("Hello world").insertValue("title", null)).getMessage();

            assertEquals("Value: [The esteemed venerable buddy guy] from path: [title] does not containIgnoreCase: [Hello world]", message);
        }
    }

    @Nested
    class equals {

        @Test
        void testEquals() throws Exception {
            mappingBuilder.startDataMap().setValue("title").isEqualTo("The esteemed venerable buddy guy").insertValue("title", null);
            String result = build();

            assertEquals(normalize("""
                    {
                      "title" : "The esteemed venerable buddy guy"
                    }"""), normalize(result));


            System.out.println("================== RESULT ==================");
            System.out.println(result);
        }

        @Test
        void testNotEquals() {
            String message = assertThrows(Exception.class,
                    () -> mappingBuilder.startDataMap().setValue("title").isEqualTo("Hello world").insertValue("title", null)).getMessage();

            assertEquals("Value: [The esteemed venerable buddy guy] does not equal: [Hello world]", message);
        }

        @Test
        void testNotEqualsIgnoreCase() {
            String message = assertThrows(Exception.class,
                    () -> mappingBuilder.startDataMap().setValue("title").isEqualToIgnoreCase("Hello world").insertValue("title", null)).getMessage();

            assertEquals("Value: [The esteemed venerable buddy guy] does not equalIgnoreCase: [Hello world]", message);
        }

        @Test
        void testEqualsIgnoreCase() throws Exception {
            mappingBuilder.startDataMap().setValue("title").isEqualToIgnoreCase("The estEemeD veNerablE BUDDY guy").insertValue("title", null);
            String result = build();

            assertEquals(normalize("""
                    {
                      "title" : "The esteemed venerable buddy guy"
                    }"""), normalize(result));


            System.out.println("================== RESULT ==================");
            System.out.println(result);
        }
    }

    @Nested
    class isType {

        @Test
        void testIsTypeString() throws Exception {
            mappingBuilder.startDataMap().setValue("title").isType(String.class).insertValue("title", null);
            String result = build();

            assertEquals(normalize("""
                    {
                      "title" : "The esteemed venerable buddy guy"
                    }"""), normalize(result));


            System.out.println("================== RESULT ==================");
            System.out.println(result);
        }

        @Test
        void testIsWrongType() {
            String message = assertThrows(Exception.class,
                    () -> mappingBuilder.startDataMap().setValue("title").isType(Integer.class).insertValue("title", null)).getMessage();

            assertEquals("Value: [The esteemed venerable buddy guy] from path: [title] is not of type: [java.lang.Integer]", message);
        }
    }


    private String normalize(String input) {
        return input.replaceAll("[\\r\\n]+", "");
    }

    private String build() throws JsonProcessingException {
        return this.mappingBuilder.build(this.objectMapper);
    }
}