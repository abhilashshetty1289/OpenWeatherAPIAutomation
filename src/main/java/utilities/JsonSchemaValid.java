package utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonValidator;

import java.io.IOException;

public class JsonSchemaValid {
    public static boolean validate(String res, String fileName) throws ProcessingException, IOException {

        String jsonSchema = ReadJsonSchema.readFile(fileName);
        final JsonNode data = JsonLoader.fromString(res);
        final JsonNode schema = JsonLoader.fromString(jsonSchema);

        final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        JsonValidator validator = factory.getValidator();

        ProcessingReport report = validator.validate(schema, data);
        return (report.isSuccess());
    }
}
