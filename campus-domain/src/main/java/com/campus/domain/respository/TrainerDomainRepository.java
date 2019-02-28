package com.campus.domain.respository;

import cn.com.hisee.common.model.CallResult;
import com.campus.dao.pojo.*;
import com.campus.domain.GradeDomain;
import com.campus.domain.TrainerDomain;
import com.campus.domain.VcodeDomain;
import com.campus.model.CertificateModel;
import com.campus.model.MyCollectionModel;
import com.campus.model.TrainClassModel;
import com.campus.model.param.GradeParam;
import com.campus.model.param.TrainerParam;
import com.campus.mybais.mapper.TrainerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by likang on 2018/8/6.
 */
@Component
public class TrainerDomainRepository {

    @Autowired
    private TrainerMapper trainerMapper;
    @Autowired
    GradeDomainRepository gradeDomainRepository;
    @Autowired
    VcodeDomainRepository vcodeDomainRepository;
    @Autowired
    CertificateDomainRepository certificateDomainRepository;
    @Autowired
    CourseDomainRepository courseDomainRepository;
    @Autowired
    TrainClassDomainRepository trainClassDomainRepository;
    @Autowired
    MyCollectionDomainRepository myCollectionDomainRepository;
    @Autowired
    OpinionDomainRepository opinionDomainRepository;

    public TrainerDomain createDomain(TrainerParam trainerParam){
        return new TrainerDomain(this,trainerParam);
    }
    public TrainerDomain createDomain(Trainer trainer){
        return new TrainerDomain(this,trainer);
    }

    public List<Trainer> findTrainerList(Map<String,Object> params){
        return trainerMapper.findTrainerList(params);
    }

    public Trainer findTrainerByIdCard(String idCard){
        return trainerMapper.findTrainByIdCard(idCard);
    }

    public Integer addTrainer(Trainer trainer){
        return trainerMapper.addTrainer(trainer);
    }

    public Grade findGradeByGradeId(Integer gradeId){
        GradeParam gradeParam = new GradeParam();
        gradeParam.setId(gradeId);
        GradeDomain gradeDomain = gradeDomainRepository.createDomain(gradeParam);
        return gradeDomain.findGradeByGradeId();
    }

    public Trainer findTrainerById(Integer trainerId){
        return trainerMapper.findTrainById(trainerId);
    }

    public Integer editTrainer(Trainer trainer){
        return trainerMapper.updateTrainer(trainer);
    }

    public Integer deleteTrainer(Integer trainerId){
        return trainerMapper.deleteTrainer(trainerId);
    }
     /**获取缓存中得验证码**/
    public String getVcode(String phone, Integer type) {
        return  vcodeDomainRepository.getVcode(phone,type);
    }
    public List<Trainer> findTrainerListByClassId(Integer classId){
        return trainerMapper.findTrainerListByClassId(classId);
    }
    /**发送验证码***/
    public CallResult sendCode(String phone, int smsCodeType) {
     return   vcodeDomainRepository.sendCode(phone,smsCodeType);
    }
   /***通过证书ID　查询证书信息**/
    public Certificate findCertificateById(Integer certificateId) {
        return certificateDomainRepository.getCertificateById(certificateId);
    }
    /***获取个人培训课程日程信息**/
    public List <Course> findCourseMessage(Integer trainClassId) {
        return courseDomainRepository.getCourseMessageByTrainClassId(trainClassId);
    }
    /**通过trainerId 查询班级信息**/
    public TrainClassModel findTrainClassByTrainerId(Integer trainerId) {
        return trainClassDomainRepository.getTrainClassByTrainerId(trainerId);
    }
    /***验证验证码是否正确**/
    public CallResult verifyCode(String phone, int type, String vCode) {
        return  vcodeDomainRepository.codeVerify(phone,type,vCode);
    }
     /***添加我的收藏**/
    public Integer addMyCollection(HashMap<String, Object> map) {
        return myCollectionDomainRepository.addMyCollection(map);
    }
   /**获取我的收藏**/
    public List<MyCollectionModel> getMyCollection(Integer trainerId) {
        return myCollectionDomainRepository.getMyCollection(trainerId);
    }
    /**添加意见反馈**/
    public CallResult sendAdvice(Map<String, Object> map) {
        return opinionDomainRepository.sendAdvice(map);
    }
}
