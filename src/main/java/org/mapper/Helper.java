package org.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Helper {

    public static void insertValue(Map<String, Object> outputMap, String outputName, Object inputValue, Class outputType) throws Exception {
        String[] outputNamePath = outputName.split("\\.");

        if (outputType != null) {
            inputValue = convertValueTo(inputValue, outputType);
        }

        if (outputNamePath.length == 1) {
            outputMap.put(outputName, inputValue);
            return;
        }

        Map<String, Object> currMap = null;
        for (int i = 0; i < outputNamePath.length; i++) {
            if (i == outputNamePath.length - 1) {
                currMap.put(outputNamePath[i], inputValue);
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

    private static Object convertValueTo(Object inputValue, Class outputType) throws Exception {
        try {
            if (outputType.equals(String.class)) {
                inputValue = String.valueOf(inputValue);
            } else if (outputType.equals(Integer.class)) {
                inputValue = Integer.valueOf(String.valueOf(inputValue));
            } else if (outputType.equals(Boolean.class)) {
                inputValue = Boolean.valueOf(String.valueOf(inputValue));
            } else if (outputType.equals(List.class)) {
                inputValue = List.of(inputValue);
            } else {
                throw new Exception("OutputType of: " + outputType.getTypeName() + " is not supported");
            }
        } catch (Exception ex) {
            throw new Exception("Failed to convert value to " + outputType, ex);
        }


        return inputValue;
    }

    public static Object getValue(Map<String, Object> inputMap, String inputName) {
        String[] inputNamePath = inputName.split("\\.");

        if (inputNamePath.length == 1) {
            return inputMap.get(inputName);
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
