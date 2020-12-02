package com.example.mapper;


import com.example.model.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

public interface StudentMapper {

    /**
     * 新增
     * @param student
     * @return
     */
    int insert(Student student);


    @Insert("insert into t_student(name,age,clazz_id,number) values " +
            "(#{name},#{age},#{clazzId},#{number})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int annoInsert(Student student);


    /**
     * 更新
     * @param student
     * @return
     */
    int updateIgnoreNullById(@Param("student") Student student);

    /**
     * 查询
     * @param id
     * @return
     */
    Student selectById(@Param("id") int id);


    List<Student> selectByIds(@Param("ids") List<Integer> id_list, @Param("age") Integer age);

    List<Student> selectByIdsV2(@Param("ids") List<Integer> id_list, @Param("age") Integer age);

    List<Student> selectByIdsV3(@Param("studentList") List<Student> studentList, @Param("age") Integer age);

    List<Student> selectByIdsV4(@Param("studentList") List<Student> studentList, @Param("age") Integer age);

    @Select("select * from t_student where id = #{id}")
    Student annoSelectById(Integer id);

    @Select("select *from t_student")
    List<Student> findAll();

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteById(@Param("id") int id);


    @Delete("delete from t_student where id = #{id}")
    int annoDeleteById(Integer id);


    @Select("select * from t_student where clazz_id = #{clazzId}")
    List<Student> findByClazzId(Integer clazzId);


}