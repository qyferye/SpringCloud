package cloud.springboot.service;


import cloud.springboot.dao.entity.CloudUser;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cloud
 * @since 2019-11-04
 */
public interface CloudUserService{//'test_user_'.concat(#p0)

        CloudUser getById(Integer id);

        // 更新
        boolean updateById(CloudUser cloudUser);

        int insertUser(CloudUser cloudUser);

        List<CloudUser> getAll();
}
