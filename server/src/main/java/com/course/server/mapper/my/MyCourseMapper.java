package com.course.server.mapper.my;

import com.course.server.dto.CourseDto;
import com.course.server.dto.CoursePageDto;
import com.course.server.dto.SortDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyCourseMapper {

    List<CourseDto> list(@Param("pageDto") CoursePageDto pageDto);

    int updateTime(@Param("courseId") String courseId);

    int updateSort(SortDto sortDto);

    int moveSortsBackward(SortDto sortDto);

    int moveSortsForward(SortDto sortDto);
}
