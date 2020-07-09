package com.course.server.mapper;

import com.course.server.domain.Member;
import com.course.server.domain.MemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberMapper {
    long countByExample(MemberExample example);

    int deleteByExample(MemberExample example);

    int deleteByPrimaryKey(String id);

    int insert(Member record);

    int insertSelective(Member record);

    List<Member> selectByExample(MemberExample example);

    Member selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Member record, @Param("example") MemberExample example);

    int updateByExample(@Param("record") Member record, @Param("example") MemberExample example);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);
}