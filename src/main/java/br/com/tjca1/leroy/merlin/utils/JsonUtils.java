package br.com.tjca1.leroy.merlin.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings({"unchecked", "rawtypes"})
public class JsonUtils {

	public static Object getObject(String json, Class classe) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		Object obj = objectMapper.readValue(json, classe);
		return obj;
	}
	
}
