package cloud.springboot.service.impl;


import cloud.springboot.annotions.DbSource;
import cloud.springboot.dao.entity.CloudUser;
import cloud.springboot.dao.mapper.CloudUserMapper;
import cloud.springboot.service.CloudUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cloud
 * @since 2019-11-04
 */
@Service
@DbSource("READ_TEST1")
public class CloudUserServiceImpl  implements CloudUserService {
    @Autowired
    private CloudUserMapper cloudUserMapper;

    @Override
    public CloudUser getById(Integer id) {
        return cloudUserMapper.getById(id);
    }

    @Override
    public boolean updateById(CloudUser cloudUser) {
        return false;
    }

    @Override
    public int insertUser(CloudUser cloudUser) {
        return cloudUserMapper.insertUser(cloudUser);
    }

    @Override
    public List<CloudUser> getAll() {
        return cloudUserMapper.getAll();
    }
}
