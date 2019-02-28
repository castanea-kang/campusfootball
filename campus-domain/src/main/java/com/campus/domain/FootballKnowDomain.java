package com.campus.domain;

import cn.com.hisee.common.model.BusinessCodeEnum;
import cn.com.hisee.common.model.CallResult;
import com.campus.dao.pojo.FootballKnow;
import com.campus.data.Cnst;
import com.campus.domain.respository.FootballKnowDomainRepository;
import com.campus.domain.utils.ImgUtil;
import com.campus.domain.utils.ParamCheckUtils;
import com.campus.model.FootballKnowModel;
import com.campus.model.param.FootballKnowParam;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TO DO :
 *
 * @author 睿动体育 | wangzhiqiang
 * @create 2018-08-24 15:28
 */
public class FootballKnowDomain {

    private static final BeanCopier p2m = BeanCopier.create(FootballKnow.class, FootballKnowModel.class, false);

    private FootballKnowDomainRepository footballKnowDomainRepository;
    private FootballKnowParam footballKnowParam;
    private CallResult footBallKnowList;

    public  FootballKnowDomain(){

    }
    public FootballKnowDomain(FootballKnowDomainRepository footballKnowDomainRepository, FootballKnowParam param) {
        this.footballKnowDomainRepository = footballKnowDomainRepository;
        this.footballKnowParam = param;
    }
    private FootballKnowModel copy(FootballKnow footballKnow) {
        if (footballKnow ==  null){
            return null;
        }
        FootballKnowModel model = new FootballKnowModel();
        p2m.copy(footballKnow,model,null);
        return  model;
    }
    /**copy list 的实现**/
    private List<FootballKnowModel> copyList(List <FootballKnow> list){
        ArrayList<FootballKnowModel> arrayList = new ArrayList<>();
        if (list == null || list.isEmpty()){
            return  arrayList;
        }
        for (int i = 0,length = list.size();i < length ;i++ ){
            FootballKnowModel copy = copy(list.get(i));
            arrayList.add(copy);
        }
        return  arrayList;
    }
    /**参数检查**/
    public CallResult checkParam() {
        Byte type = footballKnowParam.getType();
        if (ParamCheckUtils.isBlank(type)){
            return CallResult.fail(BusinessCodeEnum.CHECK_PARAM_NO_RESULT);
        }else {
            return CallResult.success();
        }
    }
    public CallResult checkIdParam() {
        Integer id = footballKnowParam.getId();
        if (ParamCheckUtils.isBlank(id)){
            return CallResult.fail(BusinessCodeEnum.CHECK_PARAM_NO_RESULT);
        }else {
            return CallResult.success();
        }
    }

