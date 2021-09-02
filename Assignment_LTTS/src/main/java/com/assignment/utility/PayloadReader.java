package com.assignment.utility;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PayloadReader {
	
	public static String getPayload_create(String fileName) throws IOException  {
		
		File file = new File("src/test/resources/requestpayload/createavengers/" + fileName);
		Path path = file.toPath();
		StringBuilder sb = new StringBuilder();
		List<String> content = Files.readAllLines(path, Charset.defaultCharset());
		for (String string : content) {
			sb.append(string);
		}
		String jsonstring = sb.toString();
		
		return jsonstring;
	}
	
	public static String getPayload_modify(String fileName) throws IOException  {
		
		File file = new File("src/test/resources/requestpayload/modify/" + fileName);
		Path path = file.toPath();
		StringBuilder sb = new StringBuilder();
		List<String> content = Files.readAllLines(path, Charset.defaultCharset());
		for (String string : content) {
			sb.append(string);
		}
		String jsonstring = sb.toString();
		
		return jsonstring;
	}

}
