package study.com;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import study.cn.MyServer;
import study.entity.Person;
import study.mapper.PersonMapper;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MyController {

    @Resource
    MyServer myServer;

    @Resource
    PersonMapper personMapper;

    @RequestMapping("test")
    public @ResponseBody
    Map test() {
        return myServer.getMap();
    }

    @RequestMapping("getPerson")
    public @ResponseBody
    List<Person>
    getAll() {
        List<Person> personList = personMapper.selectAll();
        return personList;
    }

    @RequestMapping("/getMap")
    @ResponseBody
    public List<HashMap<String, Object>> getMaps() {
        return personMapper.getFiles();
    }

    @RequestMapping("json")
    @ResponseBody
    public String json() {
        return "ok";
    }

    @RequestMapping("/index")
    public String toIndex() {
        return "index";
    }

    @RequestMapping("joker")
    public void joker() throws InterruptedException {
        Person p1 = myServer.getPerson("zhangsan", "123");
        System.out.println(p1);
        Thread.sleep(2000);
        Person p2 = myServer.getPersonByName("zhangsan");
        System.out.println(p2);
        Thread.sleep(2000);
        Person p3 = myServer.getPersonByName("zhangsan");
        System.out.println(p2);
        System.out.println(p2==p3);
    }
}
