package com.project.swordsmanhouse.service;

import com.project.swordsmanhouse.dao.EmployeeRepository;
import com.project.swordsmanhouse.pojo.Employee;
import com.project.swordsmanhouse.utils.GenericException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author wy
 * @version 1.0
 */
@Transactional          //数据修改和删除必须要声明事务
@Service
public class EmployeeService {
    private Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    @Autowired
    private EmployeeRepository employeeRepository;

    /* 添加一个员工 */
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    /* 为某员工增加权限 */
    public void updatePerm(String name, String perm) {
        int num = employeeRepository.updatePerm(name, perm);
        if(num != 1){
            logger.error("员工号不存在，添加权限失败");
            throw new GenericException("员工号不存在，添加权限失败");
        }
    }

    /* 根据员工号查询员工信息 */
    public Employee getByEmpId(String empId) {
        return employeeRepository.findEmployeeByEmpid(empId);
    }

    /* 根据员工号删除员工 */
    public void deleteByEmpId(String empId) {
        int num = employeeRepository.deleteEmployeeByEmpid(empId);
        if(num != 1){
            logger.error("员工号不存在，删除失败");
            throw new GenericException("员工号不存在，删除失败");
        }
    }
}
