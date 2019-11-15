package com.cbx.springbootcommunity.mapper;

import com.cbx.springbootcommunity.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapper {
    void insert(User user);


}
