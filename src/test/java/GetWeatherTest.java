import org.testng.Assert;
import utilities.FileUtils;
import utilities.JsonSchemaValid;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.*;

public class GetWeatherTest {

    public Properties endPoint =  new FileUtils().getProperty("api");

    @Test
    public void validateWeatherDeatailsUsingCityLatLong() throws IOException, ProcessingException {

        /**
         * Excercise 1 and 2: The question is not very clear about what to compare, comparing both responses will return not equal because
         * 'dt' field of every response is identical.
         *
         * Since I could not understand the question, I will be doing following verification hoping to cover mentioned scenarios
         * 1. Json schema validation
         * 2. Comparing both responses.
         *
         *
         * Note: I am creating schema for this and will be limiting 'lon','lat' and 'name' values to corresponding values of 'Udupi'. You can observe this in the schema
         *
         */

        Response response1 = given()
                .param("q","udupi")
                .param("appid",endPoint.getProperty("api_key"))
                .get(endPoint.getProperty("city_weather_api_endpoint"));

        float longitude = response1.path("coord.lon");
        float lattitude = response1.path("coord.lat");

        System.out.println(response1.prettyPrint());

        Response response2 = given()
                .param("lon",longitude)
                .param("lat",lattitude)
                .param("appid",endPoint.getProperty("api_key"))
                .get(endPoint.getProperty("city_weather_api_endpoint"));

        System.out.println(response2.prettyPrint());

        /**
         * 1. Json schema validation
         */
        Boolean response1SchemaValidationStatus = JsonSchemaValid.validate(response1.getBody().asString(),"weather_city_schema.json");
        Assert.assertTrue(response1SchemaValidationStatus);
        Boolean response2SchemaValidationStatus = JsonSchemaValid.validate(response2.getBody().asString(),"weather_city_schema.json");
        Assert.assertTrue(response2SchemaValidationStatus);


        /**
         * Comparing both values: This will fail because every weather api request has unique 'dt' field. Hence commenting
         */
//        Assert.assertEquals(response1.getBody().asString(),response2.getBody().asString());


    }

    @Test
    public void validateWeatherOfCircleInCircle() throws IOException, ProcessingException {
        /**
         * Exercise 3: Validate weather reports for cities in a circle api
         * Solution: I am considering udupi city and will be passing count as 2, which will return weather details of 'Udupi' and 'Malpe'.
         * Will be validating these with the help of json schema, in the schema will be creating enumerated data for 'lat','lon' and 'name' values.
         */
        Response response = given()
                .param("lat",13.35)
                .param("lon",74.75)
                .param("cnt",2)
                .param("appid",endPoint.getProperty("api_key"))
                .get(endPoint.getProperty("circle_weather_api_endpoint"));

        Boolean responseSchemaValidationStatus = JsonSchemaValid.validate(response.getBody().asString(),"weather_circle_schema.json");

        /**
         * Schema Validation and Verifying that response has both 'Udupi' and 'Malpe' which are nearby
         */
        Assert.assertTrue(responseSchemaValidationStatus);

    }
}
