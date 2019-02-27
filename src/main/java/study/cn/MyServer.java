package study.cn;


import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import study.entity.Person;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class MyServer {
    public Map getMap() {
        Map map = new HashMap();
        map.put("name", "zhangsan");
        map.put("sex", "nan");
        map.put("age", 23);
        return map;
    }
    @Cacheable(value = "personA")
    public Person getPerson(String loginName,String passWorkd){
        return new Person(loginName,passWorkd,new Date());
    }

    @Cacheable(value = "personB")
    public Person getPersonByName(String loginName){
        Person person = new Person();
        person.setLoginName(loginName);
//        person.setAddTime(new Date());
        return person;
    }

}
