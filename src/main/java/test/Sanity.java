package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.JsonPath;

import commonUtils.Utils;
import io.restassured.response.Response;
import restUtils.RequestUtils;
import testData.AddUsers;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class Sanity  {
	RequestUtils ru = null;
	ArrayList<String> myRecordIds = new ArrayList<String>();
	ArrayList<String> Ids = new ArrayList<String>();
	
	@BeforeClass
	public void setup() throws IOException {
		ru = new RequestUtils();
	}
	
	
	//@Test(priority=1)
	public void getusers_tc02() {
	Response getUsers= ru.getRequest("/getdata");
	Assert.assertEquals(getUsers.statusCode(), 200);
	String responseJson = getUsers.asString(); 
	List<String> userids= JsonPath.read(responseJson,"$[*].userid");   // in restAssured dollor not required but in jayway library we need
	List<String> ids= JsonPath.read(responseJson,"$[*].id");
    int i = 0;
	for(String userid : userids) {
		if(userid.equals("WocHzcW4PZXLgmS6NBQd")&&i<=100){
			myRecordIds.add(ids.get(i));
		}
		i++; 
	}
	System.out.println(myRecordIds);
	}
	
	
	
	
	

	//@Test(dataProvider = "deleteRecords",priority=2)
	public void deleteusers_tc03(String recordId) {
	HashMap<String, String> payload = new HashMap<String, String>();
	payload.put("userid", "WocHzcW4PZXLgmS6NBQd");
	payload.put("id",recordId);
	Response deleteUsers = ru.deleteRequest( "/deleteData",payload);
	if(deleteUsers.statusCode()==200) {
		System.out.println("record :"+ recordId + "is deleted");
	}else {
		System.out.println("error occured while deleting");
	}
	}
	
	
	
	//@Test(priority = 3)
	public void addUser_tc04() throws JsonProcessingException {
		AddUsers user1 = new AddUsers("TA-1123423","7","54653","254037");
		String user= Utils.serializeData(user1);
		System.out.println(user);
		Response addUsers= ru.postRequest(user, "/addData");
		System.out.println(addUsers.statusCode());
		
	}
	
	@Test
	public void getusers_tc03() throws JsonProcessingException {
		Response getUsers= ru.getRequest("/getdata");
		Assert.assertEquals(getUsers.statusCode(), 200);
		String responseJson = getUsers.asString(); 
		getUsers.jsonPath().getString("[0]");   // in restAssured dollor not required but in jayway library we need
		AddUsers u =Utils.deserializeData(getUsers.jsonPath().getString("[0]"));
		
		}
	// we can use hamcrest matches for api testing using assertThat(matcher)
	
	//In TestNG, a DataProvider is a method used to supply test data to a test method. It allows running the same test multiple times with different data sets.
	//in data provider we can not return arraylist, so we are using array list to Array using "toArray" by storing  obj type 
	
	@DataProvider(name="deleteRecords")
	public Object[] deleteData(){
		
		return Ids.toArray();
		
	}
	
	
	

}
