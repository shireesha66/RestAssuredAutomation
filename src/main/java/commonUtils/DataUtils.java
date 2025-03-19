package commonUtils;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import com.jayway.jsonpath.JsonPath;

public class DataUtils {
	
	public static String readJsonFileToString(String path) throws IOException {
		byte [] data = Files.readAllBytes(Paths.get(path));
		return new String(data);
	}
	
	public static Object readTestDataFromJsonString(String jsonData, String jsonPath) {
		return JsonPath.read(jsonData, jsonPath);
		
	}

}
