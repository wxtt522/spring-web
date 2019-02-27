package study.com;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

@Controller
public class Server {
    @Autowired
    private JedisPool jedisPool;//注入JedisPool

    @RequestMapping(value = "/demo_set",method = RequestMethod.GET)
    @ResponseBody
    public String demo_set(){
        //获取ShardedJedis对象
        Jedis jedis = jedisPool.getResource();
        Map map = new HashMap();
        map.put("name", "zhangsan");
        map.put("sex", "nan");
        map.put("age", 23);
        //存入键值对
        jedis.set("map", new Gson().toJson(map));
        //回收ShardedJedis实例
        jedis.close();

        return "set";
    }

    @RequestMapping(value = "/demo_get",method = RequestMethod.GET)
    @ResponseBody
    public String demo_get(){
        Jedis jedis = jedisPool.getResource();
        //根据键值获得数据
        String result = jedis.get("map");
        jedis.close();

        return result;
    }
}
