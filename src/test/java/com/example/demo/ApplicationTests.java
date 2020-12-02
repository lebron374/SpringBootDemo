package com.example.demo;

import com.example.mapper.StudentMapper;
import com.example.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
class ApplicationTests {

    @Resource
    private StudentMapper studentMapper;

	@Test
	void contextLoads() {
	}

    @Test
    public void testInsert() {
        Student student = new Student();
        student.setAge(20);
        student.setName("张四");
        student.setNumber("201913");
        student.setClazzId(1);
        studentMapper.insert(student);
        System.out.println(student.getId());
    }

}
