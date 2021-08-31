package com.project.swordsmanhouse.pojo;

import com.project.swordsmanhouse.utils.StateConstant;
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
@Table(name = "diningtable")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiningTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer state;
    private String ordername;
    private String ordertel;
    private Date ordertime;

    /* 用于预订餐桌 */
    public DiningTable(String ordername, String ordertel) {
        this.state = StateConstant.USING;
        this.ordername = ordername;
        this.ordertel = ordertel;
        this.ordertime = new Date();
    }

    /* 用于清空餐桌状态 */
    public DiningTable(Integer id) {
        this.id = id;
        this.ordername = "";
        this.ordertel = "";
        this.state = StateConstant.NOTUSE;
    }
}
