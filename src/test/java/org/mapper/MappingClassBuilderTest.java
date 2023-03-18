package org.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mapper.config.GuiToBuilder;
import org.mapper.config.gui.Configuration;
import org.mapper.output.OrderMapper;

import java.io.File;
import java.nio.charset.StandardCharsets;

class MappingClassBuilderTest {

    @Test
    @Disabled
    void testMapper() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Configuration configuration = objectMapper.readValue(FileUtils.readFileToString(new File("src/main/resources/config/config.json"), StandardCharsets.UTF_8), Configuration.class);

        MappingClassBuilder mappingClassBuilder = new MappingClassBuilder();
        mappingClassBuilder.build(GuiToBuilder.toBuilderConfiguration(configuration));
    }

    @Test
    @Disabled
    void testResult() throws Exception {
        OrderMapper orderMapper = new OrderMapper();
        String mappingResult = orderMapper.map(FileUtils.readFileToString(new File("src/test/resources/input.json"), StandardCharsets.UTF_8));

        System.out.println(mappingResult);
    }

}