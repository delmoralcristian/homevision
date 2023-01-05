package homevision.delmoralcristian.houses.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSyntaxException;
import homevision.delmoralcristian.houses.exceptions.InvalidRequestException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static homevision.delmoralcristian.houses.enums.CommonMessage.INVALID_PAYLOAD_FORMAT;

public class JsonUtils {

    private static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

    /**
     * Thread safe singleton
     */
    public enum GsonHelper {
        INSTANCE;
        private final Gson gson;

        GsonHelper() {
            gson = new GsonBuilder().serializeNulls().registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> {
                DateTimeFormatter formatter;
                formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
                return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(), formatter);
            }).create();
        }

        public Gson getGson() {
            return gson;
        }

    }

    public static <T> T fromJson(String json, Class<T> zClass) {
        return GsonHelper.INSTANCE.getGson().fromJson(json, zClass);
    }


    public static <T> T fromJsonRequest(String request, Class<T> zClass) {
        try {
            T requestDto = fromJson(request, zClass);
            return requestDto;
        } catch (JsonSyntaxException e) {
            throw new InvalidRequestException(INVALID_PAYLOAD_FORMAT.getMessage());
        }
    }

}
