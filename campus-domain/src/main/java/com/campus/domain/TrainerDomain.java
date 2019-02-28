package com.campus.domain;

import cn.com.hisee.common.model.BusinessCodeEnum;
import cn.com.hisee.common.model.CallResult;
import cn.com.hisee.common.utils.MD5FileUtil;
import com.alibaba.druid.util.StringUtils;
import com.campus.dao.pojo.Certificate;
import com.campus.dao.pojo.Course;
import com.campus.dao.pojo.Grade;
import com.campus.dao.pojo.Trainer;
import com.campus.data.Cnst;
import com.campus.domain.respository.TrainerDomainRepository;
import com.campus.domain.utils.ParamCheckUtils;
import com.campus.model.*;
import com.campus.model.param.TrainerParam;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.cglib.beans.BeanCopier;

import javax.swing.*;
import java.util.*;

public class TrainerDomain {
    private static final BeanCopier p2mR = BeanCopier.create(TrainerParam.class, Trainer.class, false);
    private static final BeanCopier p2m = BeanCopier.create(Trainer.class, TrainerModel.class, false);

    private TrainerDomainRepository trainerDomainRepository;
    private TrainerParam trainerParam;
    private Trainer trainer;

    /**构造器*/
    public TrainerDomain(TrainerDomainRepository trainerDomainRepository, TrainerParam trainerParam){
        this.trainerDomainRepository = trainerDomainRepository;
        this.trainerParam  = trainerParam;
    }
    public TrainerDomain(TrainerDomainRepository trainerDomainRepository, Trainer trainer){
        this.trainerDomainRepository = trainerDomainRepository;
        this.trainer  = trainer;
    }

    /**copy 实体 到 model*/
    private TrainerModel copy(Trainer trainer) {
        if (trainer ==  null){
            return null;
        }
        TrainerModel model = new TrainerModel();
        p2m.copy(trainer,model,null);
        return  model;
    }
    /**copy 参数 到 实体*/
    private Trainer copyR(TrainerParam param) {
        if (param ==  null){
            return null;
        }

        Trainer trainer = new Trainer();
        trainer.setName(param.getTrainerName());
        p2mR.copy(param,trainer,null);
        return  trainer;
    }

    /**接口参数校验**/
    public CallResult checkParam() {
        String trainerName = trainerParam.getTrainerName();
        String idCard = trainerParam.getIdCard();
        String phone = trainerParam.getPhone();
        String teacherVertificateNum = trainerParam.getTeacherCertificateNum();
        if (ParamCheckUtils.isBlank(trainerName) || ParamCheckUtils.isBlank(idCard) || ParamCheckUtils.isBlank(phone) || ParamCheckUtils.isBlank(teacherVertificateNum)){
            return CallResult.fail(BusinessCodeEnum.CHECK_PARAM_NO_RESULT);
        }else {
            return CallResult.success();
        }
    }
    public CallResult checkCTrainerParam() {
        Integer classId = trainerParam.getTrainClassId();
        String idCard = trainerParam.getIdCard();
        String name = trainerParam.getTrainerName();
        if (ParamCheckUtils.isBlank(classId) || ParamCheckUtils.isBlank(idCard) || ParamCheckUtils.isBlank(name)){
            return CallResult.fail(BusinessCodeEnum.CHECK_PARAM_NO_RESULT);
        }else {
            return CallResult.success();
        }
    }

