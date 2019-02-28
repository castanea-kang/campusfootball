package com.campus.domain;

import cn.com.hisee.common.model.BusinessCodeEnum;
import cn.com.hisee.common.model.CallResult;
import com.alibaba.druid.util.StringUtils;
import com.campus.dao.pojo.Grade;
import com.campus.dao.pojo.News;
import com.campus.data.Cnst;
import com.campus.domain.respository.GradeDomainRepository;
import com.campus.domain.respository.NewsDomainRepository;
import com.campus.domain.utils.ImgUtil;
import com.campus.domain.utils.ParamCheckUtils;
import com.campus.model.BannerModel;
import com.campus.model.NewsModel;
import com.campus.model.param.GradeParam;
import com.campus.model.param.NewsParam;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by likang on 2018/8/6.
 */
public class NewsDomain {

    private static final BeanCopier p2m = BeanCopier.create(News.class, NewsModel.class, false);
    private NewsDomainRepository newsDomainRepository;
    private NewsParam newsParam;

    public NewsDomain(){

    }
    public NewsDomain(NewsDomainRepository newsDomainRepository){
        this.newsDomainRepository = newsDomainRepository;
    }
    public NewsDomain(NewsDomainRepository newsDomainRepository, NewsParam newsParam){
        this.newsDomainRepository = newsDomainRepository;
        this.newsParam = newsParam;
    }
    private NewsModel copy(News news) {
        if (news ==  null){
            return null;
        }
        NewsModel model = new NewsModel();
        p2m.copy(news,model,null);
        return  model;
    }


    public CallResult checkParam(){
        Long ctime = newsParam.getCreateTime();
        String title = newsParam.getTitle();
        Byte type = newsParam.getType();
        if (ParamCheckUtils.isBlank(ctime)  || ParamCheckUtils.isBlank(title) || type == null){
            return CallResult.fail(BusinessCodeEnum.CHECK_PARAM_NO_RESULT);
        }else {
            return CallResult.success();
        }
    }

    public CallResult checkNewsIdParam(){
        Integer newsId = newsParam.getId();
        if (ParamCheckUtils.isBlank(newsId)){
            return CallResult.fail(BusinessCodeEnum.CHECK_PARAM_NO_RESULT);
        }else {
            return CallResult.success();
        }
    }

    /**检查参数**/
    public CallResult checkPrimaryKeyParam() {
        return null;
    }
    public CallResult getNewsList(){
        List<News> newsList;
        Map<String,Object> params = new HashMap<String,Object>();
        if(!ParamCheckUtils.isBlank(newsParam.getKeyword())){
            params.put("keyword",newsParam.getKeyword());
        }
        if(newsParam.getType() != null){
            params.put("type",newsParam.getType());
        }
        PageHelper.startPage(newsParam.getPage(),newsParam.getPagesize());
        newsList = this.newsDomainRepository.findNewsList(params);
        List<NewsModel> newsModelList = new ArrayList<NewsModel>();
        if(newsList != null){
            for(News news : newsList){
                    BannerModel bannerModel = newsDomainRepository.fingNewsBannerById(news.getId());
                    if (bannerModel != null && !StringUtils.isEmpty(bannerModel.getImg())){
                        news.setImgs(bannerModel.getImg());
                    }
                NewsModel newsModel = copy(news);
                newsModelList.add(newsModel);
            }
        }
        PageInfo<NewsModel> pageInfo = new PageInfo<NewsModel>(newsModelList);
        return CallResult.success(pageInfo);
    }

    public CallResult addNews(){
        News news = new News();
        news.setCreateTime(newsParam.getCreateTime());
        news.setTitle(newsParam.getTitle());
        news.setType(newsParam.getType());
        if(!ParamCheckUtils.isBlank(newsParam.getContent())){
            news.setContent(newsParam.getContent());
            if(getImgs() != null){
                news.setImgs(getImgs());
            }
        }
        if(!ParamCheckUtils.isBlank(newsParam.getCollection())){
            news.setCollection(newsParam.getCollection());
        }
        if(!ParamCheckUtils.isBlank(newsParam.getViews())){
            news.setViews(newsParam.getViews());
        }
        if(!ParamCheckUtils.isBlank(newsParam.getIsShow())){
            news.setIsShow(newsParam.getIsShow());
        }
        Integer ret = this.newsDomainRepository.addNews(news);
        if(ret < 1){
            return CallResult.fail("添加失败！");
        }
        return CallResult.success("添加成功！");
    }

