package utilities;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadJsonSchema {
    static String readFile(String filename) throws IOException {
        String path = "src/main/resources/json-schema/" + filename;
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}


