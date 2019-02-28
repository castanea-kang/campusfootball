package com.campus.domain;

import cn.com.hisee.common.model.BusinessCodeEnum;
import cn.com.hisee.common.model.CallResult;
import com.campus.dao.pojo.FootballKnow;
import com.campus.dao.pojo.Grade;
import com.campus.dao.pojo.TeachPlan;
import com.campus.data.Cnst;
import com.campus.domain.respository.TeachPlanDomainRepository;
import com.campus.domain.utils.ParamCheckUtils;
import com.campus.model.FootballKnowModel;
import com.campus.model.TeachPlanModel;
import com.campus.model.param.TeachPlanParam;
import org.omg.CORBA.Request;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by likang on 2018/8/6.
 */
public class TeachPlanDomain {

    private static final BeanCopier p2m = BeanCopier.create(TeachPlan.class, TeachPlanModel.class, false);
    private TeachPlanDomainRepository teachPlanDomainRepository;
    private TeachPlanParam teachPlanParam;

    public TeachPlanDomain(){

    }
    public TeachPlanDomain(TeachPlanDomainRepository teachPlanDomainRepository){
        this.teachPlanDomainRepository = teachPlanDomainRepository;
    }
    public TeachPlanDomain(TeachPlanDomainRepository teachPlanDomainRepository, TeachPlanParam teachPlanParam){
        this.teachPlanDomainRepository = teachPlanDomainRepository;
        this.teachPlanParam = teachPlanParam;
    }
    private TeachPlanModel copy(TeachPlan teachPlan) {
        if (teachPlan ==  null){
            return null;
        }
        TeachPlanModel model = new TeachPlanModel();
        p2m.copy(teachPlan,model,null);
        return  model;
    }
    /**copy list*/
    private List<TeachPlanModel> copyList(List<TeachPlan> list){
        ArrayList<TeachPlanModel> arrayList = new ArrayList<>();
        if (list == null || list.isEmpty()){
            return  arrayList;
        }
        for (TeachPlan teachPlan:list){
            TeachPlanModel copy = copy(teachPlan);
            arrayList.add(copy);
        }
        return  arrayList;
    }

    public CallResult checkParam(){
        Short type = teachPlanParam.getType();
        if (ParamCheckUtils.isBlank(type)){
            return CallResult.fail(BusinessCodeEnum.CHECK_PARAM_NO_RESULT);
        }else {
            return CallResult.success();
        }
    }
    public CallResult checkDeleteParam(){
        List<Integer> ids = teachPlanParam.getPlanIds();
        if (ParamCheckUtils.isBlank(ids)){
            return CallResult.fail(BusinessCodeEnum.CHECK_PARAM_NO_RESULT);
        }else {
            return CallResult.success();
        }
    }
    public CallResult checkIdParam(){
        Integer id = teachPlanParam.getId();
        if (ParamCheckUtils.isBlank(id)){
            return CallResult.fail(BusinessCodeEnum.CHECK_PARAM_NO_RESULT);
        }else {
            return CallResult.success();
        }
    }
    /**
     * @Description:查询教学方案list
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 15:07 2018-08-31
     */
    public CallResult getTeachPlanList(){
        try {
            List<TeachPlan> teachPlanList;
            Map<String,Object> params = new HashMap<String,Object>();
            /*****/
            if(!ParamCheckUtils.isBlank(teachPlanParam.getType())){
                params.put("type",teachPlanParam.getType());
            }
            if(!ParamCheckUtils.isBlank(teachPlanParam.getGradeId())){
                params.put("gradeId",teachPlanParam.getGradeId());
            }
            if(!ParamCheckUtils.isBlank(teachPlanParam.getKeyword())){
                params.put("keyword",teachPlanParam.getKeyword());
            }
            teachPlanList = this.teachPlanDomainRepository.findTeachPlanList(params);
            List<TeachPlanModel> teachPlanModelList = new ArrayList<TeachPlanModel>();
            if(teachPlanList != null){
                for(TeachPlan teachPlan : teachPlanList){
                    TeachPlanModel teachPlanModel = copy(teachPlan);
                    if(teachPlan.getGradeId() == null){
                        teachPlanModel.setGradeStr("所有年级");
                    }else{
                        Grade grade = this.teachPlanDomainRepository.findGradeByGradeId(teachPlan.getGradeId());
                        if(grade != null){
                            teachPlanModel.setGradeStr(grade.getName());
                        }
                    }
                    teachPlanModelList.add(teachPlanModel);
                }
            }
            return CallResult.success(teachPlanModelList);
        }catch (Exception e){
            e.printStackTrace();
            return CallResult.fail(BusinessCodeEnum.DEFAULT_SYS_ERROR.getCode(),"系统异常");
        }
    }

