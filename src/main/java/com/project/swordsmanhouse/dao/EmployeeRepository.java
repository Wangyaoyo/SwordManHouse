package com.project.swordsmanhouse.dao;

import com.project.swordsmanhouse.pojo.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author wy
 * @version 1.0
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> , JpaSpecificationExecutor<Employee> {
    @Modifying
    @Query(value = "update employee set perms=?2  where `name` = ?1", nativeQuery = true)
    int updatePerm(String name, String perm);

    Employee findEmployeeByEmpid(String empid);

//    @Modifying
//    @Query(value = "delete from employee where empid = ?",nativeQuery = true)
    int deleteEmployeeByEmpid(String empId);

}
