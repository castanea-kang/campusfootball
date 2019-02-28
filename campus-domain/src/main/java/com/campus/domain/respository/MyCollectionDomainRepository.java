package com.campus.domain.respository;

import com.campus.dao.pojo.FootballKnow;
import com.campus.dao.pojo.TblMycollection;
import com.campus.dao.pojo.TeachPlan;
import com.campus.domain.MyCollectionDomain;
import com.campus.model.FootballKnowModel;
import com.campus.model.MyCollectionModel;
import com.campus.model.NewsModel;
import com.campus.model.TeachPlanModel;
import com.campus.model.param.MyCollectionParam;
import com.campus.mybais.mapper.TblMycollectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * TO DO :
 *
 * @author 睿动体育 | wangzhiqiang
 * @create 2018-09-05 15:48
 */
@Component
public class MyCollectionDomainRepository {
    @Autowired
    TblMycollectionMapper tblMycollectionMapper;
    @Autowired
    NewsDomainRepository newsDomainRepository;
    @Autowired
    TeachPlanDomainRepository teachPlanDomainRepository;
    @Autowired
    FootballKnowDomainRepository footballKnowDomainRepository;
    public MyCollectionDomain createDomain(MyCollectionParam param){
        return  new MyCollectionDomain(this,param);
    }
    /**添加收藏**/
    public Integer addMyCollection(HashMap<String, Object> map) {
        return createDomain(new MyCollectionParam()).addMyCollection(map);
    }
    /**添加收藏 实现**/
    public Integer addMyCollectionTrue(HashMap<String, Object> map) {
        return tblMycollectionMapper.addMyCollectionTrue(map);
    }
    /**获取我的收藏**/
    public List<MyCollectionModel> getMyCollection(Integer trainerId) {
        return  createDomain(new MyCollectionParam()).getMyCollection(trainerId);
    }
    /**查询我的收藏List**/
    public List<TblMycollection> findMyCollectionList(Integer trainerId) {
        return tblMycollectionMapper.findMyCollectionList(trainerId);
    }
   /**id 查询新闻**/
    public NewsModel findNewsById(Integer eventId) {
        return newsDomainRepository.getNewsById(eventId);
    }
    /**id 查询教案+游戏方案**/
    public TeachPlanModel findTeachPlanById(Integer eventId) {
        return teachPlanDomainRepository.getTeachPlanById(eventId);
    }
    /***id 查询足球知识 ***/
    public FootballKnowModel findFootBallKnowById(Integer eventId) {
        return footballKnowDomainRepository.getFootBallKnowById(eventId);
    }
}
