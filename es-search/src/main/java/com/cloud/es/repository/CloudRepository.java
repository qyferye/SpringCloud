package com.cloud.es.repository;

import com.cloud.es.entity.Cloud;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CloudRepository extends ElasticsearchRepository<Cloud, Long> {
    //使用springData 自动生成
    List<Cloud> findByName(String name);

}
