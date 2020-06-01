package com.leyou.item.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Guqiancheng
 * @create 2020-05-20 20:20
 */
@Table(name = "tb_spec_param")
@Getter
@Setter
public class SpecParam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cid;
    private Long groupId;
    private String name;
    @Column(name = "`numeric`")
    private Boolean numeric;
    private String unit;
    private Boolean generic;
    private Boolean searching;
    private String segments;

    // getter和setter ...通过lombok实现
}
