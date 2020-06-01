package com.goods.web.leyougoodsweb.client;

import com.leyou.item.api.BrandApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author: Guqiancheng
 * @date: 2020-05-25 0025 1:03
 * @description:
 */
@FeignClient(value="item-service")
public interface BrandClient extends BrandApi {
}
