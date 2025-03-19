package test;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Test;

import commonUtils.Utils;
import constants.FileConstants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Login  {
	

	static String token = null;
 
@Test
 public void login() throws FileNotFoundException, IOException {
	HashMap<String, String> payload = new HashMap<String, String>();
	payload.put("username", "jan2025.shirisha@tekarch.com");
	payload.put("password", "Admin123");
	
	RestAssured.baseURI = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
	
	//given = context,when-action,when-action
	
	Response loginResponse=RestAssured.given().header("Content-type",ContentType.JSON).when()
	.body(payload).post("/login");
	
	Utils.validateSchema(loginResponse, FileConstants.LOGIN_SCHEMA_FILE_PATH);
	System.out.println(loginResponse.statusCode());
	loginResponse.then().assertThat().statusCode(201);
	token = loginResponse.jsonPath().getString("[0].token");
	System.out.println(token);
	
}

	//@Test()
	public void getUsers() {
	RestAssured.baseURI = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";

    Response getUsersResponse=RestAssured.given().header("Content-type",ContentType.JSON).header("token",token)
	.get("/getdata");
    System.out.println(getUsersResponse.getHeaders());
	
    System.out.println(getUsersResponse.statusCode());
    getUsersResponse.then().assertThat().statusCode(200);
    getUsersResponse.prettyPrint();
	
	}
	
	//@Test()
	public void addUsers() {
	RestAssured.baseURI = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";

    Response addUsers=RestAssured.given().header("Content-type",ContentType.JSON).header("token",token).when()
    		.body("{\"accountno\": \"TA-1123423\", \"departmentno\": \"6\", \"salary\": \"54653\", \"pincode\":  \"25403\"}")
	.post("/addData");
    System.out.println(addUsers.getHeaders());
	
    System.out.println(addUsers.statusCode());
    addUsers.then().assertThat().statusCode(201);
    addUsers.prettyPrint();
	
	}
	
	//@Test()
	public void updateUsers() {
		RestAssured.baseURI = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";

	    Response updateUsers=RestAssured.given().header("Content-type",ContentType.JSON).header("token",token).when()
	    		.body("{\"accountno\":\"TA-6642331\",\"departmentno\":2,\"id\":\"lE0TI7S1jDnFopnnHgEN\",\"pincode\":333333,\"salary\":2,\"userid\":\"WocHzcW4PZXLgmS6NBQd\"\r\n"
	    				+ "}")
		.put("/updateData");
	    System.out.println(updateUsers.getHeaders());
		
	    System.out.println(updateUsers.statusCode());
	    updateUsers.then().assertThat().statusCode(200);
	    updateUsers.prettyPrint();
		
		}
	
	//@Test()
	public void deleteUser() {
		RestAssured.baseURI = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";

	    Response deleteUser=RestAssured.given().header("Content-type",ContentType.JSON).header("token",token).when()
	    		.body("{\"id\": \"PH4MXZSQSD5Ai06dj0Gh\", \"userid\": \"WocHzcW4PZXLgmS6NBQd\"}")
		.delete("/deleteData");
	    System.out.println(deleteUser.getHeaders());
		
	    System.out.println(deleteUser.statusCode());
	    deleteUser.then().assertThat().statusCode(200);
	    deleteUser.prettyPrint();
		
		}
	
	//@Test()
	public void logout() {
		RestAssured.baseURI = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";

	    Response logout=RestAssured.given().header("Content-type",ContentType.JSON).header("token",token).post("/logout");
	    System.out.println( logout.getHeaders());
		
	    System.out.println(logout.statusCode());
	    logout.then().assertThat().statusCode(200);
	    logout.prettyPrint();
		
		}
}
