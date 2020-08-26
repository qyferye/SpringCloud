package com.cloud.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.dao.entity.CloudUser;
import com.cloud.dao.mapper.CloudUserMapper;
import com.cloud.service.CloudUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cloud
 * @since 2019-11-04
 */
@Service
//@DbSource("READ_TEST1")
public class CloudUserServiceImpl extends ServiceImpl<CloudUserMapper, CloudUser> implements CloudUserService {
    @Resource
    private CloudUserMapper cloudUserMapper;

    @Override
    public CloudUser getById(Integer id) {
        return cloudUserMapper.getById(id.toString());
    }

    @Override
    public int insertUser(CloudUser cloudUser) {
        return cloudUserMapper.insertUser(cloudUser);
    }
}
