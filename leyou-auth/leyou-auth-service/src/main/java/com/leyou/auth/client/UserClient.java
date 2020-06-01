package com.leyou.auth.client;

import com.leyou.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author: Guqiancheng
 * @date: 2020-05-28 0028 21:45
 * @description:
 */
@FeignClient(value = "user-service")
public interface UserClient extends UserApi {
}
