package study.tools;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtils {

	private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);
	
	/**
	 * 获得json字符串,不进行过滤,也不进行格式化。
	 * 主要用来将第三方响应的参数存储到数据库
	 * @param obj
	 * @return
	 */
	public static String getJSONNoFilter(Object obj) {
		 
		return JSON.toJSONString(obj);
	} 
	
	/**
	 * json字符串转换Map
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T toMap(String json){
		
		 return (T)JSON.parse(json); 
	}
	
	public static <T> T toObject(String json, Class<T> t){
		
		 return (T)JSON.parseObject(json, t);
	}
    /**
     * object、list等转json字符串(阿里巴巴)
     * @param object
     * @return String
     */
    public static String getJson(Object object){

        return JSON.toJSONString(object);
    }

	/**
	 * 校验json合法
	 * @param jsonInString
	 * @return
	 */
	public static boolean isJSONValid(String jsonInString) {
		try {
			new Gson().fromJson(jsonInString, Object.class);
			return true;
		} catch(JsonSyntaxException ex) {
			logger.warn("--传入非法json串："+jsonInString);
			return false;
		}
	}
}
