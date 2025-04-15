package br.upe.booklubapi.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AttributeMapDeserializer extends JsonDeserializer<Map<String, String>> {
    @Override
    public Map<String, String> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        Map<String, String> result = new HashMap<>();
        JsonNode node = p.getCodec().readTree(p);

        Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            JsonNode valueNode = entry.getValue();

            if (valueNode.isArray() && valueNode.size() > 0) {
                result.put(entry.getKey(), valueNode.get(0).asText());
            } else if (valueNode.isTextual()) {
                result.put(entry.getKey(), valueNode.asText());
            }
        }

        return result;
    }
}
