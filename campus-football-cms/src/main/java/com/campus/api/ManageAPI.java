package com.campus.api;

import cn.com.hisee.common.model.CallResult;
import com.campus.dao.pojo.Certificate;
import com.campus.dao.pojo.TeachPlan;
import com.campus.model.param.*;
import com.campus.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by likang on 2018/8/6.
 */
@RestController
@RequestMapping(value = "/manageApi")
public class ManageAPI {

    @Autowired
    GradeService gradeService;
    @Autowired
    TrainerService trainerService;
    @Autowired
    TrainClassService trainClassService;
    @Autowired
    CourseService courseService;
    @Autowired
    NewsService newsService;
    @Autowired
    BannerService bannerService;
    @Autowired
    TeachPlanService teachPlanService;
    @Autowired
    FootballKnowService footballKnowService;
    @Autowired
    CertificateService certificateService;
    @Autowired
    UserService userService;
    @Autowired
    OpinionService opinionService;

    @RequestMapping(value = "/getGradeList")
    public CallResult findGradeList(){
        return gradeService.findGradeList();
    }

    @RequestMapping(value = "/trainer/getTrainerList",method = RequestMethod.POST)
    public CallResult findClassById(@RequestBody TrainerParam trainerParam){
        return trainerService.getTrainerList(trainerParam);
    }
    @RequestMapping(value = "/trainer/addTrainer",method = RequestMethod.POST)
    public CallResult addTrainer(@RequestBody TrainerParam trainerParam){
        return trainerService.addTrainer(trainerParam);
    }
    @RequestMapping(value = "/trainer/editTrainer",method = RequestMethod.POST)
    public CallResult editTrainer(@RequestBody TrainerParam trainerParam){
        return trainerService.editTrainer(trainerParam);
    }
    @RequestMapping(value = "/trainer/deleteTrainer",method = RequestMethod.POST)
    public CallResult deleteTrainer(@RequestBody TrainerParam trainerParam){
        return trainerService.deleteTrainer(trainerParam);
    }
    @RequestMapping(value = "/trainer/getTrainer",method = RequestMethod.POST)
    public CallResult getTrainer(@RequestBody TrainerParam trainerParam){
        return trainerService.getTrainer(trainerParam);
    }
    @RequestMapping(value = "/trainClass/getTrainClass",method = RequestMethod.POST)
    public CallResult getTrainClass(@RequestBody TrainClassParam trainClassParam){
        return trainClassService.getTrainClass(trainClassParam);
    }
    @RequestMapping(value = "/trainClass/getTrainClassList",method = RequestMethod.POST)
    public CallResult getTrainClassList(@RequestBody TrainClassParam trainClassParam){
        return trainClassService.getTrainClassList(trainClassParam);
    }
    @RequestMapping(value = "/trainClass/addTrainClass",method = RequestMethod.POST)
    public CallResult addTrainClass(@RequestBody TrainClassParam trainClassParam){
        System.out.println("---------addclass");
        return trainClassService.addTrainClass(trainClassParam);
    }
    @RequestMapping(value = "/trainClass/editTrainClass",method = RequestMethod.POST)
    public CallResult editTrainClass(@RequestBody TrainClassParam trainClassParam){
        return trainClassService.editTrainClass(trainClassParam);
    }
    @RequestMapping(value = "/trainClass/deleteTrainClass",method = RequestMethod.POST)
    public CallResult deleteTrainClass(@RequestBody TrainClassParam trainClassParam){
        return trainClassService.deleteTrainClass(trainClassParam);
    }

