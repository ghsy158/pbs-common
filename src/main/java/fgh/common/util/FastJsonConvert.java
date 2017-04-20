package fgh.common.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * fastJson转换类
 * @author fgh
 * @since 2016年7月28日下午5:37:00
 */
public class FastJsonConvert {

	private static final SerializerFeature[] featuresWithNullValue = { SerializerFeature.WriteMapNullValue,
			SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullListAsEmpty,
			SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty };

	private static final Logger logger = LoggerFactory.getLogger(FastJsonConvert.class);
	
	/**
	 * 
	 * <b>方法名称：</b>JSON字符串转换成对象<br>
	 * <b>概要说明：</b><br>
	 */
	public static <T> T convertJSONToObject(String data, Class<T> clazz) {
		try {
			if(StringUtils.isBlank(data)){
				return null;
			}
			T t = JSON.parseObject(data, clazz);
			return t;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}

	/**
	 * 
	 * <b>方法名称：</b>JSON对象转换成对象<br>
	 * <b>概要说明：</b><br>
	 */
	public static <T> T convertJSONToObject(JSONObject data, Class<T> clazz) {
		try {
			T t = JSONObject.toJavaObject(data, clazz);
			return t;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}

	/**
	 * 
	 * <b>方法名称：</b>将JSON字符串数组转换成List集合对象<br>
	 * <b>概要说明：</b><br>
	 * @param data JSOn字符串数组
	 * @param clazz 转换对象
	 * @return List<T> 集合对象
	 */
	public static <T> List<T> convertJSONToArray(String data, Class<T> clazz) {
		try {
			List<T> t = JSON.parseArray(data,clazz);
			return t;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}
	
	/**
	 * 
	 * <b>方法名称：</b>将JSON字符串数组转换成List集合对象<br>
	 * <b>概要说明：</b><br>
	 * @param data List<JSONObject>
	 * @param clazz 转换对象
	 * @return List<T> 集合对象
	 */
	public static <T> List<T> convertJSONToList(List<JSONObject> data, Class<T> clazz) {
		try {
			List<T> t = new ArrayList<T>();
			for (JSONObject jsonObject : data) {
				t.add(convertJSONToObject(jsonObject, clazz));
			}
			return t;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}

	/**
	 * 
	 * <b>方法名称：</b>将对象转换成JSON字符串<br>
	 * <b>概要说明：</b><br>
	 */
	public static String convertObjectToJSON(Object obj) {
		try {
			String text = JSON.toJSONString(obj);
			return text;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}

	/**
	 * 
	 * <b>方法名称：</b>将对象转换成JSONObject对象<br>
	 * <b>概要说明：</b><br>
	 */
	public static JSONObject convertObjectToJSONObject(Object obj) {
		try {
			JSONObject jsonObject = (JSONObject) JSONObject.toJSON(obj);
			return jsonObject;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}
	
	/**
	 * json字符串转换成JSONObject
	 * @param json
	 * @return
	 */
	public static JSONObject convertString2JSONObject(String json) {
		try {
			return JSONObject.parseObject(json);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 
	 * <b>方法名称：</b><br>
	 * <b>概要说明：</b><br>
	 */
	public static String convertObjectToJSONWithNullValue(Object obj) {
		try {
			String text = JSON.toJSONString(obj, featuresWithNullValue);
			return text;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}

}
