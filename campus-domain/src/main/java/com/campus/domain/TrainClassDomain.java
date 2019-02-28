package com.campus.domain;

import cn.com.hisee.common.model.BusinessCodeEnum;
import cn.com.hisee.common.model.CallResult;
import cn.com.hisee.common.utils.TimeUtil;
import com.campus.dao.pojo.Course;
import com.campus.dao.pojo.TeachPlan;
import com.campus.dao.pojo.TrainClass;
import com.campus.dao.pojo.Trainer;
import com.campus.domain.respository.TrainClassDomainRepository;
import com.campus.domain.utils.ParamCheckUtils;
import com.campus.model.TrainClassModel;
import com.campus.model.param.TrainClassParam;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.cglib.beans.BeanCopier;


import java.util.*;

public class TrainClassDomain {
    private static final BeanCopier p2m = BeanCopier.create(TrainClass.class, TrainClassModel.class, false);
    private TrainClassDomainRepository trainerClassDomainRepository;
    private TrainClassParam trainClassParam;

    /**构造器*/
    public TrainClassDomain(TrainClassDomainRepository trainerClassDomainRepository, TrainClassParam trainClassParam){
        this.trainerClassDomainRepository = trainerClassDomainRepository;
        this.trainClassParam  = trainClassParam;
    }

    /**接口参数校验**/
    public CallResult checkClassIdParam(){
        Integer classId = trainClassParam.getClassId();
        if (ParamCheckUtils.isBlank(classId)){
            return CallResult.fail(BusinessCodeEnum.CHECK_PARAM_NO_RESULT);
        }else {
            return CallResult.success();
        }
    }
    /**copy 方法的实现***/
    public TrainClassModel copy(TrainClass trainClass){
        if (trainClass == null)  return null;
        TrainClassModel trainClassModel = new TrainClassModel();
        p2m.copy(trainClass,trainClassModel,null);
        trainClassModel.setClassNum(trainClass.getpNum());
        trainClassModel.setExamTime(trainClass.getExamTime()+"");

        if (trainClass.getTrainerId() > 0 ){
            String leaderName = getLeaderName(trainClass.getTrainerId());
            trainClassModel.setLeaderName(leaderName);
        }
        return  trainClassModel;
    }
    public CallResult checkParam(){
        String name = trainClassParam.getName();
        Long startTime = trainClassParam.getStartTime();
        Long endTime = trainClassParam.getEndTime();
        String masterName = trainClassParam.getMasterName();
        if (ParamCheckUtils.isBlank(name)||ParamCheckUtils.isBlank(startTime)||ParamCheckUtils.isBlank(endTime)||ParamCheckUtils.isBlank(masterName)){
            return CallResult.fail(BusinessCodeEnum.CHECK_PARAM_NO_RESULT);
        }else {
            return CallResult.success();
        }
    }

    public CallResult getTrainClass(){
        TrainClass oldClass = this.trainerClassDomainRepository.findTrainerClassById(trainClassParam.getClassId());
        if(oldClass == null){
            return CallResult.fail("该班级不存在！");
        }
        return CallResult.success(oldClass);
    }

    public CallResult getTrainClassList(){
        List<TrainClass> tclassList = null;
        List<TrainClassModel> tclassModelList = new ArrayList<TrainClassModel>();
        Map<String,Object> params = new HashMap<>();
        if(trainClassParam.getStatus() != null){
            params.put("status",trainClassParam.getStatus());
        }
        if(trainClassParam.getStartTime() != null){
            params.put("startTime",trainClassParam.getStartTime());
        }
        if(trainClassParam.getEndTime() != null){
            params.put("endTime",trainClassParam.getEndTime());
        }
        if(trainClassParam.isEnroll()){
            params.put("enroll",trainClassParam.isEnroll());
        }
        tclassList = this.trainerClassDomainRepository.findTrainerList(params);
        if(tclassList != null){
            for(TrainClass trainClass:tclassList){
                TrainClassModel trainClassModel = new TrainClassModel();
                trainClassModel.setId(trainClass.getId());
                trainClassModel.setName(trainClass.getName());
                trainClassModel.setClassNum(trainClass.getpNum());
                String statusStr = "未开始";
                if(trainClass.getStatus() == 1){
                    statusStr = "报名中";
                }else if(trainClass.getStatus() == 2){
                    statusStr = "培训中";
                }else if(trainClass.getStatus() == 3){
                    statusStr = "考试结束";
                }
                trainClassModel.setStatusStr(statusStr);
                String trainTime = "";
                if(trainClass.getStartTime() != null && trainClass.getEndTime() != null){
                    String start = TimeUtil.getyyyyMMdd1(trainClass.getStartTime()).replaceAll("-",".");
                    String end = TimeUtil.getyyyyMMdd1(trainClass.getEndTime()).replaceAll("-",".");
                    trainTime = start+"~"+end;
                }
                trainClassModel.setTrainTime(trainTime);
                if(trainClass.getExamTime() != null){
                    String exam = TimeUtil.getyyyyMMdd1(trainClass.getExamTime()).replaceAll("-",".");
                    trainClassModel.setExamTime(exam);
                }
                tclassModelList.add(trainClassModel);
            }
        }
        return CallResult.success(tclassModelList);
    }

