package com.cbx.springbootcommunity.mapper;

import com.cbx.springbootcommunity.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
@Mapper
public interface UserMapper {
    void insert(User user);
    User findByToken(@Param("token") String token);
@Select("select * from user where id=#{id}")
    User findById(@Param("id") Integer creatorId);
@Select("select * from user where account_id=#{accountId}")
    User findByAccountId(@Param("accountId") String accountId);
@Update("update user set name=#{name},token=#{token},gmt_create=#{gmtCreate},gmt_modified=#{gmtModified},avatar_url=#{avatarUrl} where id=#{id}")
    void update(User dbUser);
}
