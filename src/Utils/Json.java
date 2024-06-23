package Utils;

import Model.Airport;
import java.io.File;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Json {
    public static Airport loadFromJson(String path) {
        try {
            File file = new File(path);
            if (!file.exists()) return new Airport();
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(file, Airport.class);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new Airport();
    }

    public static void saveToJson(String path, Airport airport) {
        try {
            File file = new File(path);
            if (!file.exists()) return;
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, airport);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
