package org.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mapper.test.MappingTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ActionTest extends MappingTest {

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
    class split {

        @Test
        void testSplit() throws Exception {
            read("address.street").split(" ")
                    .insert("address.street", 0)
                    .insert("address.number", 1);
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
                    () -> read("address.street").split(" ")
                            .insert("address.street", 0)
                            .insert("address.number", 1)
                            .insert("address.additional", 2)).getMessage();

            assertEquals("Index [2] does not exist. Size after split is: [2]", message);
        }
    }


    @Nested
    class SplitNotNull {

        @Test
        void testNonNull() throws Exception {
            read("address.street").split(" ")
                    .notNull(0)
                    .notNull(1)
                    .insert("address.street", 0)
                    .insert("address.number", 1);
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
                    () -> read("nullValue").split(" ")
                            .notNull(0)).getMessage();

            assertEquals("Value with path: [nullValue] is null", message);
        }
    }

    private String normalize(String input) {
        return input.replaceAll("[\\r\\n]+", "");
    }
}