    public CallResult addTrainClass(){
        TrainClass oldTrainClass = this.trainerClassDomainRepository.findTrainClassByNameAndMaster(trainClassParam.getName(),trainClassParam.getMasterName());
        if(oldTrainClass != null){
            return CallResult.fail("班级已存在！");
        }
        TrainClass trainClass = new TrainClass();
        if(!ParamCheckUtils.isBlank(trainClassParam.getName())){
            trainClass.setName(trainClassParam.getName());
        }
        trainClass.setStartTime(trainClassParam.getStartTime());
        trainClass.setEndTime(trainClassParam.getEndTime());
        trainClass.setMasterName(trainClassParam.getMasterName());
        if(!ParamCheckUtils.isBlank(trainClassParam.getpNum())){
            trainClass.setpNum(trainClassParam.getpNum());
        }
        if(!ParamCheckUtils.isBlank(trainClassParam.getTrainerId())){
            trainClass.setTrainerId(trainClassParam.getTrainerId());
        }
        if(!ParamCheckUtils.isBlank(trainClassParam.getExamTime())){
            trainClass.setExamTime(trainClassParam.getExamTime());
        }
        if(!ParamCheckUtils.isBlank(trainClassParam.getIntroduction())){
            trainClass.setIntroduction(trainClassParam.getIntroduction());
        }
        if(!ParamCheckUtils.isBlank(trainClassParam.getStatus())){
            trainClass.setStatus(trainClassParam.getStatus());
        }
        Integer classId = this.trainerClassDomainRepository.addTrainClass(trainClass);
        if(classId < 1){
            return CallResult.fail("添加失败！");
        }
        TrainClass nclss = this.trainerClassDomainRepository.findTrainClassByNameAndMaster(trainClassParam.getName(),trainClassParam.getMasterName());
        List courseList = this.trainClassParam.getCourseList();
        if(courseList != null){
            for(Object ob:courseList){
                JSONObject jsonObject = null;
                Course c = new Course();
                try {
                    jsonObject = new JSONObject(ob.toString());
                    c.setTime(jsonObject.getLong("cdate"));
                    c.setContent(jsonObject.getString("content").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                c.setTrainClassId(nclss.getId());
                Integer cret = this.trainerClassDomainRepository.addCourse(c);
                if(cret < 1){
                    return CallResult.fail("课程添加失败！");
                }
            }

        }
        return CallResult.success("添加成功！");
    }
    public CallResult editTrainClass(){
        TrainClass oldTrainClass = this.trainerClassDomainRepository.findTrainerClassById(trainClassParam.getClassId());
        if(oldTrainClass == null){
            return CallResult.fail("该班级不存在！");
        }
        if(!ParamCheckUtils.isBlank(trainClassParam.getName())){
            oldTrainClass.setName(trainClassParam.getName());
        }
        if(!ParamCheckUtils.isBlank(trainClassParam.getStartTime())){
            oldTrainClass.setStartTime(trainClassParam.getStartTime());
        }
        if(!ParamCheckUtils.isBlank(trainClassParam.getEndTime())){
            oldTrainClass.setEndTime(trainClassParam.getEndTime());
        }
        if(!ParamCheckUtils.isBlank(trainClassParam.getpNum())){
            oldTrainClass.setpNum(trainClassParam.getpNum());
        }
        if(!ParamCheckUtils.isBlank(trainClassParam.getMasterName())){
            oldTrainClass.setMasterName(trainClassParam.getMasterName());
        }
        if(!ParamCheckUtils.isBlank(trainClassParam.getTrainerId())){
            oldTrainClass.setTrainerId(trainClassParam.getTrainerId());
        }
        if(!ParamCheckUtils.isBlank(trainClassParam.getExamTime())){
            oldTrainClass.setExamTime(trainClassParam.getExamTime());
        }
        if(!ParamCheckUtils.isBlank(trainClassParam.getIntroduction())){
            oldTrainClass.setIntroduction(trainClassParam.getIntroduction());
        }
        if(!ParamCheckUtils.isBlank(trainClassParam.getStatus())){
            oldTrainClass.setStatus(trainClassParam.getStatus());
        }
        Integer classId = this.trainerClassDomainRepository.editTrainClass(oldTrainClass);
        if(classId<1){
            return CallResult.fail("班级编辑失败！");
        }
        ////
        List courseList = this.trainClassParam.getCourseList();
        if(courseList != null){
            Map<String,Object> pmap = new HashMap<String,Object>();
            List<Long> times = new ArrayList<>();
            for(Object ob:courseList){
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(ob.toString());
                    Long time = jsonObject.getLong("cdate");
                    times.add(time);
                    Course oldCourse = this.trainerClassDomainRepository.findCourseByClassIdAndTime(oldTrainClass.getId(),time);
                    String content = jsonObject.getString("content").toString();
                    if(oldCourse != null){
                        oldCourse.setContent(content);
                        Integer ret = this.trainerClassDomainRepository.editClassCourse(oldCourse);
                        if(ret < 1){
                            return CallResult.fail("课程更新失败！");
                        }
                    }else{
                        Course course = new Course();
                        course.setTime(time);
                        course.setContent(content);
                        course.setTrainClassId(oldTrainClass.getId());
                        Integer cret = this.trainerClassDomainRepository.addCourse(course);
                        if(cret < 1){
                            return CallResult.fail("课程更新失败！");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            pmap.put("classId",oldTrainClass.getId());
            pmap.put("times",times);
            this.trainerClassDomainRepository.deleteCourseByParam(pmap);
        }
        return CallResult.success("编辑成功！");
    }

    public CallResult deleteTrainClass(){
        Integer ret = this.trainerClassDomainRepository.deleteTrainClass(trainClassParam.getClassId());
        if(ret < 0){
            return CallResult.fail("删除失败！");
        }
        Integer cret = this.trainerClassDomainRepository.deleteCourseByClassId(trainClassParam.getClassId());
        if(cret < 0){
            return CallResult.fail("删除课程失败！");
        }
        return CallResult.success("删除成功！");
    }

    public CallResult deleteClassTrainer(){
        if(trainClassParam.getTrainerIds() == null || trainClassParam.getTrainerIds().size()<1){
            return CallResult.fail("请选择要删除的选项！");
        }
        for(Integer trainerId:trainClassParam.getTrainerIds()){
            Trainer trainer = this.trainerClassDomainRepository.findTrainerByTrainerId(trainerId);
            if(trainer != null && trainer.getTrainClassId()-trainClassParam.getClassId() == 0){
                trainer.setTrainClassId(null);
                Integer ret = this.trainerClassDomainRepository.editTrainer(trainer);
                if(ret < 1){
                    return CallResult.fail("删除班级学员失败！");
                }
            }
        }
        return CallResult.success("删除成功！");
    }

    /**
     * @Description:通过trainerId查询培训班级信息
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 16:04 2018-09-04
     */
    public TrainClassModel findTrainClassByTrainerId(Integer trainerId) {
        TrainClass trainClass = trainerClassDomainRepository.findTrainClassByTrainerId(trainerId);
        return copy(trainClass);
    }
    private String getLeaderName(Integer trainerId){
        Trainer trainerByTrainerId = trainerClassDomainRepository.findTrainerByTrainerId(trainerId);
        return trainerByTrainerId.getName();
    }
}
