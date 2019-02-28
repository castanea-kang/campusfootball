package com.campus.api;

import cn.com.hisee.common.model.CallResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

/**
 * TO DO :
 *
 * @author 睿动体育 | wangzhiqiang
 * @create 2018-08-24 17:52
 */
@RestController
@RequestMapping(value = "/feedBackAPI")
public class FeedBackAPI {
    @RequestMapping(value = "/setFeedBack",method = RequestMethod.POST)
    public CallResult setFeedBack(){
      return  null;
    }
}
