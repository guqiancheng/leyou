package com.leyou.cart.leyoucart.client;

import com.leyou.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author: Guqiancheng
 * @date: 2020-05-30 0030 0:12
 * @description:
 */
@FeignClient("item-service")
public interface GoodsClient extends GoodsApi {
}
