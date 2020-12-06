package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.example.common.RedisUtil;
import com.example.mapper.StudentMapper;
import com.example.model.Student;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class Controller {

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Object findAll() {
        List<Student> studentList = studentMapper.findAll();

        return studentList;
    }

    @RequestMapping(value = "/ids", method = RequestMethod.GET)
    public Object findByIds(@RequestParam String ids, @RequestParam Integer age) {

        String[] idStr = StringUtils.split(ids, ",");
        List<Integer> idList = new ArrayList<Integer>();
        for (String tmp : idStr) {
            idList.add(Integer.valueOf(tmp));
        }

        List<Student> studentList = studentMapper.selectByIds(idList, age);

        redisUtil.set("student", JSON.toJSONString(studentList));

        return studentList;
    }


    @RequestMapping(value = "/idsV2", method = RequestMethod.GET)
    public Object findByIdsV2(@RequestParam String ids, @RequestParam Integer age) {

        String[] idStr = StringUtils.split(ids, ",");
        List<Integer> idList = new ArrayList<Integer>();
        for (String tmp : idStr) {
            idList.add(Integer.valueOf(tmp));
        }

        List<Student> studentList = studentMapper.selectByIdsV2(idList, age);

        redisUtil.set("student", JSON.toJSONString(studentList));

        return studentList;
    }

    @RequestMapping(value = "/idsV3", method = RequestMethod.GET)
    public Object findByIdsV3(@RequestParam String ids, @RequestParam Integer age) {

        String[] idStr = StringUtils.split(ids, ",");
        List<Student> studentList = new ArrayList<Student>();
        for (String tmp : idStr) {

            Student student = new Student();
            student.setId(Integer.valueOf(tmp));
            studentList.add(student);
        }

        List<Student> result = studentMapper.selectByIdsV3(studentList, age);

        redisUtil.set("student", JSON.toJSONString(result));

        return result;
    }

    @RequestMapping(value = "/idsV4", method = RequestMethod.GET)
    public Object findByIdsV4(@RequestParam String ids, @RequestParam Integer age) {

        String[] idStr = StringUtils.split(ids, ",");
        List<Student> studentList = new ArrayList<Student>();
        for (String tmp : idStr) {

            Student student = new Student();
            student.setId(Integer.valueOf(tmp));
            studentList.add(student);
        }

        List<Student> result = studentMapper.selectByIdsV4(studentList, age);

        redisUtil.set("student", JSON.toJSONString(result));

        return result;
    }

    @RequestMapping(value = "/insertV1", method = RequestMethod.GET)
    public Object insertV1() {
        Student student = new Student();
        student.setAge(11);
        student.setName("name");
        student.setClazzId(123);
        student.setNumber("123");

        Integer result = studentMapper.insertV1(student);

        return result;
    }

    @RequestMapping(value = "/insertV2", method = RequestMethod.GET)
    public Object insertV2() {
        Student student = new Student();
        student.setAge(13);
        student.setName("name");
        student.setClazzId(123);
        student.setNumber("123");

        Integer result = studentMapper.insertV2(student);

        return result;
    }

    @RequestMapping(value = "/batchInsertV1", method = RequestMethod.GET)
    public Object batchInsertV1() {
        Student student = new Student();
        student.setAge(13);
        student.setName("batchInsertV1");
        student.setClazzId(123);
        student.setNumber("batchInsertV1");

        Integer result = studentMapper.batchInsertV1(Arrays.asList(student));

        return result;
    }


    @RequestMapping(value = "/batchInsertV2", method = RequestMethod.GET)
    public Object batchInsertV2() {
        Student student = new Student();
        student.setAge(13);
        student.setName("batchInsertV2");
        student.setClazzId(123);
        student.setNumber("batchInsertV2");

        Integer result = studentMapper.batchInsertV2(Arrays.asList(student));

        return result;
    }

    @RequestMapping(value = "/updateV1", method = RequestMethod.GET)
    public Object updateV1(@RequestParam Integer age, @RequestParam Integer id) {

        Student student = new Student();
        student.setId(id);
        student.setAge(age);

        Integer result = studentMapper.updateV1(student);

        return result;
    }

    @RequestMapping(value = "/updateV2", method = RequestMethod.GET)
    public Object updateV2(@RequestParam Integer age, @RequestParam Integer id) {

        Student student = new Student();
        student.setId(id);
        student.setAge(age);

        Integer result = studentMapper.updateV2(student);

        return result;
    }

    @RequestMapping(value = "/batchUpate", method = RequestMethod.GET)
    public Object batchUpate() {

        List<Student> studentList = new ArrayList<Student>();

        Student student = new Student();
        student.setId(19);
        student.setAge(19);

        studentList.add(student);

        student = new Student();
        student.setId(20);
        student.setAge(20);
        studentList.add(student);

        Integer result = studentMapper.batchUpdate(studentList);

        return result;
    }

    @RequestMapping(value = "/deleteV1", method = RequestMethod.GET)
    public Object deleteV1(@RequestParam Integer id) {

        Integer result = studentMapper.deleteById(id);

        return result;
    }

    @RequestMapping(value = "/deleteV2", method = RequestMethod.GET)
    public Object deleteV2(@RequestParam String ids) {

        String[] idArr = StringUtils.split(ids, ",");
        List<Integer> idList = Arrays.stream(idArr).map(id -> Integer.valueOf(id)).collect(Collectors.toList());

        Integer result = studentMapper.batchDetele(idList);

        return result;
    }
}
