package com.campus.domain.respository;

import com.campus.dao.pojo.News;
import com.campus.domain.NewsDomain;
import com.campus.model.BannerModel;
import com.campus.model.NewsModel;
import com.campus.model.param.NewsParam;
import com.campus.mybais.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by likang on 2018/8/6.
 */
@Component
public class NewsDomainRepository {

    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private  BannerDomainRepository bannerDomainRepository;

    public NewsDomain createDomain(){
        return new NewsDomain(this);
    }

    public NewsDomain createDomain(NewsParam newsParam){
        return new NewsDomain(this,newsParam);
    }

    public List<News> findNewsList(Map<String,Object> param){
        return newsMapper.findNewsList(param);
    }
    public Integer addNews(News news){
        return newsMapper.addNews(news);
    }

    public News findNewsById(Integer newsId){
        return newsMapper.findNewsById(newsId);
    }
    public Integer editNews(News news){
        return newsMapper.editNews(news);
    }
    public Integer deleteNews(Integer newsId){
        return newsMapper.deleteNews(newsId);
    }

    public BannerModel fingNewsBannerById(Integer id) {
        return bannerDomainRepository.getBannerByNewsId(id);
    }
    /***id 查询新闻**/
    public NewsModel getNewsById(Integer newsId) {
        return createDomain(new NewsParam()).getNewsById(newsId);
    }
}
