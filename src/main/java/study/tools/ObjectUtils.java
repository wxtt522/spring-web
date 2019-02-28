package study.tools;

import org.apache.commons.lang.StringUtils;

import java.util.Collection;

/**
 * @Author xueshiqi
 * @Date 2017/8/21
 * Object转换类型工具类
 */
public final class ObjectUtils {

    /**
     * object 转String
     *
     * @param object
     * @return
     */
    public static String getString(Object object) {
        if (object == null) {
            return "";
        }
        return object.toString();
    }

    public static Integer getInteger(Object object) {
        if (object == null) {
            return 0;
        }
        return Integer.valueOf(getString(object));
    }

    public static Boolean getBoolean(Object object) {
        if ("1".equals(object)) {
            return true;
        }
        return false;
    }

    public static Short getShort(Object object) {
        if (object == null) {
            return null;
        }
        return Short.valueOf(getString(object));
    }

    public static boolean isWhetherNull(String s) {
        if (s == null || "".equals(s)) {
            return true;
        }
        return false;
    }

    public static boolean isCollection(Collection collection) {
        if (collection == null || collection.size() <= 0) {
            return true;
        }
        return false;
    }

    public static Integer intSum(Integer integer, Integer integer1) {
        if (integer == null && integer1 == null)
            return 0;
        if (integer == null)
            return integer1;
        if (integer1 == null)
            return integer;
        return integer + integer1;
    }


    /**
     * 对关键信息进行掩码
     *
     * @param s     需要掩码的字符串
     * @param start 开始位置 0为全部
     * @param end   结束位置
     * @return
     */
    public static String maskString(String s, int start, int end) {
        if (StringUtils.isNotEmpty(s)) {
            if (start == 0) {
                String y = "";
                for (int i = 0; i < s.length(); i++) {
                    y += "*";
                }
                return y;
            } else if (end > start) {
                String y = StringUtils.left(s, start);
                String z = StringUtils.substring(s, end);
                for (int i = 0, len = end - start; i < len; i++) {
                    y += "*";
                }
                return y + z;
            } else {
                String y = StringUtils.left(s, start);
                for (int i = 0; i < start; i++) {
                    y += "*";
                }
                return y;
            }
        }
        return s;
    }

    /**
     * 中间掩码工具类
     *
     * @param s     需要掩码的字符串
     * @param start 左边不需要掩码的长度
     * @param end   右边不需要掩码的长度
     * @param len   掩码长度
     * @return
     */
    public static String maskStringC(String s, int start, int end, int len) {
        if (StringUtils.isNotEmpty(s)) {
            String y = StringUtils.left(s, start);
            String z = StringUtils.right(s, end);
            if (len <= 0) {
                len = s.length() > (end + start) ? s.length() - (end + start) : 0;
            }
            for (int i = 0; i < len; i++) {
                y += "*";
            }
            return y + z;
        }
        return s;
    }

    /**
     * 校验字符串是否是数字（包括小数）
     *
     * @param str
     * @return
     */
    public static boolean isStringIsNumber(String str) {
        String reg = "^[0-9]+(.[0-9]+)?$";
        return str.matches(reg);

    }
}
