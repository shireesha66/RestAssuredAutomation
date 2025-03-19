package restUtils;

import java.io.IOException;
import java.util.HashMap;

import commonUtils.DataUtils;
import constants.FileConstants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;



public class RequestUtils  {
	 String token = null;
	
	public RequestUtils() throws IOException {
		RestAssured.baseURI= "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
		if(token==null) {
			token=this.getToken();
		}
	}
	
	public String getToken() throws IOException {
		
		String loginTestData=DataUtils.readJsonFileToString(FileConstants.LOGIN_TEST_DATA_FILE_PATH);
		Object payload = DataUtils.readTestDataFromJsonString(loginTestData, "$.qa");
		
		String endpoint="/login";
		Response res=RestAssured.given().header("Content-type",ContentType.JSON).when().body(payload).post(endpoint);
		return res.jsonPath().getString("[0].token");
	   
		
	}
	
	
	public Response postRequest(String payload, String endpoint) {
		return RestAssured.given().header("Content-type",ContentType.JSON).header("token", token).when().body(payload).post(endpoint);
	   
	}
	
	public Response postRequest(Object payload, String endpoint) {
		return RestAssured.given().header("Content-type",ContentType.JSON).header("token", token).when().body(payload).post(endpoint);
	   
	}
	
	public Response getRequest(String endpoint) {
		return RestAssured.given().header("Content-type",ContentType.JSON).header("token", token).when().get(endpoint);
	   
	}

	public Response deleteRequest(String endpoint, HashMap<String, String> payload) {
		
		return RestAssured.given().header("Content-type",ContentType.JSON).header("token", token).when().body(payload).delete(endpoint);
			   
	}
	
   public Response UpdateRequest(String endpoint,String payload) {
		
		return RestAssured.given().header("Content-type",ContentType.JSON).header("token", token).when().body(payload).put(endpoint);
			   
	}

}
