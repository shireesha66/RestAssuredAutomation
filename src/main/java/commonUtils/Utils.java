package commonUtils;

import io.restassured.response.Response;
import testData.AddUsers;

import static io.restassured.module.jsv.JsonSchemaValidator.*;
import java.io.File;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
	public static void validateSchema(Response response, String schemaFilePath) {
		
		response.then().assertThat().body(matchesJsonSchema(new File(schemaFilePath)));		
	}
	
	//converting an obj to json String
	public static String serializeData(Object obj) throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		String value = om.writeValueAsString(obj );
		return value;
		
	}
	
	// converting json String to an object
	public static AddUsers deserializeData(String Json) throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
	    AddUsers value = om.readValue(Json, AddUsers.class);
		return value;
		
	}

}
