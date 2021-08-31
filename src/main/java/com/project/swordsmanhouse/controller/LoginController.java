package com.project.swordsmanhouse.controller;

import com.project.swordsmanhouse.utils.GenericException;
import com.project.swordsmanhouse.utils.GenericResult;
import com.project.swordsmanhouse.utils.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**实现员工的登录
 *
 * @author wy
 * @version 1.0
 */
@RestController
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login")
    public GenericResult loginAndAuth(
            @RequestParam(value = "empid") String empid,
            @RequestParam(value = "pwd") String pwd){
        logger.info("开始认证: "+ empid);
        GenericResult result = new GenericResult();
        /* 认证 */
        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();

        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(empid, MD5Util.encrypt(pwd));

        try {
            /* 执行这一步会进入到shiro的认证部分,认证不通过抛出用户名和密码的异常 */
            subject.login(token);
        } catch (UnknownAccountException e) {
            logger.error("用户名错误");
            result.setMsg("用户名错误");
            new GenericException("用户名错误");
        }catch (IncorrectCredentialsException e){
            logger.error("密码不正确");
            result.setMsg("密码不正确");
            new GenericException("密码不正确");
        }
        return result;
    }
}
