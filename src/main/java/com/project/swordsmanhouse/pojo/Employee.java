package com.project.swordsmanhouse.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author wy
 * @version 1.0
 */
@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String empid;
    private String pwd;
    private String name;
    private String job;
    private String perms;

    public Employee(String empid, String pwd, String name, String job, String perms) {
        this.empid = empid;
        this.pwd = pwd;
        this.name = name;
        this.job = job;
        this.perms = perms;
    }
}
