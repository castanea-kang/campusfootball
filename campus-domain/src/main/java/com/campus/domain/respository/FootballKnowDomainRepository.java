package com.campus.domain.respository;

import com.campus.dao.pojo.FootballKnow;
import com.campus.domain.FootballKnowDomain;
import com.campus.model.FootballKnowModel;
import com.campus.model.param.FootballKnowParam;
import com.campus.mybais.mapper.FootballKnowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * TO DO :
 *
 * @author 睿动体育 | wangzhiqiang
 * @create 2018-08-24 15:25
 */
@Component
public class FootballKnowDomainRepository {

    @Autowired
    FootballKnowMapper footballKnowMapper;

    public FootballKnowDomain createDomain(FootballKnowParam param) {
        return new FootballKnowDomain(this,param);
    }

    public List<FootballKnow> findFootballKnowList(Map<String,Object> params){
        return footballKnowMapper.findFootballKnowList(params);
    }
    public FootballKnow findFootballKnowById(Integer knowId){
        return footballKnowMapper.findFootballKnowById(knowId);
    }
    public Integer addFootballKnow(FootballKnow footballKnow){
        return footballKnowMapper.addFootballKnow(footballKnow);
    }
    public Integer editFootballKnow(FootballKnow footballKnow){
        return footballKnowMapper.editFootballKnow(footballKnow);
    }
    public Integer deleteFootballKnow(Integer knowId){
        return footballKnowMapper.deleteFootballKnow(knowId);
    }
    /*关键词检索足球知识**/
    public List<FootballKnowModel> getFootballKownByKeyword(String keyword) {
        FootballKnowParam footballKnowParam = new FootballKnowParam();
        footballKnowParam.setKeyword(keyword);
        return createDomain(footballKnowParam).findByKeyword();
    }
    /**通过ID 获取足球知识**/
    public FootballKnowModel getFootBallKnowById(Integer footId) {
        FootballKnowParam footballKnowParam = new FootballKnowParam();
        footballKnowParam.setId(footId);
        return createDomain(footballKnowParam).getFootBallKnowById();
    }
}