    /**登录参数校验@wangzhiqiang**/
    public CallResult checkLoginParam() {
        String idCard = trainerParam.getIdCard();
        String password = trainerParam.getPassword();
        if (ParamCheckUtils.isBlank(idCard) || ParamCheckUtils.isBlank(password)){
            return CallResult.fail(BusinessCodeEnum.CHECK_PARAM_NO_RESULT.getCode(),"参数校验失败");
        }
        return  CallResult.success();
    }
    /***校验手机号非空*/
    public CallResult checkPhoneParam() {
        System.out.println("参数校验");
       String phone = trainerParam.getPhone();
       if (ParamCheckUtils.isBlank(phone)){
           return CallResult.fail(BusinessCodeEnum.CHECK_PARAM_NO_RESULT.getCode(),"参数校验失败");
       }
        return  CallResult.success();
    }
    /**注册参数校验register**/
    public CallResult checkRGParam() {
        if (ParamCheckUtils.isBlank(trainerParam.getTrainerName())//姓名
            || ParamCheckUtils.isBlank(trainerParam.getGender())//性别
            ||ParamCheckUtils.isBlank(trainerParam.getIdCard())//身份证
            ||ParamCheckUtils.isBlank(trainerParam.getTeacherCertificateNum())||trainerParam.getTeacherCertificateNum().length() != 17//教师证件号
            ||ParamCheckUtils.isBlank(trainerParam.getGraduateSchool())//毕业学校
            ||ParamCheckUtils.isBlank(trainerParam.getWorkUnit())//工作单位
            ||ParamCheckUtils.isBlank(trainerParam.getJobYear())//从业时间
            ||ParamCheckUtils.isBlank(trainerParam.getGradeId())//所教年级
            ||ParamCheckUtils.isBlank(trainerParam.getWeekHour())//M每周课时
                ){
            return  CallResult.fail(BusinessCodeEnum.CHECK_PARAM_NO_RESULT.getCode(),"参数校验失败");
        }
        return  CallResult.success();
    }
    /**修改密码参数校验editPassword**/
    public CallResult checkEPParam() {
        String newPassword = trainerParam.getNewPassword();
        String rNewPassword = trainerParam.getrNewPassword();
        ParamCheckUtils.isBlank(trainerParam.getrNewPassword());
        if (ParamCheckUtils.isBlank(trainerParam.getIdCard())
                || ParamCheckUtils.isBlank(trainerParam.getPhone())
                || ParamCheckUtils.isBlank(trainerParam.getvCode())
                || ParamCheckUtils.isBlank(newPassword)
                || ParamCheckUtils.isBlank(rNewPassword)
                ) {
            return CallResult.fail(BusinessCodeEnum.CHECK_PARAM_NO_RESULT.getCode(), "参数校验失败");
        }
        if (!newPassword.equals(rNewPassword)) {
            return CallResult.fail(BusinessCodeEnum.CHECK_PARAM_NO_RESULT.getCode(), "密码输入不一致");
        }
        return CallResult.success();
    }
    /**delete接口参数校验**/
    public CallResult checkTrainerIdParam(){
        Integer trainerId = trainerParam.getTrainerId();
        if (ParamCheckUtils.isBlank(trainerId)){
            return CallResult.fail(BusinessCodeEnum.CHECK_PARAM_NO_RESULT);
        }else {
            return CallResult.success();
        }
    }
   /**修改密码参数校验*/
    public CallResult checkEditPasswordParam() {
        String getrNewPassword = trainerParam.getrNewPassword();//重复输入的密码
        String password = trainerParam.getPassword();//密码
        if (ParamCheckUtils.isBlank(getrNewPassword) || ParamCheckUtils.isBlank(password)){
            return CallResult.fail(BusinessCodeEnum.CHECK_PARAM_NO_RESULT.getCode(),"参数错误");
        }else {
            return CallResult.success();
        }
    }
    public CallResult checkClassIdParam(){
        Integer classId = trainerParam.getTrainClassId();
        if (ParamCheckUtils.isBlank(classId)){
            return CallResult.fail(BusinessCodeEnum.CHECK_PARAM_NO_RESULT);
        }else {
            return CallResult.success();
        }
    }

