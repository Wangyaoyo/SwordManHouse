package com.project.swordsmanhouse.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author wy
 * @version 1.0
 */
@Entity
@Table(name = "bill")
@Data
@AllArgsConstructor
@NoArgsConstructor
//防止Json格式化出错
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String billid;
    private Integer diningtableid;
    private Integer menuid;
    private Integer nums;
    private Double money;
    private Date billtime;
    private Integer state;

}
