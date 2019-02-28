package com.campus.api;

import cn.com.hisee.common.model.CallResult;
import com.campus.domain.utils.DataExport;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by likang on 2018/6/27.
 */
@RestController
public class DataExportAPI {

    /*** 导出**/
    @RequestMapping(value = "/dataExport",method = RequestMethod.POST)
    public CallResult dataExport(String attr,String list, HttpServletResponse response){
        List<String> attrList = Arrays.asList(attr.toString().split(","));
        list = list.replaceAll("},","},,");
        List<String> llst = Arrays.asList(list.split(",,"));
        List<Map> dataList = new ArrayList<Map>();
        for(Object ob : llst){
            ob = json2map(ob.toString());
            dataList.add((LinkedTreeMap)ob);
        }
        DataExport.writeExcel(attrList,dataList,attrList.size(),response);
        return CallResult.success("导出成功！");
    }
    public static Map<String, String> json2map(String str_json) {
        Map<String, String> res = null;
        try {
            Gson gson = new Gson();
            res = gson.fromJson(str_json, new TypeToken<Map<String, String>>() {
            }.getType());
        } catch (JsonSyntaxException e) {
        }
        return res;
    }

}
