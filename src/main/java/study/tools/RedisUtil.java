package study.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具
 * Created by chengluming on 2017/11/15.
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public Object get(String key) {
        ValueOperations valueOper = this.redisTemplate.opsForValue();
        return valueOper.get(key);
    }

    public List<Object> get(Collection<String> keys) {
        ValueOperations valueOper = this.redisTemplate.opsForValue();
        return valueOper.multiGet(keys);
    }

    public void set(String key, Object value) {
        ValueOperations valueOper = this.redisTemplate.opsForValue();
        valueOper.set(key, value);
    }

    public void set(Map<String, Object> map) {
        ValueOperations valueOper = this.redisTemplate.opsForValue();
        valueOper.multiSet(map);
    }

    public void set(String key, Object value, long timeout) {
        ValueOperations valueOper = this.redisTemplate.opsForValue();
        valueOper.set(key, value, timeout, TimeUnit.SECONDS);
    }

    public void dele(String key) {
        if (key != null) {
            this.redisTemplate.delete(key);
        }
    }

    public Set<String> keys(String pattern){
        if (pattern != null) {
            return this.redisTemplate.keys(pattern);
        }
        return null;
    }
}
