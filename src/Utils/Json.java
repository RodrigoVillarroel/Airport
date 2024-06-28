package Utils;
import Model.Airport;
import java.io.File;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Json {
    private static final ObjectMapper mapper = new ObjectMapper();


    public Json() {
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static Airport loadFromJson(String path) {
        try {
            File file = new File(path);
            if (!file.exists()) return new Airport();
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
            mapper.writeValue(file, airport);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}