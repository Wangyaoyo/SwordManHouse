package com.project.swordsmanhouse.controller;

import com.project.swordsmanhouse.pojo.Employee;
import com.project.swordsmanhouse.service.EmployeeService;
import com.project.swordsmanhouse.utils.GenericResult;
import com.project.swordsmanhouse.utils.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wy
 * @version 1.0
 */
@RestController
@ResponseBody
public class EmployeeController {
    private Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    /**
     * 添加员工
     *
     * @param employee
     * @return
     */
    @PostMapping(value = "/check/emp")
    public GenericResult addEmployee(@RequestBody Employee employee) {
        logger.info("添加员工信息");
        GenericResult result = new GenericResult();
        employee.setPwd(MD5Util.encrypt(employee.getPwd()));
        try {
            //无需给id，id自增
            employeeService.addEmployee(employee);
            logger.info("添加" + employee.getName() + "信息成功！");
        } catch (Exception e) {
            result.setMsg("添加员工失败");
        }
        return result;
    }


    /**
     * 为员工添加权限
     *
     * @param name
     * @param perm
     * @return
     */
    @PutMapping(value = "/check/emp")
    public GenericResult updatePerm(
            @RequestParam("name") String name,
            @RequestParam("perm") String perm) {
        logger.info("修改员工权限");
        GenericResult result = new GenericResult();
        try {
            employeeService.updatePerm(name,perm);
        } catch (Exception e) {
            result.setMsg("修改权限失败");
        }
        return result;
    }


    /**
     * 根据员工号删除员工
     *
     * @param empId
     * @return
     */
    @DeleteMapping(value = "/check/emp/{empId}")
    public GenericResult deleteEmp(@PathVariable("empId") String empId) {
        logger.info("删除员工号为" + empId + "的员工");
        GenericResult result = new GenericResult();
        try {
            employeeService.deleteByEmpId(empId);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
        }
        return result;
    }

}
