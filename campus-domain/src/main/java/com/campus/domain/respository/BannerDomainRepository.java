package com.campus.domain.respository;

import com.campus.dao.pojo.Banner;
import com.campus.dao.pojo.News;
import com.campus.domain.BannerDomain;
import com.campus.domain.NewsDomain;
import com.campus.model.BannerModel;
import com.campus.model.param.BannerParam;
import com.campus.model.param.NewsParam;
import com.campus.mybais.mapper.BannerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by likang on 2018/8/6.
 */
@Component
public class BannerDomainRepository {

    @Autowired
    private BannerMapper bannerMapper;
    @Autowired
    private NewsDomainRepository newsDomainRepository;

    public BannerDomain createDomain(){
        return new BannerDomain(this);
    }

    public BannerDomain createDomain(BannerParam bannerParam){
        return new BannerDomain(this,bannerParam);
    }

    public List<Banner> findBannerList(Map<String,Object> param){
        return bannerMapper.findBannerList(param);
    }
    public News findNewsByNewsId(Integer newsId){
        NewsParam newsParam = new NewsParam();
        newsParam.setId(newsId);
        NewsDomain newsDomain = newsDomainRepository.createDomain(newsParam);
        return (News)newsDomain.getNews().getResult();
    }
    public Banner findBannerByNewsId(Integer newsId){
        return bannerMapper.findBannerByNewsId(newsId);
    }
    public Integer addBanner(Banner banner){
        return  bannerMapper.addBanner(banner);
    }
    public Banner findBannerById(Integer bannerId){
        return bannerMapper.findBannerById(bannerId);
    }
    public Integer editBanner(Banner banner){
        return bannerMapper.editBanner(banner);
    }
    public Integer deleteBanner(Integer bannerId){
        return bannerMapper.deleteBanner(bannerId);
    }
    /**查询banner By newsId**/
    public BannerModel getBannerByNewsId(Integer newId) {
        BannerParam bannerParam = new BannerParam();
        bannerParam.setNewsId(newId);
        return createDomain(bannerParam).getBannerByNewsId();
    }
}
