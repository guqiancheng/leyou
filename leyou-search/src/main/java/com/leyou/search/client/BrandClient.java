package com.leyou.search.client;

import com.leyou.item.api.BrandApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Guqiancheng
 * @create 2020-05-21 23:49
 */
@FeignClient(value = "item-service")
public interface BrandClient extends BrandApi {
}
