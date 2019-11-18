package com.cbx.springbootcommunity.mapper;

import com.cbx.springbootcommunity.domain.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper {
       void create(Question question);

       List<Question> list();
}
