package com.cbx.springbootcommunity.mapper;

import com.cbx.springbootcommunity.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface UserMapper {
    void insert(User user);
    User findByToken(@Param("token") String token);
@Select("select * from user where id=#{id}")
    User findById(@Param("id") Integer creatorId);
}
