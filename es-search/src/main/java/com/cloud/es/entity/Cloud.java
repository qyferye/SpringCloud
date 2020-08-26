package com.cloud.es.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
@Document(indexName = "cloud-up", type = "_doc")
@Data
public class Cloud implements Serializable {
    @Id
    private Long id;
    @Field(type = FieldType.Text)
    private String name;

    private String work;

    private String age;

    private int salary_min;

    private Company company;


/**
 *
 * Spring Data通过注解来声明字段的映射属性，有下面的三个注解：
 *
 *     @Document 作用在类，标记实体类为文档对象，一般有两个属性
 *         indexName：对应索引库名称
 *         type：对应在索引库中的类型
 *         shards：分片数量，默认5
 *         replicas：副本数量，默认1
 *     @Id 作用在成员变量，标记一个字段作为id主键
 *     @Field 作用在成员变量，标记为文档的字段，并指定字段映射属性：
 *         type：字段类型，是枚举：FieldType，可以是text、long、short、date、integer、object等
 *             text：存储数据时候，会自动分词，并生成索引
 *             keyword：存储数据时候，不会分词建立索引
 *             Numerical：数值类型，分两类
 *                 基本数据类型：long、interger、short、byte、double、float、half_float
 *                 浮点数的高精度类型：scaled_float
 *                     需要指定一个精度因子，比如10或100。elasticsearch会把真实值乘以这个因子后存储，取出时再还原。
 *             Date：日期类型
 *                 elasticsearch可以对日期格式化为字符串存储，但是建议我们存储为毫秒值，存储为long，节省空间。
 *         index：是否索引，布尔类型，默认是true
 *         store：是否存储，布尔类型，默认是false
 *         analyzer：分词器名称，这里的ik_max_word即使用ik分词器
 * ————————————————
 * 创建索引 可以根据 这些注解内容使用
 *         ElasticsearchTemplate elasticsearchTemplate;
 *         elasticsearchTemplate.createIndex(Cloud.class); 根据类的@Documemnt信息自动生成
 *         elasticsearchTemplate.putMapping(Cloud.class); 根据@Id  @Field  自动完成映射
 * 原文链接：https://blog.csdn.net/fhkkkbfgggjk/java/article/details/85166375
 *
 * */

}