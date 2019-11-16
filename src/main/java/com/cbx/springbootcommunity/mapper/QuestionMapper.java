package com.cbx.springbootcommunity.mapper;

import com.cbx.springbootcommunity.domain.Question;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
       void create(Question question);
}
