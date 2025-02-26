package com.example.generic;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Session {
    private static final Map<String, Object> session = new HashMap<>();

    public static void add(String key, Object value) {
        session.put(key, value);
    }

   @SuppressWarnings("unchecked")
    public static <T> Optional<T> getData(String key) {
        try {
            return Optional.of((T) session.get(key));
        } catch (Exception e) {
            return Optional.empty();
        }

    }
}
