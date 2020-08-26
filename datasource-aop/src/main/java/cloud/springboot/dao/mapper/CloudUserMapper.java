package cloud.springboot.dao.mapper;


import cloud.springboot.dao.entity.CloudUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author cloud
 * @since 2019-11-04
 */
public interface CloudUserMapper {
    /*null值会被缓存，所以update 和  insert的时候都需要CacheEvict；不然先查（缓存了null），再插，后查询可能一直返回null*/

    CloudUser getById(@Param("id") Integer id);
    // 更新

    int updateById(CloudUser cloudUser);

    int insertUser(CloudUser cloudUser);

    List<CloudUser> getAll();
}
