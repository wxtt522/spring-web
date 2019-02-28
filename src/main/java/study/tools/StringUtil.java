package study.tools;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.UUID;
import java.util.regex.Pattern;

public class StringUtil extends StringUtils {

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$";

    /**
     * 简单验证企业手机号
     */
    public static final String UNIT_MOBILE = "^[0][1-9]{2,3}-[0-9]{5,10}$";

    /**
     * 身份证号码
     */
    public static final String REGEX_IDNUMBER = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$|^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X|x)$";

    /**
     * 正整数
     */
    public static final String REGEX_POSITIVE = "^[1-9][0-9]*(\\.0+)?$";

    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }


    public static synchronized String encryptSha256(String inputStr) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte digest[] = md.digest(inputStr.getBytes("UTF-8"));

            return new String(Base64.encodeBase64(digest));

            // return (new BASE64Encoder()).encode(digest);
            // return new String(Hex.encodeHex(digest));
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 自动生成32位的UUid
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        if (isBlank(mobile)) {
            return false;
        }
        return Pattern.matches(REGEX_MOBILE, mobile);
//        return Pattern.matches("^[0][1-9]{2,3}-[0-9]{5,10}$", mobile);
    }

    /**
     * 校检企业手机号码
     */
    public static boolean isUnitMobile(String mobile) {
        if (isBlank(mobile)) {
            return false;
        }
        return Pattern.matches(UNIT_MOBILE, mobile);
    }

    /**
     * 校验身份证号
     *
     * @param idnumber
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isIdnumber(String idnumber) {
        if (isBlank(idnumber)) {
            return false;
        }
        return Pattern.matches(REGEX_IDNUMBER, idnumber);
    }

    /**
     * 校验正整数
     *
     * @param number
     * @return
     */
    public static boolean isPositiveInteger(String number) {
        if (isBlank(number)) {
            return false;
        }
        return Pattern.matches(REGEX_POSITIVE, number);
    }

    /**
     * 首字母转大写
     *
     * @param str
     * @return
     */
    public static String upperFirstLetter(String str) {
        if (str != null) {
            char[] cs = str.toCharArray();
            cs[0] -= 32;
            str = String.valueOf(cs);
        }
        return str;
    }

    /**
     * MD5签名
     *
     * @param str
     * @param charset
     * @return
     */
    public static String md5Sign(String str, String charset) {
        HashFunction md5 = Hashing.md5();
        HashCode md5Hc = null;
        try {
            md5Hc = md5.hashBytes(str.getBytes(charset));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return md5Hc.toString();
    }

    /**
     * 判断字符串是否为数字(包含小数)
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        String reg = "^[0-9]+(.[0-9]+)?$";
        return str.matches(reg);
    }
//    public static void main(String[] args) {
//		System.out.println(isPositiveInteger("200000"));
//		System.out.println(isPositiveInteger("3000000.00"));
//		System.out.println(isPositiveInteger("0.1"));
//		System.out.println(isPositiveInteger("0"));
//		System.out.println(isPositiveInteger("-999"));
//		System.out.println(isPositiveInteger("002"));
//		System.out.println(isPositiveInteger("2000.1"));
//        System.out.println(isMobile("0234-12342314"));
//    }
}
