package com.campus.service;

import cn.com.hisee.common.model.CallResult;
import com.campus.model.param.LoginLogParam;

/**
 * Created by likang on 2018/8/6.
 */
public interface LoginLogService {

    CallResult addLoginLog(LoginLogParam loginLogParam);
}
