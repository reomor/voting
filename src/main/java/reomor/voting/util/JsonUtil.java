package reomor.voting.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectReader;

import java.io.IOException;
import java.util.List;

import static reomor.voting.web.json.JacksonObjectMapper.getMapper;

public class JsonUtil {

    public static <T> String writeValue(T obj) {
        try {
            return getMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Error while writing object to JSON:\n'" + obj + "'", e);
        }
    }

    public static <T> T readValue(String json, Class<T> clazz) {
        try {
            return getMapper().readValue(json, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error while reading object from JSON:\n'" + json + "'", e);
        }
    }

    public static <T> List<T> readValues(String json, Class<T> clazz) {
        final ObjectReader objectReader = getMapper().readerFor(clazz);
        try {
            return objectReader.<T>readValues(json).readAll();
        } catch (IOException e) {
            throw new IllegalArgumentException("Error while reading objects from JSON:\n'" + json + "'", e);
        }
    }
}
