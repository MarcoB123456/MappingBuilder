package org.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mapper.test.MappingTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConditionalTest extends MappingTest {

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
    

    @BeforeEach
    void setup() throws JsonProcessingException {
        super.init(json);
    }


    @Nested
    class notNull {

        @Test
        void testNonNull() throws Exception {
            read("firstName").notNull().insert("first_name");
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
                    () -> read("nullField").notNull().insert("null_field")).getMessage();

            assertEquals("Value with path: [nullField] is null", message);
        }
    }

    @Nested
    class contains {

        @Test
        void testContains() throws Exception {
            read("title").contains("The esteemed").insert("title");
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
            read("title").containsIgnoreCase("the esteemed").insert("title");
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
                    () -> read("title").contains("Hello world").insert("title")).getMessage();

            assertEquals("Value: [The esteemed venerable buddy guy] from path: [title] does not contain: [Hello world]", message);
        }

        @Test
        void testNotContainsIgnoreCase() throws Exception {
            String message = assertThrows(Exception.class,
                    () -> read("title").containsIgnoreCase("Hello world").insert("title")).getMessage();

            assertEquals("Value: [The esteemed venerable buddy guy] from path: [title] does not containIgnoreCase: [Hello world]", message);
        }
    }

    @Nested
    class equals {

        @Test
        void testEquals() throws Exception {
            read("title").isEqualTo("The esteemed venerable buddy guy").insert("title");
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
                    () -> read("title").isEqualTo("Hello world").insert("title")).getMessage();

            assertEquals("Value: [The esteemed venerable buddy guy] does not equal: [Hello world]", message);
        }

        @Test
        void testNotEqualsIgnoreCase() {
            String message = assertThrows(Exception.class,
                    () -> read("title").isEqualToIgnoreCase("Hello world").insert("title")).getMessage();

            assertEquals("Value: [The esteemed venerable buddy guy] does not equalIgnoreCase: [Hello world]", message);
        }

        @Test
        void testEqualsIgnoreCase() throws Exception {
            read("title").isEqualToIgnoreCase("The estEemeD veNerablE BUDDY guy").insert("title");
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
            read("title").isType(String.class).insert("title");
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
                    () -> read("title").isType(Integer.class).insert("title")).getMessage();

            assertEquals("Value: [The esteemed venerable buddy guy] from path: [title] is not of type: [java.lang.Integer]", message);
        }
    }


    private String normalize(String input) {
        return input.replaceAll("[\\r\\n]+", "");
    }
}