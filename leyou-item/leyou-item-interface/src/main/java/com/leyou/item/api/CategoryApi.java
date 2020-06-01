package com.leyou.item.api;

import com.leyou.item.pojo.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Guqiancheng
 * @create 2020-05-21 23:46
 */
@RequestMapping("category")
public interface CategoryApi {

    @GetMapping("names")
    ResponseEntity<List<String>> queryNamesByIds(@RequestParam("ids") List<Long> ids);

    /*new*/
    @GetMapping("list/ids")
    List<Category> queryByIds(@RequestParam("ids") List<Long> ids);

}