    public CallResult editNews(){
        News oldNews = this.newsDomainRepository.findNewsById(newsParam.getId());
        if(oldNews == null){
            return CallResult.fail("无此新闻！");
        }
        if(!ParamCheckUtils.isBlank(newsParam.getCreateTime())){
            oldNews.setCreateTime(newsParam.getCreateTime());
        }
        if(!ParamCheckUtils.isBlank(newsParam.getTitle())){
            oldNews.setTitle(newsParam.getTitle());
        }
        if(!ParamCheckUtils.isBlank(newsParam.getType())){
            oldNews.setType(newsParam.getType());
        }
        if(!ParamCheckUtils.isBlank(newsParam.getContent())){
            oldNews.setContent(newsParam.getContent());
            if(getImgs() != null){
                oldNews.setImgs(getImgs());
            }
        }
        if(!ParamCheckUtils.isBlank(newsParam.getCollection())){
            oldNews.setCollection(newsParam.getCollection());
        }
        if(!ParamCheckUtils.isBlank(newsParam.getViews())){
            oldNews.setViews(newsParam.getViews());
        }
        if(!ParamCheckUtils.isBlank(newsParam.getIsShow())){
            oldNews.setIsShow(newsParam.getIsShow());
        }
        Integer ret = this.newsDomainRepository.editNews(oldNews);
        if(ret < 1){
            return CallResult.fail("更新失败！");
        }
        return CallResult.success("更新成功！");
    }

    public String getImgs(){
        String imgs = null;
        List<String> imageSrcList = ImgUtil.getImageSrc(newsParam.getContent());
        if(imageSrcList != null && imageSrcList.size() > 0){
            String imgsStr = ImgUtil.listToString(imageSrcList,';');
            if(imgsStr != null && imgsStr.length()>0){
                imgsStr = imgsStr.replaceAll(Cnst.QINIU_BASE_URL,"");
                imgs = imgsStr;
            }
        }
        return imgs;
    }


    public CallResult deleteNews(){
        Integer ret = this.newsDomainRepository.deleteNews(newsParam.getId());
        if(ret < 0){
            return CallResult.fail("删除失败！");
        }
        return CallResult.success("删除成功！");
    }

    public CallResult getNews(){
        News news = this.newsDomainRepository.findNewsById(newsParam.getId());
        return CallResult.success(news);
    }
    /**
     * @Description:查询新闻资讯详情
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 9:53 2018-08-24
     */
    public CallResult getNewsDetail() {
        //查询数据库
        return new CallResult(BusinessCodeEnum.DEFAULT_SUCCESS.getCode(),"查询成功",null);
    }

    /**
     * @Description: 查询新闻list   h5界面
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 17:54 2018-08-30
     */
    public CallResult getNewsListDetail() {
        try {
            Map<String, Object> map = new HashMap<String,Object>();
            if (!ParamCheckUtils.isBlank(newsParam.getType())){
                map.put("type",newsParam.getType());
            }
            List<News> newsList = newsDomainRepository.findNewsList(map);
            for (int i = 0,size = newsList.size(); i < size ; i++){
                Integer id = newsList.get(i).getId();
                BannerModel bannerModel = newsDomainRepository.fingNewsBannerById(id);
                if (bannerModel != null && !StringUtils.isEmpty(bannerModel.getImg())){
                    newsList.get(i).setImgs(bannerModel.getImg());
                }
            }
            return new CallResult(BusinessCodeEnum.DEFAULT_SUCCESS.getCode(),"查询成功",newsList);
        }catch (Exception e){
            return  CallResult.fail(BusinessCodeEnum.DEFAULT_SYS_ERROR.getCode(),"系统异常");
        }
    }

    /**
     * @Description:通过ID查询新闻
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 17:55 2018-09-05
     */
    public NewsModel getNewsById(Integer newsId) {
        News newsById = newsDomainRepository.findNewsById(newsId);
        return  copy(newsById);
    }
}
