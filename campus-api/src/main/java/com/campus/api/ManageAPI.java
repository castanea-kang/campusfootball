package com.campus.api;

import cn.com.hisee.common.model.CallResult;
import com.campus.model.param.TrainClassParam;
import com.campus.model.param.TrainerParam;
import com.campus.service.GradeService;
import com.campus.service.TrainClassService;
import com.campus.service.TrainerService;
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

}
