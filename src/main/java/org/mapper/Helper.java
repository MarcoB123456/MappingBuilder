package org.mapper;

import java.util.HashMap;
import java.util.Map;

public class Helper {

    public static void insertValue(Map<String, Object> outputMap, String outputName, Object inputValue, String outputType) throws Exception {
        String[] outputNamePath = outputName.split("/");

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

    private static Object convertValueTo(Object inputValue, String outputType) throws Exception {
        try {

            switch (outputType) {
                case "STRING":
                    inputValue = String.valueOf(inputValue);
                    break;
                case "INTEGER":
                    inputValue = Integer.valueOf(String.valueOf(inputValue));
                    break;
                case "BOOLEAN":
                    inputValue = Boolean.valueOf(String.valueOf(inputValue));
                    break;
            }
        } catch (Exception ex) {
            throw new Exception("Failed to convert value to " + outputType);
        }


        return inputValue;
    }

    public static Object getValue(Map<String, Object> inputMap, String inputName) {
        String[] inputNamePath = inputName.split("/");

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
