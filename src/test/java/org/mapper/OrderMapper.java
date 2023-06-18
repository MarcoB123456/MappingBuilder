package org.mapper;

import org.mapper.builder.mapper.MappingBuilder;
import org.mapper.builder.mapper.Mapper;

public class OrderMapper extends MappingBuilder {
    public String map(String payload) throws Exception {
        init(payload);

        Mapper read = read("address.city");

        read("firstName").insert("first_name");
        read("lastName").insert("last_name");
        readString("address.street").split(" ").insert("address.street", 0).insert("address.number", 1);
        read("address.city").insert("address.city");
        read("nothing").insert("nothing");
        read("address.country").isNotNull().insert("address.country");
        read("processed").insert("processed", Boolean.class);
        readString("title").contains("The esteemed").subString(0, 10).insert("title");

        if ((predicate("conditional.use_a").notNull() && predicate("conditional.use_a").isEqualTo(true))) {
            read("conditional.conditional_a").insert("conditional.result");
        } else {
            read("conditional.conditional_b").insert("conditional.result");
        }

        return build();
    }
}
