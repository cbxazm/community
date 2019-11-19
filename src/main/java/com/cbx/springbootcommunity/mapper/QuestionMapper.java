package com.cbx.springbootcommunity.mapper;

import com.cbx.springbootcommunity.domain.Question;
import com.cbx.springbootcommunity.dto.QuestionDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
       void create(Question question);
       @Select("select * from question limit #{offset},#{size}")
       List<Question> list(@Param("offset") Integer offset,@Param("size") Integer size);
       @Select("SELECT COUNT(*) FROM question q LEFT JOIN USER u ON q.creatorId=u.id;")
       Integer count();
}
