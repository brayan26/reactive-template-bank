package com.lulobank.insurance.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class BuildErrorResponseUtil {
    public static Map<String, Object> build(String code, String errorMessage) {
        Map<String, Object> parent = new LinkedHashMap<>();
        Map<String, String> error = new LinkedHashMap<>();
        error.put("code", code);
        error.put("message", errorMessage);

        parent.put("error", error);
        return parent;
    }
}
