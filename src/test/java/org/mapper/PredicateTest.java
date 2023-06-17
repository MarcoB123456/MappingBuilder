package org.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapper.test.MappingTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PredicateTest extends MappingTest {

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


    @Test
    void testIsEqualTo() {
        boolean result = predicate("firstName").isEqualTo("Marco");

        assertTrue(result);
    }



    private String normalize(String input) {
        return input.replaceAll("[\\r\\n]+", "");
    }
}