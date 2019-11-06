package com.cloud.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.dao.entity.CloudUser;
import com.cloud.dao.mapper.CloudUserMapper;
import com.cloud.service.CloudUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cloud
 * @since 2019-11-04
 */
@Service
public class CloudUserServiceImpl extends ServiceImpl<CloudUserMapper, CloudUser> implements CloudUserService {
    @Autowired
    private CloudUserMapper cloudUserMapper;

    @Override
    public CloudUser getById(String id) {
        return cloudUserMapper.getById(id);
    }

    @Override
    public int insertUser(CloudUser cloudUser) {
        return cloudUserMapper.insertUser(cloudUser);
    }
}
