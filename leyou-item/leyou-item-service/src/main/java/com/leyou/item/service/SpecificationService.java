package com.leyou.item.service;

import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Guqiancheng
 * @create 2020-05-20 20:22
 */

@Service
public class SpecificationService {

    @Autowired
    private SpecGroupMapper groupMapper;
    @Autowired
    private SpecParamMapper specParamMapper;

    /**
     * 根据分类id查询分组
     *
     * @param cid
     * @return
     */
    public List<SpecGroup> queryGroupsByCid(Long cid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        List<SpecGroup> result1 = this.groupMapper.select(specGroup);
        for (int i = 0; i <result1.size() ; i++) {
            Long id = result1.get(i).getId();
            List<SpecParam>  list = this.queryParams(id,null,null,null);
            result1.get(i).setParams(list);
        }
        return result1;
    }

    @Autowired
    private SpecParamMapper paramMapper;


    /**
     * 根据gid查询规格参数
     *
     * @param gid
     * @return
     */
    public List<SpecParam> queryParams(Long gid, Long cid, Boolean generic, Boolean searching) {
//        SpecParam record = new SpecParam();
//        record.setGroupId(gid);
//        record.setCid(cid);
//        record.setGeneric(generic);
//        record.setSearching(searching);
//        System.out.println("cid:" + cid + "结果是：");
//        List<SpecParam> result = this.specParamMapper.select(record);
//        for(int i = 0;i < result.size();i++){
//            System.out.println("id:"+result.get(i).getId()+"cid:"+result.get(i).getCid()+"name:"+result.get(i).getName());
//        }

        Example example = new Example(SpecParam.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("groupId", gid);
        criteria.andEqualTo("cid", cid);
        criteria.andEqualTo("generic", generic);
        criteria.andEqualTo("searching", searching);
        List<SpecParam> result = this.specParamMapper.selectByExample(example);
//        for (int i = 0; i < result.size(); i++) {
//            System.out.println("id:" + result.get(i).getId() + "cid:" + result.get(i).getCid() + "name:" + result.get(i).getName());
//        }
//        return this.specParamMapper.select(record);
        return result;
    }

    public SpecParam queryById(Long id) {
        return this.specParamMapper.selectByPrimaryKey(id);
    }

    public List<SpecGroup> querySpecsByCid(Long cid) {
        // 查询规格组
        List<SpecGroup> groups = this.queryGroupsByCid(cid);
        groups.forEach(group -> {
            // 查询组内参数
            group.setParams(this.queryParams(group.getId(), null, null, null));
        });
        return groups;
    }
}

