package com.campus.model;

import com.campus.dao.pojo.Certificate;
import com.campus.data.Cnst;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by likang on 2018/8/6.
 */
public class TrainerModel {

    /** */
    private Integer id;

    /*头像**/
    private String image;


    /** 姓名*/
    private String name;

    /** 身份证号*/
    private String idCard;

    /** 手机号*/
    private String phone;

    /** 工作单位*/
    private String workUnit;

    /** 性别[1:男，2:女]*/
    private Byte gender;

    /** 教师证号*/
    private String teacherCertificateNum;

    /** 邮箱*/
    private String mailbox;

    /** 毕业院校*/
    private String graduateSchool;

    /** 工作年限*/
    private Byte jobYear;

    /** 每周教学学时*/
    private Byte weekHour;

    /** 是否训练[1:否，2:是]*/
    private Byte isTrain;
    private String isTrainStr;

    /** 训练状态[1:未参加，2:已报名，3:培训中，4:考试结束，5:获得证书]*/
    private Byte trainStatus;
    private String trainStatusStr;

    /** 所教年级id*/
    private Integer gradeId;
    private String gradeName;

    /** 所在班级*/
    private Integer trainClassId;

    /** 是否注册[1:未注册，2:已注册]*/
    private Byte isRegister;
    private String isRegisterStr;
    /***证书ID**/
    private Integer certificateId;
    /***证书信息**/
    private Certificate certificate;
    /***获取证书时间**/
    private Long certificateGainTime;

    public Integer getId() {
        return id;
    }


    public String getImage() {
        if (StringUtils.isNotEmpty(image)){
            return image;
        }
        return Cnst.DefaultTrainerImg;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit == null ? null : workUnit.trim();
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public String getTeacherCertificateNum() {
        return teacherCertificateNum;
    }

    public void setTeacherCertificateNum(String teacherCertificateNum) {
        this.teacherCertificateNum = teacherCertificateNum == null ? null : teacherCertificateNum.trim();
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox == null ? null : mailbox.trim();
    }

    public String getGraduateSchool() {
        return graduateSchool;
    }

    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool == null ? null : graduateSchool.trim();
    }

    public Byte getJobYear() {
        return jobYear;
    }

    public void setJobYear(Byte jobYear) {
        this.jobYear = jobYear;
    }

    public Byte getWeekHour() {
        return weekHour;
    }

    public void setWeekHour(Byte weekHour) {
        this.weekHour = weekHour;
    }

    public Byte getIsTrain() {
        return isTrain;
    }

    public void setIsTrain(Byte isTrain) {
        this.isTrain = isTrain;
    }

    public Byte getTrainStatus() {
        return trainStatus;
    }

    public void setTrainStatus(Byte trainStatus) {
        this.trainStatus = trainStatus;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getTrainClassId() {
        return trainClassId;
    }

    public void setTrainClassId(Integer trainClassId) {
        this.trainClassId = trainClassId;
    }

    public Byte getIsRegister() {
        return isRegister;
    }

    public void setIsRegister(Byte isRegister) {
        this.isRegister = isRegister;
    }

    public String getIsTrainStr() {
        if(this.isTrain == 2){
            isTrainStr = "是";
        }else{
            isTrainStr = "否";
        }
        return isTrainStr;
    }

    public void setIsTrainStr(String isTrainStr) {
        this.isTrainStr = isTrainStr;
    }

    public String getTrainStatusStr() {
        if(this.trainStatus == 2){
            trainStatusStr = "已报名";
        }else if(this.trainStatus == 3){
            trainStatusStr = "培训中";
        }else if(this.trainStatus == 4){
            trainStatusStr = "考试结束";
        }else if(this.trainStatus == 5){
            trainStatusStr = "获得证书";
        }else{
            trainStatusStr = "未报名";
        }
        return trainStatusStr;
    }

    public void setTrainStatusStr(String trainStatusStr) {
        this.trainStatusStr = trainStatusStr;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getIsRegisterStr() {
        if(this.isRegister == 2){
            isRegisterStr = "已注册";
        }else{
            isRegisterStr = "未注册";
        }
        return isRegisterStr;
    }

    public void setIsRegisterStr(String isRegisterStr) {
        this.isRegisterStr = isRegisterStr;
    }

    public Integer getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Integer certificateId) {
        this.certificateId = certificateId;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public Long getCertificateGainTime() {
        return certificateGainTime;
    }

    public void setCertificateGainTime(Long certificateGainTime) {
        this.certificateGainTime = certificateGainTime;
    }
}
