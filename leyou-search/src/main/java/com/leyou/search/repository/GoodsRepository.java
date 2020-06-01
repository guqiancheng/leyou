package com.leyou.search.repository;

import com.leyou.search.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Guqiancheng
 * @create 2020-05-22 0:35
 */

public interface GoodsRepository extends ElasticsearchRepository<Goods, Long> {
}
