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
import java.util.List;

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
}
