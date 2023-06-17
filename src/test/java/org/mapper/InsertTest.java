package org.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mapper.test.MappingTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InsertTest extends MappingTest {

    String json = """
            {
              "firstName": "Marco",
              "lastName": "Bergsma",
              "age": 23,
              "value": "100",
              "title": "The esteemed venerable buddy guy",
              "processed": false,
              "boolValue": "False",
              "types": ["A", "B", "C"],
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
    class Default {

        @Test
        void testInsert() throws Exception {
            read("firstName").insert("first_name");
            read("lastName").insert("last_name");
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
            read("firstName").insert("first_name");
            read("lastName").insert("last_name");
            insert("middle_name", "Jeffrey");
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
            read("middleName").insert("middle_name");
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
            read("age").insert("age");
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
            read("processed").insert("processed");
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
            read("address.city").insert("address.city");
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

        @Test
        void testListInsert() throws Exception {
            read("types").insert("types");
            String result = build();

            assertEquals(normalize("""
                    {
                      "types" : [ "A", "B", "C" ]
                    }"""), normalize(result));


            System.out.println("================== RESULT ==================");
            System.out.println(result);
        }



    }

    @Nested
    class OutputType {

        @Test
        void testStringInsert() throws Exception {
            read("processed").insert("processed", String.class);
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
            read("value").insert("value", Integer.class);
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
            read("boolValue").insert("boolValue", Boolean.class);
            String result = build();

            assertEquals(normalize("""
                    {
                      "boolValue" : false
                    }"""), normalize(result));


            System.out.println("================== RESULT ==================");
            System.out.println(result);
        }

        @Test
        void testListOneValueInsert() throws Exception {
            read("firstName").insert("firstNameList", List.class);
            String result = build();

            assertEquals(normalize("""
                    {
                      "firstNameList" : [ "Marco" ]
                    }"""), normalize(result));


            System.out.println("================== RESULT ==================");
            System.out.println(result);
        }

    }

    private String normalize(String input) {
        return input.replaceAll("[\\r\\n]+", "");
    }
}