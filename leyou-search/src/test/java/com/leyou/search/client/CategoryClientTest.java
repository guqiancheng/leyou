package com.leyou.search.client;

import com.leyou.search.LeyouSearchApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author Guqiancheng
 * @create 2020-05-21 23:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LeyouSearchApplication.class)
public class CategoryClientTest {

    @Autowired
    private CategoryClient categoryClient;

    @Test
    public void testQueryCategories() {
        ResponseEntity<List<String>> names1 = this.categoryClient.queryNamesByIds(Arrays.asList(1L, 2L, 3L));
        List<String> names2 = names1.getBody();
        names2.forEach(System.out::println);
    }
}
