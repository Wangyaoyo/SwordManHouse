package com.project.swordsmanhouse.config;

import com.project.swordsmanhouse.pojo.Employee;
import com.project.swordsmanhouse.service.EmployeeService;
import com.project.swordsmanhouse.utils.BaseUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**UserRealm
 * 完成认证和授权
 *
 * @author wy
 * @version 1.0
 */

public class UserRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(UserRealm.class);
    @Autowired
    private EmployeeService employeeService;

    /* 授权 */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("开始授权~");
        //new 返回对象
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //拿到subject对象
        Subject subject = SecurityUtils.getSubject();
        //拿到subject中的employee对象
        Employee employee = (Employee)subject.getPrincipal();
        //设置用户权限
        info.addStringPermission(employee.getPerms());
        return info;
    }

    /* 认证 */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("开始认证...");
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String empid = token.getUsername();
        Employee employee = employeeService.getByEmpId(empid);
        logger.info("员工信息如下：");
        System.out.println(employee);

        //判断员工是否存在
        if(BaseUtil.objectNull(employee)){
            logger.info("该员工不存在");
            return null;
        }
        //验证密码是否正确(并将employee传递给授权阶段)
        return new SimpleAuthenticationInfo(employee, employee.getPwd(),"");
    }
}
