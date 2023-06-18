package org.mapper.builder.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IOHelper {

    public static void insertValue(Map<String, Object> outputMap, String outputPath, Object value, Class type) throws Exception {
        String[] outputNamePath = outputPath.split("\\.");

        if (type != null) {
            value = convertValueTo(value, type);
        }

        if (outputNamePath.length == 1) {
            outputMap.put(outputPath, value);
            return;
        }

        Map<String, Object> currMap = null;
        for (int i = 0; i < outputNamePath.length; i++) {
            if (i == outputNamePath.length - 1) {
                currMap.put(outputNamePath[i], value);
            } else {
                if (outputMap.containsKey(outputNamePath[i])) {
                    currMap = (Map<String, Object>) outputMap.get(outputNamePath[i]);
                } else if (currMap != null && currMap.containsKey(outputNamePath[i])) {
                    currMap = (Map<String, Object>) outputMap.get(outputNamePath[i]);
                } else {
                    currMap = new HashMap<>();
                    outputMap.put(outputNamePath[i], currMap);
                }
            }
        }
    }

    public static Object convertValueTo(Object value, Class type) throws Exception {
        if (value == null || value.getClass().equals(type)) {
            return value;
        }

        try {
            if (type.equals(String.class)) {
                value = String.valueOf(value);
            } else if (type.equals(Integer.class)) {
                value = Integer.valueOf(String.valueOf(value));
            } else if (type.equals(Boolean.class)) {
                value = Boolean.valueOf(String.valueOf(value));
            } else if (type.equals(List.class)) {
                value = List.of(value);
            } else {
                throw new Exception("Type of: " + type.getTypeName() + " is not supported");
            }
        } catch (Exception ex) {
            throw new Exception("Failed to convert value to " + type, ex);
        }


        return value;
    }

    public static Object getValue(Map<String, Object> inputMap, String inputPath) {
        String[] inputNamePath = inputPath.split("\\.");

        if (inputNamePath.length == 1) {
            return inputMap.get(inputPath);
        }

        Map<String, Object> currMap = null;
        for (String s : inputNamePath) {
            if (currMap != null) {
                return currMap.get(s);
            } else {
                currMap = (Map<String, Object>) inputMap.get(s);
            }
        }

        return null;
    }

    public static Object isString(Object value) {
        if (value instanceof String) {
            return "\"" + value + "\"";
        } else {
            return value;
        }
    }
}
