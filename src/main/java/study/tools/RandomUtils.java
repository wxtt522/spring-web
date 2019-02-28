package study.tools;

import org.apache.commons.lang.StringUtils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @ClassName RandomUtils
 * @Description TODO
 * @Author lixm
 * @Date 2018/12/19 17:43
 * @Version
 **/
public class RandomUtils {
    private static final Random random = new Random();

    /**
     * 生成num个长度为length的字符串（字符串各不相同）,字符串只包含字母
     * @param length 字符串的长度
     * @param num 字符串的个数
     * @return
     */
    public static String[] random( int length,  int num)
    {
        return RandomUtils.buildRandom(length, num);
    }

    /**
     *  生成长度为length的字符串,字符串只包含数字
     * @param length 字符串的长度
     * @return
     */
    public static String random( int length)
    {
        return RandomUtils.buildRandom(length);
    }


    /**
     * 生成num个长度为length的字符串，组成如 123-123-123 格式(只包含数字)
     * @param length
     * @param num
     * @return
     * @throws
     *
     * @author lixm
     */
    public static String randombunch(int length,int num)  {
        StringBuffer str = new StringBuffer();
        for(int i = 0;i<num;i++){
            str.append(RandomUtils.random(length));
            if(i != num -1)
                str.append("-");
        }
        return str.toString();
    }


    /**
     * 生成num个长度为length的字符串（字符串各不相同）,字符串只包含字母
     * @param length 字符串的长度
     * @param num 字符串的个数
     * @return
     */
    public static String[] buildRandom ( int length,  int num) {
        if (num < 1 || length < 1) {
            return null;
        }
        Set<String> tempRets = new HashSet(num);  //存放临时结果，以避免重复值的发生

        //生成num个不相同的字符串
        while (tempRets.size() < num) {
            tempRets.add(buildRandom(length));
        }

        String[] rets = new String[num];
        rets = tempRets.toArray(rets);
        return rets;
    }

    /**
     * 返回指定位数的整数
     * @param length
     * @return
     *
     * @author lixm
     */
    public static int buildIntRandom(int length){
        String maxStr = StringUtils.rightPad("1",length +1,'0');
        long max = Long.parseLong(maxStr);
        long i = Math.abs(random.nextLong()) % max;
        String rand = String.valueOf(i);
        return Integer.parseInt(rand);
    }

    /**
     * 取小于指定范围内的整数
     * @param length
     * @return
     *
     * @author lixm
     */
    public static int buildIntRandomBy(int length){
        return (int)(Math.random()*length);
    }

    /**
     *  生成长度为length的字符串,字符串只包含数字
     * @param length 字符串的长度
     * @return
     */
    public static String buildRandom (int length) {
        //长度为length的最多整数
        String maxStr = StringUtils.rightPad("1", length + 1, '0');
        long max = Long.parseLong(maxStr);
        long i = random.nextLong();  //取得随机数
        i = Math.abs(i) % max;  //取正数，并限制其长度
        String value = StringUtils.leftPad(String.valueOf(i), length, '0');
        return value;
    }
}