    public CallResult getTrainerList(){
        List<Trainer> trainerList;
        Map<String,Object> params = new HashMap<String,Object>();
        if(!ParamCheckUtils.isBlank(trainerParam.getTrainClassId())){
            params.put("classId",trainerParam.getTrainClassId());
        }
        if(!ParamCheckUtils.isBlank(trainerParam.getKeyword())){
            params.put("keyword",trainerParam.getKeyword());
        }
        if(!ParamCheckUtils.isBlank(trainerParam.getGradeId())){
            params.put("gradeId",trainerParam.getGradeId());
        }
        if(!ParamCheckUtils.isBlank(trainerParam.getIsTrain())){
            params.put("isTrain",trainerParam.getIsTrain());
        }
        if(trainerParam.getTrainStatus() != null){
            params.put("trainStatus",trainerParam.getTrainStatus());
        }
        PageHelper.startPage(trainerParam.getPage(),trainerParam.getPagesize());
        trainerList = this.trainerDomainRepository.findTrainerList(params);
        List<TrainerModel> trainerModelList = new ArrayList<TrainerModel>();
        for(Trainer trainer : trainerList){
            TrainerModel trainerModel = new TrainerModel();
            trainerModel.setId(trainer.getId());
            trainerModel.setIdCard(trainer.getIdCard());
            trainerModel.setName(trainer.getName());
            trainerModel.setPhone(trainer.getPhone());
            trainerModel.setGradeId(trainer.getGradeId());
            trainerModel.setTrainClassId(trainer.getTrainClassId());
            Grade grade = this.trainerDomainRepository.findGradeByGradeId(trainer.getGradeId());
            if(grade != null){
                trainerModel.setGradeName(grade.getName());
            }
            trainerModel.setWorkUnit(trainer.getWorkUnit());
            trainerModel.setIsTrain(trainer.getIsTrain());
            trainerModel.setTrainStatus(trainer.getTrainStatus());
            trainerModel.setIsRegister(trainer.getIsRegister());
            trainerModelList.add(trainerModel);
        }
        PageInfo<TrainerModel> pageInfo = new PageInfo<TrainerModel>(trainerModelList);
        return CallResult.success(pageInfo);
    }

    public CallResult addTrainer(){
        Trainer oldTrainer = this.trainerDomainRepository.findTrainerByIdCard(trainerParam.getIdCard());
        if(oldTrainer != null){
            return CallResult.fail("此用户已存在！");
        }
        Trainer trainer = new Trainer();
        trainer.setIdCard(trainerParam.getIdCard());
        trainer.setPhone(trainerParam.getPhone());
        trainer.setTeacherCertificateNum(trainerParam.getTeacherCertificateNum());
        if(!ParamCheckUtils.isBlank(trainerParam.getTrainerName())){
            trainer.setName(trainerParam.getTrainerName());
        }
        if(!ParamCheckUtils.isBlank(trainerParam.getWorkUnit())){
            trainer.setWorkUnit(trainerParam.getWorkUnit());
        }
        if(trainerParam.getGender() != null){
            trainer.setGender(trainerParam.getGender());
        }
        if(!ParamCheckUtils.isBlank(trainerParam.getMailbox())){
            trainer.setMailbox(trainerParam.getMailbox());
        }
        if(!ParamCheckUtils.isBlank(trainerParam.getGraduateSchool())){
            trainer.setGraduateSchool(trainerParam.getGraduateSchool());
        }
        if(trainerParam.getWeekHour() != null){
            trainer.setWeekHour(trainerParam.getWeekHour());
        }
        if(trainerParam.getGradeId() != null){
            trainer.setGradeId(trainerParam.getGradeId());
        }
        if(trainerParam.getJobYear() != null){
            trainer.setJobYear(trainerParam.getJobYear());
        }
        String password = "123456";
        if(!ParamCheckUtils.isBlank(trainerParam.getPassword())){
            password = trainerParam.getPassword();
        }
        String md5String = MD5FileUtil.getMD5String(password);
        trainer.setPassword(md5String);
        Integer ret = this.trainerDomainRepository.addTrainer(trainer);
        if(ret < 1){
            return CallResult.fail("添加失败！");
        }
        return CallResult.success("添加成功！");
    }


    /**
     * @Description: 登录
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 13:52 2018-08-08
     */
    public CallResult login() {
        try{
            String idCard = trainerParam.getIdCard();
            String password = trainerParam.getPassword();
            Trainer trainerByIdCard = trainerDomainRepository.findTrainerByIdCard(idCard);
            if (trainerByIdCard == null){
                return CallResult.fail(BusinessCodeEnum.CHECK_BIZ_NO_RESULT.getCode(),"用户不存在，请注册");
            }
            //密码加密
            if (!MD5FileUtil.checkPassword(password,trainerByIdCard.getPassword())){
                return CallResult.fail(BusinessCodeEnum.CHECK_BIZ_NO_RESULT.getCode(),"密码错误");
            }
            return new CallResult(BusinessCodeEnum.DEFAULT_SUCCESS.getCode(),"登录成功","");
        }catch (Exception e){
            return CallResult.fail(BusinessCodeEnum.DEFAULT_SYS_ERROR.getCode(),"登录异常请重试");
        }
    }

