package com.cloud.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.dao.entity.CloudUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author cloud
 * @since 2019-11-04
 */
public interface CloudUserMapper extends BaseMapper<CloudUser> {
    /*null值会被缓存，所以update 和  insert的时候都需要CacheEvict；不然先查（缓存了null），再插，后查询可能一直返回null*/
    @Cacheable(value = "CloudUserMapper", key = "'CloudUserMapper_ById_'.concat(#p0)")
    CloudUser getById(@Param("id") String id);
    // 更新
    @CacheEvict(value = "CloudUserMapper", key = "'CloudUserMapper_ById_'.concat(#p0.id)")
    int updateById(CloudUser cloudUser);

    @CacheEvict(value = "CloudUserMapper", key = "'CloudUserMapper_ById_'.concat(#p0.id)")
    int insertUser(CloudUser cloudUser);
}
