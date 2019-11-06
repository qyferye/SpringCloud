package com.cloud.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.dao.entity.CloudUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cloud
 * @since 2019-11-04
 */
public interface CloudUserService extends IService<CloudUser> {//'test_user_'.concat(#p0)

        CloudUser getById(String id);

        // 更新
        boolean updateById(CloudUser cloudUser);

        int insertUser(CloudUser cloudUser);
}
