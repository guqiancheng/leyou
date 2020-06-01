package com.leyou.item.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author Guqiancheng
 * @create 2020-05-20 20:20
 */
@Table(name = "tb_spec_group")
@Getter
@Setter
public class SpecGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cid;

    private String name;

    @Transient
    private List<SpecParam> params; //该组下的所有规格参数集合

    // getter和setter省略 通过lombok实现
}
