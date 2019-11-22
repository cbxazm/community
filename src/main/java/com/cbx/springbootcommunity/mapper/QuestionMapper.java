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
       @Select("SELECT COUNT(*) FROM question q LEFT JOIN USER u ON q.creatorId=u.id")
       Integer count();
       List<Question> listByUserId(@Param("userId") Integer userId,@Param("offset") Integer offset,@Param("size") Integer size);
         @Select("select count(*) from question where creatorId=#{userId}")
       Integer countByUserId(@Param("userId") Integer userId);

   @Select("select * from question where id =#{id}")
    Question getById(Integer id);
 @Select("update question set title=#{title},description=#{description},gmt_modified=#{gmtModified},tag=#{tag} where id=#{id}")
    void update(Question question);
}
