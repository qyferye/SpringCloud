package com.cloud.mybatis.mapper;

import com.cloud.mybatis.entity.UserDomain;

import java.util.List;

/**
 * @ Author     ：cloud
 * @ Date       ：Created in  2020-08-21 9:19
 * @ Description：SpringCloud
 */
public interface UserMapper {

    int insert(UserDomain record);

    List<UserDomain> selectUsers();
}
