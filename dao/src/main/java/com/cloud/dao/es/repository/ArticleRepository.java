package com.cloud.dao.es.repository;

import com.cloud.dao.es.entity.ArticleDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleRepository extends ElasticsearchRepository<ArticleDocument,Long> {
}
