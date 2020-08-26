package com.cloud.es.service;

import com.cloud.es.entity.Cloud;
import com.cloud.es.entity.Company;
import com.cloud.es.repository.CloudRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CloudService {

    @Resource
    private CloudRepository cloudRepository;
    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    public List<Cloud> getByName(String name){
        System.out.println(cloudRepository.findById(7L));
        List<Cloud> byName = cloudRepository.findByName(name);
        return byName;
    }

    public String add(){
        Cloud cloud = new Cloud();
        cloud.setAge("25");
        cloud.setName("add");
        cloud.setSalary_min(30000);
        cloud.setWork("UI设计");
        cloud.setId(10L);
        cloud.setCompany(new Company("字节跳动","北京"));
        Cloud cloud1 = cloudRepository.save(cloud);
        return String.valueOf(cloud1);
    }

    public String del(){
        cloudRepository.deleteById(10L);
        return "success";
    }

    public String update(){
        /*没有对应的直接更新的操作
        * 只能先删除  再增加
        * */
        return "";
    }


    public List<Cloud> filter() {
        QueryBuilder query = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("company.name","阿里"));
        Page<Cloud> search = cloudRepository.search(query, PageRequest.of(1,1));
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(query).build();
       return search.getContent();
    }

    public List<Cloud> template(){

        //elasticsearchTemplate.bulkIndex();
        return null;
    }
}
