package com.cloud.api.controller;

import com.cloud.core.dto.DefaultResult;
import com.cloud.dao.es.entity.ArticleDocument;
import com.cloud.dao.es.repository.ArticleRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ Description   :  测试es
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2019-11-07$ 15:31$
 */
@RestController
@RequestMapping("/ignore/es")
@Api("测试es接口")
@Slf4j
public class EsController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @ApiOperation(value = "插入es数据")
    @PostMapping(value = "/insertArticle")
    public DefaultResult<ArticleDocument> insertEs(@RequestParam("name") String name, @RequestParam("type") String type) {
        ArticleDocument articleDocument = new ArticleDocument();
        articleDocument.setId(1L);
        articleDocument.setName(name);
        articleDocument.setType(type);
        articleRepository.save(articleDocument);

        ArticleDocument articleDocument2 = new ArticleDocument();
        articleDocument2.setId(2L);
        articleDocument2.setName("西游记");
        articleDocument2.setType("神话");
        articleRepository.save(articleDocument2);

        ArticleDocument articleDocument3 = new ArticleDocument();
        articleDocument3.setId(3L);
        articleDocument3.setName("java编程思想");
        articleDocument3.setType("编程语言");
        articleRepository.save(articleDocument3);
        return DefaultResult.success(articleDocument3);
    }

    @ApiOperation(value = "插入es数据")
    @PostMapping(value = "/getArticle")
    public DefaultResult<ArticleDocument> getArticle(@RequestParam("id") Long id, @PageableDefault(page = 0, size = 5, sort = "name", direction = Sort.Direction.DESC) Pageable pageable) {


        ArticleDocument articleDocument = articleRepository.findById(id).get();
        System.out.println(articleDocument);


        /**模糊匹配*/
        System.out.println("--------------------模糊匹配---------------------------");
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("name", "朝花夕拾"))//写  朝花  模糊匹配不上  找找问题
                .withPageable(PageRequest.of(0, 3)).build();   //分页
        List<ArticleDocument> articleDocuments = elasticsearchTemplate.queryForList(searchQuery, ArticleDocument.class);
        articleDocuments.forEach(System.out::println);


        /**匹配所有*/
        System.out.println("--------------------匹配所有---------------------------");
        SearchQuery searchQuery2 = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchAllQuery())
                .withPageable(PageRequest.of(0, 3)).build();
        articleDocuments = elasticsearchTemplate.queryForList(searchQuery2, ArticleDocument.class);
        articleDocuments.forEach(System.out::println);


        /**精确匹配*/
        System.out.println("--------------------精确匹配---------------------------");
        SearchQuery searchQuery3 = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.termQuery("name", "西游记"))
                .withPageable(PageRequest.of(0, 3)).build();
        articleDocuments = elasticsearchTemplate.queryForList(searchQuery3, ArticleDocument.class);
        articleDocuments.forEach(System.out::println);

        /**短语模糊匹配*/
        System.out.println("--------------------短语模糊匹配---------------------------");
        BoolQueryBuilder qb = QueryBuilders.boolQuery();
        qb.must(QueryBuilders.matchPhraseQuery("name", "java编程思想"));//写  朝花  模糊匹配不上  找找问题
        Page<ArticleDocument> search = articleRepository.search(qb, pageable);
        search.getContent().forEach(System.out::println);


        return DefaultResult.success(articleDocument);
    }

}
