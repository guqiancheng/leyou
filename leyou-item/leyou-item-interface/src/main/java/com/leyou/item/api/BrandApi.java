package com.leyou.item.api;

import com.leyou.item.pojo.Brand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Guqiancheng
 * @create 2020-05-21 23:47
 */
@RequestMapping("brand")
public interface BrandApi {

    @GetMapping("{id}")
    Brand queryBrandById(@PathVariable("id") Long id);

    /*new*/
    @GetMapping("list")
    List<Brand> queryBrandsByIds(@RequestParam("ids") List<Long> ids);
}