    /**
     * @Description:搜索文件(tile内容模糊搜索)
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 16:08 2018-08-24
     */
    public CallResult searchTeachPlanList() {
        String keyword = teachPlanParam.getKeyword();
        Short type = teachPlanParam.getType();
        if (type == 3){
            //查询足球知识表
            List <FootballKnowModel>footballKnowModels =  teachPlanDomainRepository.findFootballKownByKeyword(keyword);
            return new CallResult(BusinessCodeEnum.DEFAULT_SUCCESS.getCode(),"查询成功",  footballKnowModels);
        }else {
            //查询教案
            HashMap<String, Object> map = new HashMap<>();
            map.put("keyword",keyword);
            List<TeachPlan> teachPlanList = teachPlanDomainRepository.findTeachPlanList(map);
            return new CallResult(BusinessCodeEnum.DEFAULT_SUCCESS.getCode(),"查询成功",  copyList(teachPlanList));
        }

    }

    public CallResult addTeachPlan(){
        TeachPlan teachPlan = new TeachPlan();
        teachPlan.setType(teachPlanParam.getType());
        if(teachPlanParam.getGradeId() != null){
            teachPlan.setGradeId(teachPlanParam.getGradeId());
            teachPlan.setLevel(getLevel());
        }
        if(!ParamCheckUtils.isBlank(teachPlanParam.getTitle())){
            teachPlan.setTitle(teachPlanParam.getTitle());
        }
        if(!ParamCheckUtils.isBlank(teachPlanParam.getUrl())){
            String url = teachPlanParam.getUrl().replaceAll(Cnst.QINIU_BASE_URL,"");
            teachPlan.setUrl(url);
        }
        Integer ret = this.teachPlanDomainRepository.addTeachPlan(teachPlan);
        if(ret <1){
            return CallResult.fail("存储失败！");
        }
        return CallResult.success("成功！");
    }
    public short getLevel(){
        Grade grade = this.teachPlanDomainRepository.findGradeByGradeId(teachPlanParam.getGradeId());
        short level = 1;
        if(grade != null && grade.getParentId() != null){
            if(grade.getParentId() == 3){
                level = 2;
            }else if(grade.getParentId() == 4){
                level = 3;
            }
        }
        return level;
    }

    public CallResult deleteTeachPlan(){
        for(Integer planId:teachPlanParam.getPlanIds()){
            Integer ret = this.teachPlanDomainRepository.deleteTeachPlan(planId);
            if(ret < 1){
                return CallResult.fail("删除失败！");
            }
        }

        return CallResult.success("删除成功！");
    }

    public CallResult editTeachPlan(){
        TeachPlan teachPlan = this.teachPlanDomainRepository.findTeachPlanById(teachPlanParam.getId());
        if(teachPlan == null){
            return CallResult.fail("记录不存在！");
        }
        if(!ParamCheckUtils.isBlank(teachPlanParam.getTitle())){
            teachPlan.setTitle(teachPlanParam.getTitle());
        }
        if(!ParamCheckUtils.isBlank(teachPlanParam.getUrl())){
            String url = teachPlanParam.getUrl().replaceAll(Cnst.QINIU_BASE_URL,"");
            teachPlan.setUrl(url);
        }
        if(!ParamCheckUtils.isBlank(teachPlanParam.getGradeId())){
            teachPlan.setGradeId(teachPlanParam.getGradeId());
            teachPlan.setLevel(getLevel());
        }
        if(!ParamCheckUtils.isBlank(teachPlanParam.getDownload())){
            teachPlan.setDownload(teachPlanParam.getDownload());
        }
        if(!ParamCheckUtils.isBlank(teachPlanParam.getCollection())){
            teachPlan.setViews(teachPlanParam.getViews());
        }
        if(!ParamCheckUtils.isBlank(teachPlanParam.getType())){
            teachPlan.setType(teachPlanParam.getType());
        }
        Integer ret = this.teachPlanDomainRepository.editTeachPlan(teachPlan);
        if(ret < 1){
            return CallResult.fail("编辑失败！");
        }
        return CallResult.success("编辑成功！");
    }

    /**
     * @Description: 通过Id 查询
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 17:59 2018-09-05
     */
    public TeachPlanModel getTeachPlanById(Integer teachPlanId) {
        TeachPlan teachPlanById = teachPlanDomainRepository.findTeachPlanById(teachPlanId);
        TeachPlanModel copy = copy(teachPlanById);
        return copy;
    }

    /**
     * @Description: 添加关注
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 18:03 2018-11-07
     */
    public CallResult addViews() {
        try{
            TeachPlan teachPlan = teachPlanDomainRepository.findTeachPlanById(teachPlanParam.getId());
            if (teachPlan != null){
                Long views = teachPlan.getViews();
                views = views + 1;
                teachPlan.setViews(views);
                teachPlanDomainRepository.editTeachPlan(teachPlan);
                TeachPlanModel copy = copy(teachPlan);
                return new CallResult(BusinessCodeEnum.DEFAULT_SUCCESS.getCode(),"更新成功",copy);
            }
            return CallResult.fail(BusinessCodeEnum.CHECK_BIZ_NO_RESULT.getCode(),"更新失败");

        }catch (Exception e){
            e.printStackTrace();
            return CallResult.fail(BusinessCodeEnum.DEFAULT_SYS_ERROR.getCode(),"系统错误");
        }
    }
}
