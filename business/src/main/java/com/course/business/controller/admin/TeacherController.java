package com.course.business.controller.admin;

import com.course.server.dto.TeacherDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.TeacherService;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/teacher")
public class TeacherController {

    private static final Logger LOG = LoggerFactory.getLogger(TeacherController.class);
    public static final String BUSINESS_NAME = "讲师";

    @Resource
    private TeacherService teacherService;

    /**
     * 列表查询
     */
    @PostMapping("/all")
    public ResponseDto all() {
        ResponseDto responseDto = new ResponseDto();
        List<TeacherDto> teacherDtoList = teacherService.all();
        responseDto.setContent(teacherDtoList);
        return responseDto;
    }

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        teacherService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody TeacherDto teacherDto) {
        // 保存校验
        ValidatorUtil.require(teacherDto.getName(), "姓名");
        ValidatorUtil.length(teacherDto.getName(), "姓名", 1, 50);
        ValidatorUtil.length(teacherDto.getNickname(), "昵称", 1, 50);
        ValidatorUtil.length(teacherDto.getImage(), "头像", 1, 100);
        ValidatorUtil.length(teacherDto.getPosition(), "职位", 1, 50);
        ValidatorUtil.length(teacherDto.getMotto(), "座右铭", 1, 50);
        ValidatorUtil.length(teacherDto.getIntro(), "简介", 1, 500);

        ResponseDto responseDto = new ResponseDto();
        teacherService.save(teacherDto);
        responseDto.setContent(teacherDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        teacherService.delete(id);
        return responseDto;
    }
}
