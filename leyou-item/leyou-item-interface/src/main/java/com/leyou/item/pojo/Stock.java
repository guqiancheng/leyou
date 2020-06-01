package com.leyou.item.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Guqiancheng
 * @create 2020-05-20 20:32
 */
@Table(name = "tb_stock")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    @Id
    private Long skuId;
    private Integer seckillStock;// 秒杀可用库存
    private Integer seckillTotal;// 已秒杀数量
    private Integer stock;// 正常库存
}
