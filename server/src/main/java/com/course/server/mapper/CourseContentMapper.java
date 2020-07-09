package com.course.server.mapper;

import com.course.server.domain.CourseContent;
import com.course.server.domain.CourseContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseContentMapper {
    long countByExample(CourseContentExample example);

    int deleteByExample(CourseContentExample example);

    int deleteByPrimaryKey(String id);

    int insert(CourseContent record);

    int insertSelective(CourseContent record);

    List<CourseContent> selectByExampleWithBLOBs(CourseContentExample example);

    List<CourseContent> selectByExample(CourseContentExample example);

    CourseContent selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CourseContent record, @Param("example") CourseContentExample example);

    int updateByExampleWithBLOBs(@Param("record") CourseContent record, @Param("example") CourseContentExample example);

    int updateByExample(@Param("record") CourseContent record, @Param("example") CourseContentExample example);

    int updateByPrimaryKeySelective(CourseContent record);

    int updateByPrimaryKeyWithBLOBs(CourseContent record);
}