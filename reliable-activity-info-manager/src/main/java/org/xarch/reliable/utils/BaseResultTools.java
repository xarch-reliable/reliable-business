package org.xarch.reliable.utils;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseResultTools {

	private static final Logger logger = LoggerFactory.getLogger(BaseResultTools.class);
	private static final ObjectMapper oMapper = new ObjectMapper();

	@SuppressWarnings("unchecked")
	public static <T> T fromJSON(String jsonlString, Class<T> clz) {
		try {
			return oMapper.readValue(jsonlString, clz);
		} catch (Exception e) {
			logger.error("[xml]-->[pojo]失败：" + jsonlString);
			e.printStackTrace();
		}
		return (T) new Object();
	}

	public static <T> T MapToObject(Map<String, Object> map, Class<T> clz) {
		return oMapper.convertValue(map, clz);
	}

	public static String JsonObjectToStr(Object object) {
		try {
			oMapper.setSerializationInclusion(Include.NON_NULL);
			return oMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			logger.error("[pojo]-->[JsonString]序列化失败");
			e.printStackTrace();
		}
		return new String();
	}

}
