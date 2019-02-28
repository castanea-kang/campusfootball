package com.campus.controller;

import com.campus.dao.pojo.User;
import com.campus.model.param.LoginLogParam;
import com.campus.service.LoginLogService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class LonginController {

    @Autowired
    LoginLogService loginLogService;

    @RequestMapping({"/","/index"})
    public String index(Model model){
        System.out.println("index==========");
        SecurityUtils.getSubject().getSession().setTimeout(1800000);
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        LoginLogParam loginLogParam = new LoginLogParam();
        loginLogParam.setUserId(user.getId());
        loginLogService.addLoginLog(loginLogParam);
//        System.out.println(user.getOrgId());
        model.addAttribute("userId",user.getId());
        return "/index";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Map<String, Object> map) throws Exception{
        System.out.println("LoginController.login()");
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getAttribute("shiroLoginFailure");
        System.out.println("exception=" + exception);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> "+exception;
                System.out.println("else -- >" + exception);
            }
        }
        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理
        System.out.println("controller login---------------");
        return "/login";
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "403";
    }

    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @RequestMapping("/trainerList")
    public String trainerList(){
        System.out.println("------trainerList-------");
        return "trainer-list";
    }
    @RequestMapping("/trainerAdd")
    public String trainerAdd(){
        return "trainer-add";
    }
    @RequestMapping("/trainerUpdate")
    public String trainerUpdate(){
        return "trainer-update";
    }
    @RequestMapping("/trainClassList")
    public String trainClassList(){
        return "trainer-class-list";
    }
    @RequestMapping("/trainClassAdd")
    public String trainClassAdd(){
        return "trainer-class-add";
    }
    @RequestMapping("/trainClassUpdate")
    public String trainClassUpdate(){
        return "trainer-class-update";
    }
    @RequestMapping("/classTrainerList")
    public String classTrainerList(){
        return "class-trainer-list";
    }
    @RequestMapping("/classTrainerAdd")
    public String classTrainerAdd(){
        return "class-trainer-add";
    }
    @RequestMapping("/newsList")
    public String newsList(){
        return "news-list";
    }
    @RequestMapping("/newsAdd")
    public String newsAdd(){
        return "news-add";
    }
    @RequestMapping("/uploadImg")
    public String uploadImg(){
        return "welcome";
    }
    @RequestMapping("/bannerList")
    public String bannerList(){
        return "banner-list";
    }
    @RequestMapping("/bannerAdd")
    public String bannerAdd(){
        return "banner-add";
    }
    @RequestMapping("/teachPlanList")
    public String teachPlanList(){
        return "teach-plan-list";
    }
    @RequestMapping("/teachPlanAdd")
    public String teachPlanAdd(){
        return "teach-plan-add";
    }
    @RequestMapping("/knowList")
    public String knowList(){
        return "know-list";
    }
    @RequestMapping("/knowAdd")
    public String knowAdd(){
        return "know-add";
    }
    @RequestMapping("/userList")
    public String userList(){
        return "user-list";
    }
    @RequestMapping("/userAdd")
    public String userAdd(){
        return "user-add";
    }
    @RequestMapping("/opinionList")
    public String opinionList(){
        return "opinion-list";
    }
}