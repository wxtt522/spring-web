package study.tools;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;


/**
 * 系统配置属性工具类
 * @author chengluming
 */
public class SystemConfig {

	private static Logger logger = Logger.getLogger(SystemConfig.class);
	
	private static Properties props;
	
	private SystemConfig(){}
	
	static {
		props=new Properties();
	}
	
	/**
	 * 配制资源路径,并且加载资源
	 * @param properties
	 */
	public static void configuration(String properties) {
		InputStream is=null;
		try {
			is=new FileInputStream(properties);
			props.clear();
			props.load(is);
		} catch (Exception e) {
			logger.error(properties+" 加载失败!");
		} finally {
			if(is!=null) {
				try { is.close(); } catch (IOException e) {}
			}
		}
	}
	
	/**
	 * 根据key获取value
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		return trim(props.getProperty(key));
	}
	
	public static String getProperty(String key,String defaultValue) {
		return trim(props.getProperty(key,defaultValue));
	}
	
	/**
	 * 获取数组
	 * @param key
	 * @param limit  分隔符,值为正则表达式
	 * @return
	 */
	public static String[] getValues(String key,String limit) {
		return getProperty(key).split(limit);
	}
	
	public static int getInt(String key) {
		return Integer.parseInt(getProperty(key));
	}
	
	public static int getInt(String key,int defaultValue) {
		try {
			return Integer.parseInt(getProperty(key));
		} catch(Exception e) {
			return defaultValue;
		}
	}
	
	public static long getLong(String key) {
		return Long.parseLong(getProperty(key));
	}
	
	public static long getLong(String key,long defaultValue) {
		try {
			return Long.parseLong(getProperty(key));
		} catch(Exception e) {
			return defaultValue;
		}
	}
	
	public static boolean getBoolean(String key) {
		return Boolean.parseBoolean(getProperty(key));
	}
	
	public static boolean getBoolean(String key,boolean defaultValue) {
		try {
			return Boolean.parseBoolean(getProperty(key));
		} catch(Exception e) {
			return defaultValue;
		}
	}
	
	/**
	 * 截取两边空格字符
	 * @param str
	 * @return
	 */
	private static String trim(String str) {
		if(isEmpty(str)) return "";
		
		return str.trim();
	}
	
	/**
	 * 判断字符串是否空
	 * @param str
	 * @return
	 */
	private static boolean isEmpty(String str) {
		return str==null || str.trim().length()==0;
	}
	
	/**
	 * 判断key是否存在
	 * @param key
	 * @return
	 */
	public static boolean contains(String key) {
		return props.containsKey(key);
	}
	
	/**
	 * 获取所有key
	 * @return
	 */
	public static Set getKeys() {
		return props.keySet();
	}
	
}
