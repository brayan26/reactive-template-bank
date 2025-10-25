package com.lulobank.insurance.commons.util;

import java.util.LinkedHashMap;
import java.util.Map;

public final class BuildErrorResponseUtil {
    private BuildErrorResponseUtil() {
    }

    public static Map<String, Object> build(final String code, final String errorMessage) {
        Map<String, Object> parent = new LinkedHashMap<>();
        Map<String, String> error = new LinkedHashMap<>();
        error.put("code", code);
        error.put("message", errorMessage);

        parent.put("error", error);
        return parent;
    }
}