    /**
     * @Description:注册
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 13:53 2018-08-08
     */
    public CallResult register() {
        try {
            HashMap<String, Object> resultMap = new HashMap<>();
            /**判断验证码输入是否正确**/
            String getvCode = trainerParam.getvCode();
            if (StringUtils.isEmpty(getvCode) || !trainerDomainRepository.verifyCode(trainerParam.getPhone(),Cnst.SmsCodeType.register,getvCode).isSuccess()){
                return     CallResult.fail(BusinessCodeEnum.CHECK_BIZ_NO_RESULT.getCode(),"验证码输入不正确");
            }

            /*判断两次输入密码是否一致*/
            String getPwd = trainerParam.getPassword();
            String getrNewPassword = trainerParam.getrNewPassword();
            if (!getPwd.equals(getrNewPassword)){
                return   CallResult.fail(BusinessCodeEnum.CHECK_BIZ_NO_RESULT.getCode(),"输入密码不一致，请重新输入");
            }

            Trainer trainerByIdCard = trainerDomainRepository.findTrainerByIdCard(trainerParam.getIdCard());
            if (trainerByIdCard != null){
                Integer id = trainerByIdCard.getId();
                resultMap.put("id",id);
                return CallResult.fail(BusinessCodeEnum.CHECK_BIZ_NO_RESULT.getCode(),"用户已存在，请直接登录",resultMap);
            }
            //密码加密
            String password = trainerParam.getPassword();
            String md5String = MD5FileUtil.getMD5String(password);
            trainerParam.setPassword(md5String);
            Trainer trainer = copyR(trainerParam);
            Integer integer = trainerDomainRepository.addTrainer(trainer);
            if (integer != null && integer > 0){
                resultMap.put("id",integer);
                return new CallResult(BusinessCodeEnum.DEFAULT_SUCCESS.getCode(),"注册成功",resultMap);
            }
            return CallResult.fail(BusinessCodeEnum.CHECK_BIZ_NO_RESULT.getCode(),"注册失败请重试");
        }catch (Exception e){
            e.printStackTrace();
            return CallResult.fail(BusinessCodeEnum.DEFAULT_SYS_ERROR.getCode(),"注册异常请重试");
        }
    }
    /**
     * @Description:注册时发送手机验证码
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 9:41 2018-08-27
     */
    public CallResult sendRegisterCode() {
        String phone = trainerParam.getPhone();
        CallResult callResult = trainerDomainRepository.sendCode(phone, Cnst.SmsCodeType.register);
        return callResult;
    }
    /**
     * @Description:修改密码
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 13:53 2018-08-08
     */
    public CallResult editPassword() {
        String idCard = trainerParam.getIdCard();
        Trainer trainerByIdCard = trainerDomainRepository.findTrainerByIdCard(idCard);
        String getrNewPassword = trainerParam.getrNewPassword();
        String password = trainerParam.getPassword();
        if (!getrNewPassword.equals(password)){
            return CallResult.fail(BusinessCodeEnum.CHECK_BIZ_NO_RESULT.getCode(),"重复密码与密码不一致");
        }
        //验证验证码
        CallResult callResult = trainerDomainRepository.verifyCode(trainerParam.getPhone(),Cnst.SmsCodeType.editPassword, trainerParam.getvCode());
        if (!callResult.isSuccess()){
            return callResult;
        }
        //修改操作
        trainerByIdCard.setPassword(MD5FileUtil.getMD5String(trainerParam.getPassword()));
        Integer i = trainerDomainRepository.editTrainer(trainerByIdCard);
        if (i <= 0 ){
              return CallResult.fail(BusinessCodeEnum.CHECK_BIZ_NO_RESULT.getCode(),"更新失败请重试");
        }
        return new CallResult(BusinessCodeEnum.DEFAULT_SUCCESS.getCode(),"修改成功",null);
    }
    public CallResult editTrainer(){
        Trainer oldTrainer = this.trainerDomainRepository.findTrainerById(trainerParam.getTrainerId());
        if(oldTrainer == null){
            return CallResult.fail("用户不存在！");
        }
        if(!ParamCheckUtils.isBlank(trainerParam.getTrainerName())){
            oldTrainer.setName(trainerParam.getTrainerName());
        }
        if(!ParamCheckUtils.isBlank(trainerParam.getMailbox())){
            oldTrainer.setMailbox(trainerParam.getMailbox());
        }
        if(!ParamCheckUtils.isBlank(trainerParam.getIdCard())){
            oldTrainer.setIdCard(trainerParam.getIdCard());
        }
        if(!ParamCheckUtils.isBlank(trainerParam.getGraduateSchool())){
            oldTrainer.setGraduateSchool(trainerParam.getGraduateSchool());
        }
        if(!ParamCheckUtils.isBlank(trainerParam.getWorkUnit())){
            oldTrainer.setWorkUnit(trainerParam.getWorkUnit());
        }
        if(trainerParam.getJobYear() != null){
            oldTrainer.setJobYear(trainerParam.getJobYear());
        }
        if(trainerParam.getGender() != null){
            oldTrainer.setGender(trainerParam.getGender());
        }
        if(trainerParam.getGradeId() != null){
            oldTrainer.setGradeId(trainerParam.getGradeId());
        }
        if(!ParamCheckUtils.isBlank(trainerParam.getTeacherCertificateNum())){
            oldTrainer.setTeacherCertificateNum(trainerParam.getTeacherCertificateNum());
        }
        if(trainerParam.getWeekHour() != null){
            oldTrainer.setWeekHour(trainerParam.getWeekHour());
        }
        if(!ParamCheckUtils.isBlank(trainerParam.getPhone())){
            oldTrainer.setPhone(trainerParam.getPhone());
        }
        if(trainerParam.getTrainStatus() != null){
            oldTrainer.setTrainStatus(trainerParam.getTrainStatus());
            if(trainerParam.getTrainStatus() > 1){
                oldTrainer.setIsTrain(Trainer.JOIN_TRAIN);
            }
        }
        if(!ParamCheckUtils.isBlank(trainerParam.getPassword()) && !trainerParam.getPassword().equals("000000")){
            String password = trainerParam.getPassword();
            String md5String = MD5FileUtil.getMD5String(password);
            oldTrainer.setPassword(md5String);
        }
        if(!ParamCheckUtils.isBlank(trainerParam.getTrainClassId())){
            oldTrainer.setTrainClassId(trainerParam.getTrainClassId());
            if(oldTrainer.getTrainStatus() < 2){
                oldTrainer.setTrainStatus(Trainer.ENROLL);
            }
        }
        if(!ParamCheckUtils.isBlank(trainerParam.getCertificateId())){
            Long certificateGainTime = new Date().getTime();
            if(oldTrainer.getCertificateGainTime() == null || oldTrainer.getCertificateId() == null || oldTrainer.getCertificateId() != trainerParam.getCertificateId()){
                oldTrainer.setCertificateGainTime(certificateGainTime);
            }
            oldTrainer.setCertificateId(trainerParam.getCertificateId());
        }
        Integer ret = this.trainerDomainRepository.editTrainer(oldTrainer);
        if(ret < 1){
            return CallResult.fail("编辑失败！");
        }
        return CallResult.success("编辑成功！");
    }

