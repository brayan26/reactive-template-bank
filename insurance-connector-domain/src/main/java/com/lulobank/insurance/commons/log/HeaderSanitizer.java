package com.lulobank.insurance.commons.log;

import org.springframework.http.HttpHeaders;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class HeaderSanitizer {
    private HeaderSanitizer() {
    }

    private static final Set<String> SENSITIVE_HEADERS = Set.of(
            "authorization", "x-api-key", "cookie", "set-cookie"
    );

    public static Map<String, String> safeLog(final HttpHeaders headers) {
        Map<String, String> sanitized = new HashMap<>();
        headers.forEach((key, values) -> {
            if (SENSITIVE_HEADERS.contains(key.toLowerCase())) {
                String masked = values.stream()
                        .map(HeaderSanitizer::maskValue)
                        .findFirst()
                        .orElse("***");
                sanitized.put(key, masked);
            } else {
                sanitized.put(key, String.join(",", values));
            }
        });
        return sanitized;
    }

    private static String maskValue(final String value) {
        if (value == null || value.length() <= 4) {
            return "***";
        }
        int visibleChars = 4;
        String visiblePart = value.substring(value.length() - visibleChars);
        return "***" + visiblePart;
    }

}
