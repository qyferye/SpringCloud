import com.cloud.es.EsApplication;
import com.cloud.es.entity.Cloud;
import com.cloud.es.repository.CloudRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.AvgAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EsApplication.class)
public class Test {
    @Resource
    private CloudRepository cloudRepository;
    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;


    @org.junit.Test
    public  void  test1(){
        System.out.println(cloudRepository.findById(7L));
    }

    @org.junit.Test
    public  void  boolAndFilter(){
        //
        QueryBuilder queryBuilder = QueryBuilders.boolQuery().filter(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("age","27")));
        Iterable<Cloud> search = cloudRepository.search(queryBuilder);
        search.forEach(System.out::println);

    }

    @org.junit.Test
    public  void  bool(){
        //
        QueryBuilder queryBuilder = QueryBuilders.boolQuery().filter(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("age","27")));
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //nativeSearchQueryBuilder.withQuery(queryBuilder);

        SortBuilder sortBuilder = SortBuilders.fieldSort("id");
        sortBuilder.order(SortOrder.DESC);
        //nativeSearchQueryBuilder.withSort(sortBuilder);

        AvgAggregationBuilder salary_min = AggregationBuilders.avg("salary_min").field("salary_min");

        TermsAggregationBuilder age = AggregationBuilders.terms("age").field("age.keyword");
        age.subAggregation(salary_min);
        nativeSearchQueryBuilder.addAggregation(age);
        AggregatedPage<Cloud> search = (AggregatedPage<Cloud>) cloudRepository.search(nativeSearchQueryBuilder.build());

        StringTerms terms = (StringTerms)search.getAggregation("age");
        terms.getBuckets().forEach(term->{
            System.out.println("key:"+term.getKey());
            System.out.println("getDocCount"+term.getDocCount());
            InternalAvg sm = (InternalAvg)(term.getAggregations().asMap().get("salary_min"));
            System.out.println("salary_min:"+sm.getValue()+"\n");
            /*InternalAggregations aggregations = (InternalAggregations) term.getAggregations();
            aggregations.forEach(avg->{
                InternalAvg avgs =(InternalAvg) avg;
                System.out.println("avgs::"+avgs.getValue());
            });*/

        });
        System.out.println("---------------------------------------");
        System.out.println(terms.toString());

    }

}
