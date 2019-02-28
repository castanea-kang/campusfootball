package com.campus.mybais.mapper;

import com.campus.dao.pojo.Banner;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class BannerMapper {

    @Autowired
    private final SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public BannerMapper(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public List<Banner> findBannerList(Map<String,Object> param){
        return sqlSessionTemplate.selectList("findBannerList",param);
    }
    public Banner findBannerByNewsId(Integer newsId){
        return sqlSessionTemplate.selectOne("findBannerByNewsId",newsId);
    }
    public Integer addBanner(Banner banner){
        return sqlSessionTemplate.insert("insertBannerSelective",banner);
    }
    public Banner findBannerById(Integer id){
        return sqlSessionTemplate.selectOne("findBannerById",id);
    }
    public Integer editBanner(Banner banner){
        return sqlSessionTemplate.insert("updateBannerByIdSelective",banner);
    }
    public Integer deleteBanner(Integer id){
        return sqlSessionTemplate.delete("deleteBannerById",id);
    }
}