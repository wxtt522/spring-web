package study.mapper;

import study.entity.Person;

import java.util.HashMap;
import java.util.List;

public interface PersonMapper {
    List<Person> selectAll();

    List<HashMap<String, Object>> getFiles();
}
