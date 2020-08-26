package com.cloud.mybatis.service;

import com.cloud.mybatis.entity.UserDomain;
import com.github.pagehelper.PageInfo;

public interface UserService {

    int addUser(UserDomain user);

    PageInfo<UserDomain> findAllUser(int pageNum, int pageSize);
}