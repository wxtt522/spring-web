package study.tools;

import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;

/**
 * @ClassName SendMessage
 * @Description TODO
 * @Author lixm
 * @Date 2018/9/14 11:27
 * @Version
 **/

public class SendMessage {

    private RedisTemplate<String, Object> redisTemplate;

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void sendMessage(String channel, Serializable message) {
        redisTemplate.convertAndSend(channel, message);
    }

}
