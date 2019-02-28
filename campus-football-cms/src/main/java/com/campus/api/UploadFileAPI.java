package com.campus.api;

import cn.com.hisee.common.model.BusinessCodeEnum;
import cn.com.hisee.common.model.CallResult;
import com.campus.dao.pojo.TeachPlan;
import com.campus.data.Cnst;
import com.campus.model.param.TeachPlanParam;
import com.campus.service.TeachPlanService;
import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by likang on 2018/7/11.
 */
@RestController
public class UploadFileAPI {

    @Value("${qiniu_AccessKey}")
    private String  accessKey;
    @Value("${qiniu_SecretKey}")
    private String  secretKey;
    @Value("${qiniu_bucket}")
    private String bucket;
    @Value("${qiniu_upload_file_path}")
    private String uploadPath;
    @Value("${qiniu_default_path}")
    private String defaultPath;
    @Autowired
    TeachPlanService teachPlanService;

    @RequestMapping(value = "/unloadFile",method = RequestMethod.POST)
    public CallResult uploadUserImage(Short planType,String gradeId,MultipartFile[] files) {
        try {
            if(files == null || files.length == 0){
                return CallResult.fail("未检测到文件！");
            }
            List<Map<String,Object>> urlList = new ArrayList<>();
            //构造一个带指定Zone对象的配置类
            Configuration cfg = new Configuration(Zone.zone1());
            //...其他参数参考类注释
            UploadManager uploadManager = new UploadManager(cfg);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss");
            Auth auth = Auth.create(accessKey, secretKey);
            for(int i=0;i<files.length;i++){
                String format = simpleDateFormat.format(new Date());
                MultipartFile file = files[i];
                String key = file.getOriginalFilename();
                String fileName = key.substring(0, key.indexOf("."));
                key = uploadPath + key;
                String upToken = auth.uploadToken(bucket,key);
                Response qresponse;
                InputStream inputStream = file.getInputStream();
                byte[] bytes = this.readStream(inputStream);
                qresponse = uploadManager.put(bytes, key, upToken);
                DefaultPutRet putRet = new Gson().fromJson(qresponse.bodyString(), DefaultPutRet.class);
                TeachPlanParam teachPlanParam = new TeachPlanParam();
                teachPlanParam.setType(planType);
                teachPlanParam.setTitle(fileName);
                teachPlanParam.setGradeId(Integer.parseInt(gradeId));
                String url = "/"+putRet.key+"?time="+format;
                teachPlanParam.setUrl(url);
                CallResult result = teachPlanService.addTeachPlan(teachPlanParam);
                if(result.getResult().toString().contains("失败")){
                    return CallResult.fail(BusinessCodeEnum.DEFAULT_SUCCESS.getCode(),"上传文件出现问题！");
                }
                HashMap<String, Object> map = new HashMap<>();
                map.put("fileName",key);
                map.put("url", Cnst.QINIU_BASE_URL+"/"+putRet.key);
                urlList.add(map);
            }
            return new CallResult(BusinessCodeEnum.DEFAULT_SUCCESS.getCode(),"上传文件成功",urlList);
        }catch (IOException e) {
            e.printStackTrace();
            return CallResult.fail(BusinessCodeEnum.DEFAULT_SYS_ERROR.getCode(),"系统错误");
        } catch (Exception e) {
            e.printStackTrace();
            return CallResult.fail(BusinessCodeEnum.DEFAULT_SYS_ERROR.getCode(),"系统错误");

        }
    }
    @RequestMapping(value = "/checkFile",method = RequestMethod.POST)
    public CallResult checkFileExist(String fileList) {
        if(fileList != null && !fileList.isEmpty() && checkFile(fileList).size()>0){
            List<String> fileNameList = checkFile(fileList);
            StringBuffer stringBuffer = new StringBuffer();
            for(int i=0;i<fileNameList.size();i++){
                if(i== 0){
                    stringBuffer.append(fileNameList.get(i));
                }else{
                    stringBuffer.append(",");
                    stringBuffer.append(fileNameList.get(i));
                }
            }
            return CallResult.fail("【"+fileNameList.toString()+"】存在同名文件，是否替换？");
        }
        return CallResult.success("通过");
    }

    public List<String> checkFile(String fnames){
        List<String> fileNames = new ArrayList<>();
        String[] files = fnames.split(",");
        for(int i=0;i<files.length;i++){
            String key = files[i];
            if(checkFileItem(key)){
                fileNames.add(key);
            }
        }
        return fileNames;
    }
    public boolean checkFileItem(String key){
        URL url = null;
        try {
            url = new URL(Cnst.QINIU_BASE_URL+"/"+uploadPath +key);
            URLConnection conn = url.openConnection();
            if(conn.getContent().toString().contains("Document not found")){
                return false;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static byte[] readStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while((len = inStream.read(buffer)) != -1){
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inStream.close();
        return outStream.toByteArray();
    }

}
