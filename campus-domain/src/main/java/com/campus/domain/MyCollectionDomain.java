package com.campus.domain;

import com.campus.dao.pojo.TblMycollection;
import com.campus.dao.pojo.TeachPlan;
import com.campus.domain.respository.MyCollectionDomainRepository;
import com.campus.model.*;
import com.campus.model.param.MyCollectionParam;
import org.springframework.cglib.beans.BeanCopier;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * TO DO :
 *
 * @author 睿动体育 | wangzhiqiang
 * @create 2018-09-05 15:49
 */
public class MyCollectionDomain {
    private   static final BeanCopier p2m = BeanCopier.create(TblMycollection.class, MyCollectionModel.class,false);
    private MyCollectionDomainRepository myCollectionDomainRepository;
    private MyCollectionParam param;
    public MyCollectionDomain(MyCollectionDomainRepository myCollectionDomainRepository, MyCollectionParam param) {
        this.myCollectionDomainRepository = myCollectionDomainRepository;
        this.param = param;
    }
    /**copy 实现**/
    private  MyCollectionModel copy(TblMycollection t){
        MyCollectionModel myCollectionModel = new MyCollectionModel();
        if (t == null){
            return null;
        }
        p2m.copy(t,myCollectionModel,null);
        return myCollectionModel;
    }
    /*copy list 实现**/
    private  List<MyCollectionModel>  copyList(List<TblMycollection> list){
        ArrayList<MyCollectionModel> arrayList = new ArrayList<>();
        if (list == null || list.isEmpty()){
            return  arrayList;
        }
        for (TblMycollection t:list){
            arrayList.add(copy(t));
        }
        return arrayList;
    }

    /**
     * @Description:添加收藏
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 15:55 2018-09-05
     */
    public Integer addMyCollection(HashMap<String, Object> map) {
        return myCollectionDomainRepository.addMyCollectionTrue(map);
    }

    /**
     * @Description:查询我的收藏
     * * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 16:13 2018-09-05
     */
    public List<MyCollectionModel> getMyCollection(Integer trainerId) {
       List <TblMycollection> list = myCollectionDomainRepository.findMyCollectionList(trainerId);
        List<MyCollectionModel> arrayList = new ArrayList<>();
       if (list.isEmpty()) return arrayList;
       for (TblMycollection t:list){
           MyCollectionModel copy = copy(t);
           Byte type = copy.getType();
           Integer eventId = t.getEventId();
           if ("1".equals(type+"")){//新闻

               //查询对应新闻
               NewsModel model =  myCollectionDomainRepository.findNewsById(eventId);
               copy.setTitle(model.getTitle());
               copy.setContent(model.getContent());
               copy.setImagesUrl(model.getImgs());
           }else if ("2".equals(type+"")){//教案+游戏方案
               TeachPlanModel teachPlanModel = myCollectionDomainRepository.findTeachPlanById(eventId);
               copy.setTitle(teachPlanModel.getTitle());
               copy.setUrl(teachPlanModel.getUrl());
           }else if ("2".equals(type+"")){//足球知识
               FootballKnowModel footballKnowModel = myCollectionDomainRepository.findFootBallKnowById(eventId);
               copy.setTitle(footballKnowModel.getTitle());
               copy.setContent(footballKnowModel.getContent());
           }
           arrayList.add(copy);
       }
        return arrayList;
    }
}
