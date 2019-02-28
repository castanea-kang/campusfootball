package com.campus.dao.pojo;

public class Trainer {
    public static final Byte NO_ENROLL = 1;
    public static final Byte ENROLL = 2;
    public static final Byte TRAINING = 3;
    public static final Byte END_EXAM = 4;
    public static final Byte GET_CERTIFICATE = 5;

    public static final Byte JOIN_TRAIN =2;
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

    /** 训练状态[1:未参加，2:已报名，3:培训中，4:考试结束，5:获得证书]*/
    private Byte trainStatus;

    /** 所教年级id*/
    private Integer gradeId;

    /** 所在班级*/
    private Integer trainClassId;

    /** 是否注册[1:未注册，2:已注册]*/
    private Byte isRegister;
    /***密码**/
    private  String password = "";
    /***证书ID**/
    private Integer certificateId;
    /***获取证书时间**/
    private Long certificateGainTime;

    public Integer getId() {
        return id;
    }

    public String getImage() {
        return image;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Integer certificateId) {
        this.certificateId = certificateId;
    }

    public Long getCertificateGainTime() {
        return certificateGainTime;
    }

    public void setCertificateGainTime(Long certificateGainTime) {
        this.certificateGainTime = certificateGainTime;
    }
}