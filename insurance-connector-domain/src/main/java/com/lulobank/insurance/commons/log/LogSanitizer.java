package com.lulobank.insurance.commons.log;

import com.lulobank.insurance.commons.annotations.Sensitive;

import java.lang.reflect.Method;
import java.lang.reflect.RecordComponent;
import java.util.Arrays;

public final class LogSanitizer {

    private LogSanitizer() {
    }

    public static String safeLog(final Object record) {
        if (record == null) {
            return "null";
        }

        Class<?> clazz = record.getClass();
        if (!clazz.isRecord()) {
            return record.toString();
        }

        StringBuilder sb = new StringBuilder(clazz.getSimpleName()).append("[");
        RecordComponent[] components = clazz.getRecordComponents();

        for (int i = 0; i < components.length; i++) {
            RecordComponent component = components[i];
            String name = component.getName();
            boolean isSensitive = Arrays.stream(component.getAnnotations())
                    .anyMatch(a -> a.annotationType().equals(Sensitive.class));

            try {
                Method accessor = component.getAccessor();
                final Object value = accessor.invoke(record);

                if (isSensitive) {
                    sb.append(maskSensitive(value));
                } else if (value != null && value.getClass().isRecord()) {
                    sb.append(safeLog(value));
                } else {
                    sb.append(value);
                }
            } catch (Exception e) {
                sb.append(name).append("=ERROR");
            }

            if (i < components.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private static String maskSensitive(final Object value) {
        if (value == null) {
            return "***";
        }
        String str = value.toString();
        if (str.length() <= 4) {
            return "***";
        }
        return "***" + str.substring(str.length() - 4);
    }

}
