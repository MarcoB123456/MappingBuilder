package org.mapper.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.mapper.builder.MappingBuilder;

import java.util.HashMap;
import java.util.Map;

public class OrderMapper {
    public String map(String payload) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
        Map<String, Object> inputMap = objectMapper.readValue(payload, HashMap.class);

        MappingBuilder mappingBuilder = new MappingBuilder(inputMap, new HashMap<>());
        mappingBuilder.startDataMap().setValue("firstName").insertValue("first_name", null);
        mappingBuilder.startDataMap().setValue("lastName").insertValue("last_name", null);
        mappingBuilder.startDataMap().setValue("address/street").split(" ").insertValue("address/street", 0, null).insertValue("address/number", 1, null);
        mappingBuilder.startDataMap().setValue("address/city").insertValue("address/city", null);
        mappingBuilder.startDataMap().setValue("nothing").insertValue("nothing", null);
        mappingBuilder.startDataMap().setValue("address/country").notNull().insertValue("address/country", null);
        mappingBuilder.startDataMap().setValue("processed").insertValue("processed", "BOOLEAN");
        mappingBuilder.startDataMap().setValue("title").contains("The esteemed").subString(0, 10).insertValue("title", null);

        if ((mappingBuilder.startPredicateMapper("conditional/use_a").notNull() && mappingBuilder.startPredicateMapper("conditional/use_a").isEqualTo(true))) {
            mappingBuilder.startDataMap().setValue("conditional/conditional_a").insertValue("conditional/result", null);
        } else {
            mappingBuilder.startDataMap().setValue("conditional/conditional_b").insertValue("conditional/result", null);
        }

        return mappingBuilder.build(objectMapper);
    }
}
