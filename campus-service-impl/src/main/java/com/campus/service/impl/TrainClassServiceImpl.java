package com.campus.service.impl;

import cn.com.hisee.common.model.CallResult;
import cn.com.hisee.common.service.AbstractTemplateAction;
import com.campus.domain.TrainClassDomain;
import com.campus.domain.respository.TrainClassDomainRepository;
import com.campus.model.param.TrainClassParam;
import com.campus.service.AbstractService;
import com.campus.service.TrainClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainClassServiceImpl extends AbstractService implements TrainClassService {
    @Autowired
    TrainClassDomainRepository trainClassDomainRepository;


    @Override
    public CallResult getTrainClass(TrainClassParam param) {
        synchronized (this){
            final TrainClassDomain trainClassDomain = trainClassDomainRepository.createDomain(param);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public  CallResult checkParam(){
                    return trainClassDomain.checkClassIdParam();
                }
                @Override
                public CallResult doAction() {
                    return trainClassDomain.getTrainClass();
                }
            });
        }
    }

    @Override
    public CallResult getTrainClassList(TrainClassParam param) {
        synchronized (this){
            final TrainClassDomain trainClassDomain = trainClassDomainRepository.createDomain(param);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public CallResult doAction() {
                    return trainClassDomain.getTrainClassList();
                }
            });
        }
    }

    @Override
    public CallResult addTrainClass(TrainClassParam param) {
        synchronized (this){
            final TrainClassDomain trainClassDomain = trainClassDomainRepository.createDomain(param);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public  CallResult checkParam(){
                    return trainClassDomain.checkParam();
                }
                @Override
                public CallResult doAction() {
                    return trainClassDomain.addTrainClass();
                }
            });
        }
    }

    @Override
    public CallResult editTrainClass(TrainClassParam param) {
        synchronized (this){
            final TrainClassDomain trainClassDomain = trainClassDomainRepository.createDomain(param);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public  CallResult checkParam(){
                    return trainClassDomain.checkClassIdParam();
                }
                @Override
                public CallResult doAction() {
                    return trainClassDomain.editTrainClass();
                }
            });
        }
    }

    @Override
    public CallResult deleteTrainClass(TrainClassParam param) {
        synchronized (this){
            final TrainClassDomain trainClassDomain = trainClassDomainRepository.createDomain(param);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public  CallResult checkParam(){
                    return trainClassDomain.checkClassIdParam();
                }
                @Override
                public CallResult doAction() {
                    return trainClassDomain.deleteTrainClass();
                }
            });
        }
    }

    @Override
    public CallResult deleteClassTrainer(TrainClassParam param) {
        synchronized (this){
            final TrainClassDomain trainClassDomain = trainClassDomainRepository.createDomain(param);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public  CallResult checkParam(){
                    return trainClassDomain.checkClassIdParam();
                }
                @Override
                public CallResult doAction() {
                    return trainClassDomain.deleteClassTrainer();
                }
            });
        }
    }
}
