package com.project.swordsmanhouse.controller;

import com.project.swordsmanhouse.pojo.DiningTable;
import com.project.swordsmanhouse.service.DiningTableService;
import com.project.swordsmanhouse.utils.GenericResult;
import com.project.swordsmanhouse.utils.StateConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author wy
 * @version 1.0
 */
@RestController
public class TableController {
    private Logger logger = LoggerFactory.getLogger(TableController.class);

    @Autowired
    private DiningTableService diningTableService;

    @PostMapping(value = "/check/table/{num}")
    public GenericResult addDiningTable(@PathVariable("num") int num){
        GenericResult result = new GenericResult();
        try {
            diningTableService.initTable(num);
        } catch (Exception e) {
            logger.info("添加餐桌失败");
            result.setMsg("添加餐桌失败");
        }
        return result;
    }

    @PostMapping(value = "/order/table")
    public GenericResult reserveTable(@RequestBody DiningTable table){
        logger.info("预定餐桌");
        GenericResult result = new GenericResult();
        try {
            diningTableService.save(table);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @GetMapping(value = "/order/table")
    public GenericResult allTableInfo(){
        logger.info("查询所有餐桌信息");
        GenericResult result = new GenericResult();
        List<DiningTable> allTable = null;
        try {
            allTable = diningTableService.getAllTable();
        } catch (Exception e) {
            result.setMsg(e.getMessage());
        }
        result.setData(allTable);
        return result;
    }


    @PutMapping(value = "/order/table/{id}")
    public GenericResult cleanTable(@PathVariable int id){
        logger.info("客人离开，开始清空桌子");
        GenericResult result = new GenericResult();
        DiningTable table = new DiningTable(id);
        try {
            diningTableService.update(table);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
        }
        return result;
    }

}