    @RequestMapping(value = "/trainer/getClassTrainerList",method = RequestMethod.POST)
    public CallResult getClassTrainerList(@RequestBody TrainerParam trainerParam){
        return trainerService.getClassTrainerList(trainerParam);
    }
    @RequestMapping(value = "/trainClass/getClassCourseList",method = RequestMethod.POST)
    public CallResult getClassCourseList(@RequestBody CourseParam courseParam){
        return courseService.getClassCourseList(courseParam);
    }
    /**删除班级学员**/
    @RequestMapping(value = "/trainClass/deleteClassTrainer",method = RequestMethod.POST)
    public CallResult deleteClassTrainer(@RequestBody TrainClassParam trainClassParam){
        return trainClassService.deleteClassTrainer(trainClassParam);
    }
    /**添加班级学员**/
    @RequestMapping(value = "/trainClass/addClassTrainer",method = RequestMethod.POST)
    public CallResult addClassTrainer(@RequestBody TrainerParam trainerParam){
        return trainerService.addClassTrainer(trainerParam);
    }
    /**获取新闻列表**/
    @RequestMapping(value = "/news/getNewsList",method = RequestMethod.POST)
    public CallResult getNewsList(@RequestBody NewsParam newsParam){
        return newsService.getNewsList(newsParam);
    }
    /**添加新闻**/
    @RequestMapping(value = "/news/addNews",method = RequestMethod.POST)
    public CallResult addNews(@RequestBody NewsParam newsParam){
        return newsService.addNews(newsParam);
    }
    /**添加新闻**/
    @RequestMapping(value = "/news/editNews",method = RequestMethod.POST)
    public CallResult editNews(@RequestBody NewsParam newsParam){
        return newsService.editNews(newsParam);
    }
    /**删除新闻**/
    @RequestMapping(value = "/news/deleteNews",method = RequestMethod.POST)
    public CallResult deleteNews(@RequestBody NewsParam newsParam){
        return newsService.deleteNews(newsParam);
    }
    /**删除新闻**/
    @RequestMapping(value = "/news/getNews",method = RequestMethod.POST)
    public CallResult getNews(@RequestBody NewsParam newsParam){
        return newsService.getNews(newsParam);
    }
    /**获取banner列表**/
    @RequestMapping(value = "/banner/getBannerList",method = RequestMethod.POST)
    public CallResult getBannerList(@RequestBody BannerParam bannerParam){
        return bannerService.getBannerList(bannerParam);
    }
    /**添加banner**/
    @RequestMapping(value = "/banner/addBanner",method = RequestMethod.POST)
    public CallResult addBanner(@RequestBody BannerParam bannerParam){
        return bannerService.addBanner(bannerParam);
    }
    /**编辑banner**/
    @RequestMapping(value = "/banner/editBanner",method = RequestMethod.POST)
    public CallResult editBanner(@RequestBody BannerParam bannerParam){
        return bannerService.editBanner(bannerParam);
    }
    /**根据id获取banner**/
    @RequestMapping(value = "/banner/getBanner",method = RequestMethod.POST)
    public CallResult getBanner(@RequestBody BannerParam bannerParam){
        return bannerService.getBanner(bannerParam);
    }
    /**删除banner**/
    @RequestMapping(value = "/banner/deleteBanner",method = RequestMethod.POST)
    public CallResult deleteBanner(@RequestBody BannerParam bannerParam){
        return bannerService.deleteBanner(bannerParam);
    }
    /**获取教学教案**/
    @RequestMapping(value = "/teachPlan/getTeachPlanList",method = RequestMethod.POST)
    public CallResult getTeachPlanList(@RequestBody TeachPlanParam teachPlanParam){
        return teachPlanService.getTeachPlanList(teachPlanParam);
    }
    /**删除教案**/
    @RequestMapping(value = "/teachPlan/deleteTeachPlan",method = RequestMethod.POST)
    public CallResult deleteTeachPlan(@RequestBody TeachPlanParam teachPlanParam){
        return teachPlanService.deleteTeachPlan(teachPlanParam);
    }
    /**更新教案**/
    @RequestMapping(value = "/teachPlan/editTeachPlan",method = RequestMethod.POST)
    public CallResult editTeachPlan(@RequestBody TeachPlanParam teachPlanParam){
        return teachPlanService.editTeachPlan(teachPlanParam);
    }
    /**获取足球知识列表**/
    @RequestMapping(value = "/footballKnow/getFootballKnowList",method = RequestMethod.POST)
    public CallResult getFootballKnowList(@RequestBody FootballKnowParam footballKnowParam){
        return footballKnowService.getFootBallKnowList(footballKnowParam);
    }
    /**根据id获取足球知识**/
    @RequestMapping(value = "/footballKnow/getFootballKnow",method = RequestMethod.POST)
    public CallResult getFootballKnow(@RequestBody FootballKnowParam footballKnowParam){
        return footballKnowService.getFootballKnow(footballKnowParam);
    }
    /**添加足球知识**/
    @RequestMapping(value = "/footballKnow/addFootballKnow",method = RequestMethod.POST)
    public CallResult addFootballKnow(@RequestBody FootballKnowParam footballKnowParam){
        return footballKnowService.addFootballKnow(footballKnowParam);
    }
    /**编辑足球知识**/
    @RequestMapping(value = "/footballKnow/editFootballKnow",method = RequestMethod.POST)
    public CallResult editFootballKnow(@RequestBody FootballKnowParam footballKnowParam){
        return footballKnowService.editFootballKnow(footballKnowParam);
    }
    /**删除足球知识**/
    @RequestMapping(value = "/footballKnow/deleteFootballKnow",method = RequestMethod.POST)
    public CallResult deleteFootballKnow(@RequestBody FootballKnowParam footballKnowParam){
        return footballKnowService.deleteFootballKnow(footballKnowParam);
    }
    /**获取证书列表**/
    @RequestMapping(value = "/getCertificateList")
    public CallResult getCertificateList(){
        return certificateService.getCertificateList();
    }
    /**获取用户list**/
    @RequestMapping(value = "/user/getUserList",method = RequestMethod.POST)
    public CallResult getUserList(@RequestBody UserParam userParam){
        return userService.getUserList(userParam);
    }
    /**添加用户**/
    @RequestMapping(value = "/user/addUser",method = RequestMethod.POST)
    public CallResult addUser(@RequestBody UserParam userParam){
        return userService.addUser(userParam);
    }
    /**编辑用户**/
    @RequestMapping(value = "/user/editUser",method = RequestMethod.POST)
    public CallResult editUser(@RequestBody UserParam userParam){
        return userService.editUser(userParam);
    }
    /**根据id获取用户**/
    @RequestMapping(value = "/user/getUser",method = RequestMethod.POST)
    public CallResult getUser(@RequestBody UserParam userParam){
        return userService.findById(userParam);
    }
    /**删除用户**/
    @RequestMapping(value = "/user/deleteUser",method = RequestMethod.POST)
    public CallResult deleteUser(@RequestBody UserParam userParam){
        return userService.deleteUser(userParam);
    }
    /**获取反馈list**/
    @RequestMapping(value = "/opinion/getOpinionList",method = RequestMethod.POST)
    public CallResult getOpinionList(@RequestBody OpinionParam opinionParam){
        return opinionService.getOpinionList(opinionParam);
    }
    /**删除反馈意见**/
    @RequestMapping(value = "/opinion/deleteOpinion",method = RequestMethod.POST)
    public CallResult deleteOpinion(@RequestBody OpinionParam opinionParam){
        return opinionService.deleteOpinion(opinionParam);
    }
    /**根据id获取反馈信息**/
    @RequestMapping(value = "/opinion/getOpinion",method = RequestMethod.POST)
    public CallResult getOpinion(@RequestBody OpinionParam opinionParam){
        return opinionService.getOpinion(opinionParam);
    }
}
