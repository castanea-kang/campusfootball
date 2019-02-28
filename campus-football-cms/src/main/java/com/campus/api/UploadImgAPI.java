package com.campus.api;

import cn.com.hisee.common.model.BusinessCodeEnum;
import cn.com.hisee.common.model.CallResult;
import com.campus.data.Cnst;
import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by likang on 2018/7/11.
 */
@RestController
public class UploadImgAPI {

    @Value("${qiniu_AccessKey}")
    private String  accessKey;
    @Value("${qiniu_SecretKey}")
    private String  secretKey;
    @Value("${qiniu_bucket}")
    private String bucket;
    @Value("${qiniu_upload_path}")
    private String uploadPath;
    @Value("${qiniu_default_path}")
    private String defaultPath;

    @RequestMapping(value = "/uploadImg",method = RequestMethod.POST)
    public CallResult uploadUserImage(MultipartFile upfile,MultipartFile file) {
        try {
            if(upfile == null){
                upfile = file;
            }
            //构造一个带指定Zone对象的配置类
            Configuration cfg = new Configuration(Zone.zone1());
            //...其他参数参考类注释
            UploadManager uploadManager = new UploadManager(cfg);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss");
            String format = simpleDateFormat.format(new Date());
            String key = upfile.getOriginalFilename();
            String newKey = key.substring(0, key.indexOf(".")) + "-" + format + (new Random().nextInt(100) + 1) + key.substring(key.lastIndexOf("."));
            key = uploadPath + newKey;
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            Response qresponse;
            InputStream inputStream = upfile.getInputStream();
            byte[] bytes = this.readStream(inputStream);
//            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bytes);
            qresponse = uploadManager.put(bytes, key, upToken);
            DefaultPutRet putRet = new Gson().fromJson(qresponse.bodyString(), DefaultPutRet.class);
            HashMap<String, Object> map = new HashMap<>();
            map.put("imageUrl", Cnst.QINIU_BASE_URL+"/"+putRet.key);
            return new CallResult(BusinessCodeEnum.DEFAULT_SUCCESS.getCode(),"上传图片成功",map);
        }catch (IOException e) {
            e.printStackTrace();
            return CallResult.fail(BusinessCodeEnum.DEFAULT_SYS_ERROR.getCode(),"系统错误");
        } catch (Exception e) {
            e.printStackTrace();
            return CallResult.fail(BusinessCodeEnum.DEFAULT_SYS_ERROR.getCode(),"系统错误");

        }
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
