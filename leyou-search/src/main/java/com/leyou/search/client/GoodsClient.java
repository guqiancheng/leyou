package com.leyou.search.client;

import com.leyou.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Guqiancheng
 * @create 2020-05-21 23:45
 */
@FeignClient(value = "item-service")
public interface GoodsClient extends GoodsApi {
}