    public CallResult deleteTrainer(){
        Integer ret = this.trainerDomainRepository.deleteTrainer(trainerParam.getTrainerId());
        if(ret < 0){
            return CallResult.fail("删除失败！");
        }
        return CallResult.success("删除成功！");
    }

    public CallResult getTrainer(){
        //查询基本信息
        Trainer trainerById = trainerDomainRepository.findTrainerById(trainerParam.getTrainerId());
        if (trainerById == null){
            return CallResult.fail(BusinessCodeEnum.CHECK_BIZ_NO_RESULT.getCode(),"数据不存在");
        }
        Integer certificateId = trainerById.getCertificateId();
        TrainerModel copy = copy(trainerById);
        //查询证书信息
        if (certificateId != null && certificateId > 0){
            Certificate certificate =  trainerDomainRepository.findCertificateById(certificateId);
            copy.setCertificate(certificate);
        }
        return new CallResult(BusinessCodeEnum.DEFAULT_SUCCESS.getCode(),"查询成功",copy);
    }

    public CallResult getClassTrainerList(){
        List<Trainer> trainerList = this.trainerDomainRepository.findTrainerListByClassId(trainerParam.getTrainClassId());
        return CallResult.success(trainerList);
    }
    public Trainer findTrainerByTrainerId(){
        return this.trainerDomainRepository.findTrainerById(trainerParam.getTrainerId());
    }
    public Integer editTrainerClassId(){
        return this.trainerDomainRepository.editTrainer(trainer);
    }

