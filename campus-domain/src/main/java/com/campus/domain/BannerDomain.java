package com.campus.domain;

import cn.com.hisee.common.model.BusinessCodeEnum;
import cn.com.hisee.common.model.CallResult;
import cn.com.hisee.common.utils.TimeUtil;
import com.campus.dao.pojo.Banner;
import com.campus.dao.pojo.News;
import com.campus.data.Cnst;
import com.campus.domain.respository.BannerDomainRepository;
import com.campus.domain.respository.NewsDomainRepository;
import com.campus.domain.utils.ImgUtil;
import com.campus.domain.utils.ParamCheckUtils;
import com.campus.model.BannerModel;
import com.campus.model.NewsModel;
import com.campus.model.param.BannerParam;
import com.campus.model.param.NewsParam;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by likang on 2018/8/6.
 */
public class BannerDomain {

    private static final BeanCopier p2m = BeanCopier.create(Banner.class, BannerModel.class, false);
    private BannerDomainRepository bannerDomainRepository;
    private BannerParam bannerParam;

    public BannerDomain(){

    }
    public BannerDomain(BannerDomainRepository bannerDomainRepository){
        this.bannerDomainRepository = bannerDomainRepository;
    }
    public BannerDomain(BannerDomainRepository bannerDomainRepository, BannerParam bannerParam){
        this.bannerDomainRepository = bannerDomainRepository;
        this.bannerParam = bannerParam;
    }
    private BannerModel copy(Banner banner) {
        if (banner ==  null){
            return null;
        }
        BannerModel model = new BannerModel();
        p2m.copy(banner,model,null);
        return  model;
    }

    public CallResult checkParam(){
        Integer id = bannerParam.getId();
        if (ParamCheckUtils.isBlank(id)){
            return CallResult.fail(BusinessCodeEnum.CHECK_PARAM_NO_RESULT);
        }else {
            return CallResult.success();
        }
    }

    public CallResult checkParamParam(){
        String img = bannerParam.getImg();
        Integer newsId = bannerParam.getNewsId();
        if (ParamCheckUtils.isBlank(img) || ParamCheckUtils.isBlank(newsId)){
            return CallResult.fail(BusinessCodeEnum.CHECK_PARAM_NO_RESULT);
        }else {
            return CallResult.success();
        }
    }

    public CallResult getBannerList(){
        List<Banner> bannerList;
        Map<String,Object> params = new HashMap<String,Object>();
        if(!ParamCheckUtils.isBlank(bannerParam.getPubdate())){
            String pubdate = TimeUtil.getyyyyMMdd1(bannerParam.getPubdate());
            Long startTime = TimeUtil.getTime(pubdate);
            Long endTime = startTime + 86400000;
            params.put("startTime",startTime);
            params.put("endTime",endTime);
        }
        if(bannerParam.getNewsType() != null){
            params.put("newsType",bannerParam.getNewsType());
        }
        bannerList = this.bannerDomainRepository.findBannerList(params);
        List<BannerModel> bannerModelList = new ArrayList<BannerModel>();
        if(bannerList != null){
            for(Banner banner : bannerList){
                BannerModel bannerModel = copy(banner);
                News news = this.bannerDomainRepository.findNewsByNewsId(banner.getNewsId());
                bannerModel.setNewsTitle(news.getTitle());
                bannerModelList.add(bannerModel);
            }
        }
        return CallResult.success(bannerModelList);
    }
    public CallResult addBanner(){
        Banner oldBanner = this.bannerDomainRepository.findBannerByNewsId(bannerParam.getNewsId());
        if(oldBanner != null){
            return CallResult.fail("此banner已存在！");
        }
        Banner banner = new Banner();
        if(!ParamCheckUtils.isBlank(bannerParam.getImg())){
            String simg = bannerParam.getImg();
            simg = simg.replaceAll(Cnst.QINIU_BASE_URL,"");
            banner.setImg(simg);
        }
        banner.setNewsId(bannerParam.getNewsId());
        if(bannerParam.getStartTime() != null){
            banner.setStartTime(bannerParam.getStartTime());
        }
        if(bannerParam.getEndTime() != null){
            banner.setEndTime(bannerParam.getEndTime());
        }
        if(bannerParam.getStartTime() != null && bannerParam.getEndTime() != null){
            Long time = System.currentTimeMillis();
            if(time >= bannerParam.getStartTime() && time <= bannerParam.getEndTime()){
                banner.setIsShow(Banner.IS_SHOW);
            }
        }
        Integer ret = this.bannerDomainRepository.addBanner(banner);
        if(ret < 1){
            return CallResult.fail("banner添加失败！");
        }
        return CallResult.success("添加成功！");
    }
    public CallResult editBanner(){
        Banner oldBanner = this.bannerDomainRepository.findBannerById(bannerParam.getId());
        if(oldBanner == null){
            return CallResult.fail("此banner不存在！");
        }
        if(!ParamCheckUtils.isBlank(bannerParam.getImg())){
            String simg = bannerParam.getImg();
            simg = simg.replaceAll(Cnst.QINIU_BASE_URL,"");
            oldBanner.setImg(simg);
        }
        if(bannerParam.getStartTime() != null){
            oldBanner.setStartTime(bannerParam.getStartTime());
        }
        if(bannerParam.getEndTime() != null){
            oldBanner.setEndTime(bannerParam.getEndTime());
        }
        if(oldBanner.getStartTime() != null && oldBanner.getEndTime() != null){
            Long time = System.currentTimeMillis();
            if(time >= oldBanner.getStartTime() && time <= oldBanner.getEndTime()){
                oldBanner.setIsShow(Banner.IS_SHOW);
            }
        }
        if(bannerParam.getNewsId() != null){
            oldBanner.setNewsId(bannerParam.getNewsId());
        }
        Integer ret = this.bannerDomainRepository.editBanner(oldBanner);
        if(ret < 1){
            return CallResult.fail("编辑失败!");
        }
        return CallResult.success("编辑成功！");
    }
    public CallResult getBanner(){
        Banner banner = this.bannerDomainRepository.findBannerById(bannerParam.getId());
        return CallResult.success(copy(banner));
    }
    public CallResult deleteBanner(){
        Integer ret = this.bannerDomainRepository.deleteBanner(bannerParam.getId());
        if(ret < 1){
            return CallResult.fail("删除失败!");
        }
        return CallResult.success("删除成功!");
    }

    public BannerModel getBannerByNewsId() {
        Banner bannerByNewsId = bannerDomainRepository.findBannerByNewsId(bannerParam.getNewsId());
        return copy(bannerByNewsId);
    }
}
