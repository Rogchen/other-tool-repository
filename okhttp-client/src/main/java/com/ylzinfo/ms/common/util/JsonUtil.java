package com.ylzinfo.ms.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ylzinfo.ms.common.exception.JsonParseException;
/**
 * 
* @author xuyajie 
* @date 2018年7月18日 上午11:29:49 
* json转换工具
 */
public class JsonUtil {
	/**
	 * 
	* @date 2018年7月18日 上午11:33:41 
	* @author xuyajie 
	* @param object 目标对象
	* @return json字符串
	* @throws JsonParseException json转换异常
* 将对象转成json字符串
	 */
	public static String toJsonString(Object object) throws JsonParseException {
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (Exception e) {
			throw new JsonParseException(e);
		}
	}
	/**
	 * 
	* @date 2018年7月18日 上午11:34:07 
	* @author xuyajie 
	* @param json json字符串
	* @param t 目标类型
	* @return 对象
	* @throws JsonParseException json转换异常
* 将json字符串转成对应类型的对象
	 */
	public static <T> T toObject(String json, Class<T> t) throws JsonParseException {
		try {
			return new ObjectMapper().readValue(json, t);
		} catch (Exception e) {
			throw new JsonParseException(e);
		}
	}
}