    public CallResult addClassTrainer(){
        Trainer trainer = this.trainerDomainRepository.findTrainerByIdCard(trainerParam.getIdCard());
        if(trainer == null){
            return CallResult.fail("系统暂无此球员！");
        }
        trainer.setTrainClassId(trainerParam.getTrainClassId());
        Integer ret = this.trainerDomainRepository.editTrainer(trainer);
        if(ret < 1){
            return CallResult.fail("添加学员失败！");
        }
        return CallResult.success("添加学员成功！");
    }

    /**
     * @Description:查看我的收藏
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 16:24 2018-08-24
     */
    public CallResult getMyCollection() {
        List<MyCollectionModel>  list  = trainerDomainRepository.getMyCollection(trainerParam.getTrainerId());
        return  new CallResult(BusinessCodeEnum.DEFAULT_SUCCESS.getCode(),"查询成功",list);
    }

    /**
     * @Description:更换手机号
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 17:23 2018-08-24
     */
    public CallResult replacePhone() {
        String phone = trainerParam.getPhone();
        String getvCode = trainerParam.getvCode();
        CallResult callResult = trainerDomainRepository.verifyCode(phone, Cnst.SmsCodeType.changePhone, getvCode);
        if (!callResult.isSuccess()){
            return callResult;
        }
        Integer trainerId = trainerParam.getTrainerId();
        Trainer trainer= trainerDomainRepository.findTrainerById(trainerId);
        trainer.setPhone(phone);
        //更新手机号
        Integer integer = trainerDomainRepository.editTrainer(trainer);
        if (integer > 0){
            return new CallResult(BusinessCodeEnum.DEFAULT_SUCCESS.getCode(),"更新手机号成功",null);
        }
        return  CallResult.fail(BusinessCodeEnum.CHECK_BIZ_NO_RESULT.getCode(),"更新手机号失败");
    }

    /**
     * @Description:更换登录密码
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 17:32 2018-08-24
     */
    public CallResult replacePassword() {
        String password = trainerParam.getPassword();
        String newPassword = trainerParam.getNewPassword();
        String rNewPassword = trainerParam.getrNewPassword();
        if (ParamCheckUtils.isBlank(password) || ParamCheckUtils.isBlank(newPassword) ||ParamCheckUtils.isBlank(rNewPassword)){
            return CallResult.fail(BusinessCodeEnum.CHECK_PARAM_NO_RESULT.getCode(),"参数错误");
        }
        if (!newPassword.equals(rNewPassword)){
            return CallResult.fail(BusinessCodeEnum.CHECK_PARAM_NO_RESULT.getCode(),"确认密码前后填写不一致");
        }
        Trainer trainerById = trainerDomainRepository.findTrainerById(trainerParam.getTrainerId());
        if (!MD5FileUtil.checkPassword(password,trainerById.getPassword())){
            return CallResult.fail(BusinessCodeEnum.CHECK_BIZ_NO_RESULT.getCode(),"密码输入错误,请重新输入");
        }

        trainerById.setPassword(MD5FileUtil.getMD5String(newPassword));
        //更新操作
        Integer integer = trainerDomainRepository.editTrainer(trainerById);
        if (integer >0 ){
            return  new CallResult(BusinessCodeEnum.DEFAULT_SUCCESS.getCode(),"更新密码成功",null);
        }
        return CallResult.fail(BusinessCodeEnum.CHECK_BIZ_NO_RESULT.getCode(),"更新失败请重试");
    }

