package br.upe.booklubapi.utils;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    public static String convertMapToJson(Map<String, String> map) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting map to JSON", e);
        }
    }
}
