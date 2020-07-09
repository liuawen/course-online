package com.course.business.controller.web;

import com.course.server.dto.CourseDto;
import com.course.server.dto.CoursePageDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.enums.CourseStatusEnum;
import com.course.server.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController("webCourseController")
@RequestMapping("/web/course")
public class CourseController {

    private static final Logger LOG = LoggerFactory.getLogger(CourseController.class);
    public static final String BUSINESS_NAME = "课程";

    @Resource
    private CourseService courseService;

    /**
     * 列表查询，查询最新的3门已发布的课程
     */
    @GetMapping("/list-new")
    public ResponseDto listNew() {
        PageDto pageDto = new PageDto();
        pageDto.setPage(1);
        pageDto.setSize(3);
        ResponseDto responseDto = new ResponseDto();
        List<CourseDto> courseDtoList = courseService.listNew(pageDto);
        responseDto.setContent(courseDtoList);
        return responseDto;
    }

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody CoursePageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        pageDto.setStatus(CourseStatusEnum.PUBLISH.getCode());
        courseService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @GetMapping("/find/{id}")
    public ResponseDto findCourse(@PathVariable String id) {
        LOG.info("查找课程开始：{}", id);
        ResponseDto responseDto = new ResponseDto();
        CourseDto courseDto = courseService.findCourse(id);
        responseDto.setContent(courseDto);
        LOG.info("查找课程结束：{}", responseDto);
        return responseDto;
    }
}