    /**
     * @Description:忘记密码发送验证码
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 13:59 2018-08-27
     */
    public CallResult forgetPasswordSendCode() {
        System.out.println("忘记密码");
        Trainer trainerByIdCard = trainerDomainRepository.findTrainerByIdCard(trainerParam.getIdCard());
        if (trainerByIdCard == null || !trainerParam.getPhone().equals(trainerByIdCard.getPhone())){
            return CallResult.fail(BusinessCodeEnum.CHECK_BIZ_NO_RESULT.getCode(),"用户信息错误请核对");
        }
        return trainerDomainRepository.sendCode(trainerParam.getPhone(),Cnst.SmsCodeType.editPassword);
    }

    /**
     * @Description:我的培训
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 10:57 2018-09-04
     */
    public CallResult getMyCourse() {
        MyCourseModel myCourseModel = new MyCourseModel();

        Integer trainerId = trainerParam.getTrainerId();
        Trainer trainerById = trainerDomainRepository.findTrainerById(trainerId);
        //学院姓名
        myCourseModel.setTrainerName(trainerById.getName());
        //证书ID
        Integer certificateId = trainerById.getCertificateId();
        if (trainerById == null){
            return CallResult.fail(BusinessCodeEnum.CHECK_BIZ_NO_RESULT.getCode(),"暂无数据");
        }
        /**获取球员班级信息**/
        TrainClassModel trainClassModel = trainerDomainRepository.findTrainClassByTrainerId(trainerParam.getTrainerId());
        //获取课程基本信息
        if (trainClassModel == null){
            return CallResult.fail(BusinessCodeEnum.CHECK_BIZ_NO_RESULT.getCode(),"暂无数据");
        }
        myCourseModel.setTrainClassModel(trainClassModel);
        List <Course> course = trainerDomainRepository.findCourseMessage(trainClassModel.getId());
        myCourseModel.setCourses(course);
        //毕业证书
        if (certificateId != null &&  certificateId  >0){
            Certificate certificateById = trainerDomainRepository.findCertificateById(certificateId);
            myCourseModel.setCertificate(certificateById);
        }
        return new CallResult(BusinessCodeEnum.DEFAULT_SUCCESS.getCode(),"获取信息成功",myCourseModel);
    }

    /**
     * @Description:更改手机号发送验证码
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 10:04 2018-09-05
     */
    public CallResult replacePhoneSendCode() {
        CallResult callResult = trainerDomainRepository.sendCode(trainerParam.getPhone(), Cnst.SmsCodeType.changePhone);
        return callResult;
    }

    /**
     * @Description:添加收藏
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 14:46 2018-09-05
     */
    public CallResult addMyCollection() {
        Integer eventId = trainerParam.getEventId();
        if (ParamCheckUtils.isBlank(eventId) || ParamCheckUtils.isBlank(trainerParam.getEventType())){
            return CallResult.fail(BusinessCodeEnum.CHECK_PARAM_NO_RESULT.getCode(),"参数错误");
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("eventType",trainerParam.getEventType());
        map.put("eventId",eventId);
        map.put("trainerId",trainerParam.getTrainerId());
        Integer i = trainerDomainRepository.addMyCollection(map);
        if (i > 0){
            return  new CallResult(BusinessCodeEnum.DEFAULT_SUCCESS.getCode(),"添加收藏成功",i);
        }
        return CallResult.fail(BusinessCodeEnum.CHECK_BIZ_NO_RESULT.getCode(),"添加收藏失败");
    }

    /**
     * @Description: 反馈意见
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 11:10 2018-09-06
     */
    public CallResult sendAdvice() {
        try{
            Map<String, Object> map = new HashMap<>();
            map.put("trainerId",trainerParam.getTrainerId());
            map.put("pubdate",new Date().getTime());
            map.put("content",trainerParam.getOpinionContent());
            return trainerDomainRepository.sendAdvice(map);
        }catch (Exception  e){
            return  CallResult.fail(BusinessCodeEnum.DEFAULT_SYS_ERROR.getCode(),"系统错误");
        }

    }
}
