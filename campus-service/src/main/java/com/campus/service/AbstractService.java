package com.campus.service;

import cn.com.hisee.common.service.ServiceTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by likang on 18/8/3.
 */
public abstract class AbstractService {

    @Autowired
    protected ServiceTemplate serviceTemplate;
}