    /**
     * @Description: 查询相关足球知识列表
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 15:53 2018-08-24
     */
    public CallResult getFootBallKnowList() {
        List<FootballKnow> footballKnowList;
        List<FootballKnowModel> footballKnowModelList = new ArrayList<>();
        Map<String,Object> params = new HashMap<String,Object>();
        if(!ParamCheckUtils.isBlank(footballKnowParam.getTitle())){
            params.put("title",footballKnowParam.getTitle());
        }
        if(!ParamCheckUtils.isBlank(footballKnowParam.getType())){
            params.put("type",footballKnowParam.getType());
        }
        footballKnowList = this.footballKnowDomainRepository.findFootballKnowList(params);
        for(FootballKnow footballKnow : footballKnowList){
            FootballKnowModel footBallKnowModel = copy(footballKnow);
            footballKnowModelList.add(footBallKnowModel);
        }
        return CallResult.success(footballKnowModelList);
    }
    public CallResult getFootballKnow(){
        FootballKnow footballKnow = this.footballKnowDomainRepository.findFootballKnowById(footballKnowParam.getId());
        return CallResult.success(footballKnow);
    }
    public CallResult addFootballKnow(){
        FootballKnow footballKnow = new FootballKnow();
        if(!ParamCheckUtils.isBlank(footballKnowParam.getType())){
            footballKnow.setType(footballKnowParam.getType());
        }
        if(!ParamCheckUtils.isBlank(footballKnowParam.getTitle())){
            footballKnow.setTitle(footballKnowParam.getTitle());
        }
        if(!ParamCheckUtils.isBlank(footballKnowParam.getContent())){
            if(getImgs() != null){
                footballKnow.setImgs(getImgs());
            }
            footballKnow.setContent(footballKnowParam.getContent());
        }
        if(!ParamCheckUtils.isBlank(footballKnowParam.getCollection())){
            footballKnow.setCollection(footballKnowParam.getCollection());
        }
        if(!ParamCheckUtils.isBlank(footballKnowParam.getViews())){
            footballKnow.setViews(footballKnowParam.getViews());
        }
        Integer ret = this.footballKnowDomainRepository.addFootballKnow(footballKnow);
        if(ret < 1){
            return CallResult.fail("添加失败！");
        }
        return CallResult.success("添加成功！");
    }
    public CallResult editFootballKnow(){
        FootballKnow oldFootballKnow = this.footballKnowDomainRepository.findFootballKnowById(footballKnowParam.getId());
        if(oldFootballKnow == null){
            return CallResult.fail("记录不存在！");
        }
        if(!ParamCheckUtils.isBlank(footballKnowParam.getType())){
            oldFootballKnow.setType(footballKnowParam.getType());
        }
        if(!ParamCheckUtils.isBlank(footballKnowParam.getTitle())){
            oldFootballKnow.setTitle(footballKnowParam.getTitle());
        }
        if(!ParamCheckUtils.isBlank(footballKnowParam.getContent())){
            if(getImgs() != null){
                oldFootballKnow.setImgs(getImgs());
            }
            oldFootballKnow.setContent(footballKnowParam.getContent());
        }
        if(!ParamCheckUtils.isBlank(footballKnowParam.getImgs())){
            oldFootballKnow.setImgs(footballKnowParam.getImgs());
        }
        if(!ParamCheckUtils.isBlank(footballKnowParam.getCollection())){
            oldFootballKnow.setCollection(footballKnowParam.getCollection());
        }
        if(!ParamCheckUtils.isBlank(footballKnowParam.getViews())){
            oldFootballKnow.setViews(footballKnowParam.getViews());
        }
        Integer ret = this.footballKnowDomainRepository.editFootballKnow(oldFootballKnow);
        if(ret < 1){
            return CallResult.fail("编辑失败！");
        }
        return CallResult.success("编辑成功！");
    }
    public String getImgs(){
        String imgs = null;
        List<String> imageSrcList = ImgUtil.getImageSrc(footballKnowParam.getContent());
        if(imageSrcList != null && imageSrcList.size() > 0){
            String imgsStr = ImgUtil.listToString(imageSrcList,';');
            if(imgsStr != null && imgsStr.length()>0){
                imgsStr = imgsStr.replaceAll(Cnst.QINIU_BASE_URL,"");
                imgs = imgsStr;
            }
        }
        return imgs;
    }
    public CallResult deleteFootballKnow(){
        Integer ret = this.footballKnowDomainRepository.deleteFootballKnow(footballKnowParam.getId());
        if(ret < 1){
            return CallResult.fail("删除失败！");
        }
        return CallResult.success("删除成功！");
    }

    /**
     * @Description:通过关键词检索足球信息
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 15:23 2018-09-03
     */

    public List<FootballKnowModel> findByKeyword() {
        String keyword = footballKnowParam.getKeyword();
        HashMap<String, Object> map = new HashMap<>();
        map.put("title",keyword);
        List<FootballKnow> footballKnowList = footballKnowDomainRepository.findFootballKnowList(map);
        return copyList(footballKnowList);
    }

    /**
     * @Description:获取足球知识byId
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 18:02 2018-09-05
     */
    public FootballKnowModel getFootBallKnowById() {
        FootballKnow footballKnowById = footballKnowDomainRepository.findFootballKnowById(footballKnowParam.getId());
        return copy(footballKnowById);
    }

    /**
     * @Description:增加关注量
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 17:39 2018-11-07
     */
    public CallResult addViews() {
        try{
            FootballKnow footballKnowById = footballKnowDomainRepository.findFootballKnowById(footballKnowParam.getId());
            if (footballKnowById != null){
                Long views = footballKnowById.getViews();
                views = views + 1;
                footballKnowById.setViews(views);
                footballKnowDomainRepository.editFootballKnow(footballKnowById);
                FootballKnowModel copy = copy(footballKnowById);
                return new CallResult(BusinessCodeEnum.DEFAULT_SUCCESS.getCode(),"更新成功",copy);
            }
            return CallResult.fail(BusinessCodeEnum.CHECK_BIZ_NO_RESULT.getCode(),"更新失败,相关足球知识不存在");

        }catch (Exception e){
            e.printStackTrace();
            return CallResult.fail(BusinessCodeEnum.DEFAULT_SYS_ERROR.getCode(),"系统错误");
        }
    }
}
