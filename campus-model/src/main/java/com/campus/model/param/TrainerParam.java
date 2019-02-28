package com.campus.model.param;

/**
 * Created by likang on 2018/8/6.
 */
public class TrainerParam {

    private Integer trainerId;

    private String trainerName;

    private String idCard;

    private String phone;

    private String teacherCertificateNum;

    private String keyword;

    private Integer gradeId;

    private Short isTrain;

    private Byte trainStatus;

    /** 工作单位*/
    private String workUnit;

    /** 性别[1:男，2:女]*/
    private Byte gender;

    /** 邮箱*/
    private String mailbox;

    /** 毕业院校*/
    private String graduateSchool;

    /** 工作年限*/
    private Byte jobYear;

    /** 每周教学学时*/
    private Byte weekHour;

    /** 所在班级*/
    private Integer trainClassId;

    private Integer page = 1;

    private Integer pagesize = 10;

    private  String password = "";

    /**新密码*/
    private  String newPassword = "";
    /**重复密码**/
    private String rNewPassword = "";
    /**验证码**/
    private String vCode;
    /***证书ID**/
    private Integer certificateId;

    /***收藏事件类型**/
   private Integer eventId;
   /****收藏时间类型 1:新闻 2：教案**/
   private Short eventType;
   /**反馈内容 **/
   private String opinionContent;



    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Short getIsTrain() {
        return isTrain;
    }

    public void setIsTrain(Short isTrain) {
        this.isTrain = isTrain;
    }

    public Byte getTrainStatus() {
        return trainStatus;
    }

    public void setTrainStatus(Byte trainStatus) {
        this.trainStatus = trainStatus;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTeacherCertificateNum() {
        return teacherCertificateNum;
    }

    public void setTeacherCertificateNum(String teacherCertificateNum) {
        this.teacherCertificateNum = teacherCertificateNum;
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public String getGraduateSchool() {
        return graduateSchool;
    }

    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getvCode() {
        return vCode;
    }

    public void setvCode(String vCode) {
        this.vCode = vCode;
    }

    public String getrNewPassword() {
        return rNewPassword;
    }

    public void setrNewPassword(String rNewPassword) {
        this.rNewPassword = rNewPassword;
    }

    public Integer getTrainClassId() {
        return trainClassId;
    }

    public void setTrainClassId(Integer trainClassId) {
        this.trainClassId = trainClassId;
    }

    public Integer getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Integer certificateId) {
        this.certificateId = certificateId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    /**
     * Sets new 收藏时间类型 1:新闻 2：教案.
     *
     * @param eventType New value of 收藏时间类型 1:新闻 2：教案.
     */
    public void setEventType(Short eventType) {
        this.eventType = eventType;
    }

    /**
     * Gets 收藏时间类型 1:新闻 2：教案.
     *
     * @return Value of 收藏时间类型 1:新闻 2：教案.
     */
    public Short getEventType() {
        return eventType;
    }


    /**
     * Gets 反馈内容.
     *
     * @return Value of 反馈内容.
     */
    public String getOpinionContent() {
        return opinionContent;
    }

    /**
     * Sets new 反馈内容.
     *
     * @param opinionContent New value of 反馈内容.
     */
    public void setOpinionContent(String opinionContent) {
        this.opinionContent = opinionContent;
    }
}
