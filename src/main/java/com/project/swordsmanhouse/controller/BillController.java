package com.project.swordsmanhouse.controller;

import com.project.swordsmanhouse.pojo.Bill;
import com.project.swordsmanhouse.service.BillService;
import com.project.swordsmanhouse.utils.BaseUtil;
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
public class BillController {
    private Logger logger = LoggerFactory.getLogger(BillController.class);

    @Autowired
    private BillService billService;

    @PostMapping(value = "/order/bill")
    public GenericResult orderBill(@RequestBody Bill bill){
        logger.info("点餐");
        GenericResult result = new GenericResult();
        bill.setBilltime(new Date());
        bill.setState(StateConstant.NOTPAY);
        try {
            billService.addBill(bill);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @GetMapping(value = "/check/bill")
    public GenericResult getAllBill(){
        logger.info("查询所有账单");
        GenericResult result = new GenericResult();
        result.setData(billService.getBillList());
        return result;
    }

    @GetMapping(value = "/check/bill/{diningTableId}")
    public GenericResult getOneById(@PathVariable int diningTableId){
        logger.info("根据diningTableId查询账单");
        GenericResult result = new GenericResult();
        List<Bill> bill = billService.getBillByTableId(diningTableId);
        if(BaseUtil.listNull(bill)){
            result.setMsg("该账单不存在");
            logger.info("该账单不存在");
        }
        result.setData(bill);
        return result;
    }

    @PutMapping(value = "/check/bill/{diningTableId}")
    public GenericResult payBill(@PathVariable int diningTableId){
        logger.info("结账");
        GenericResult result = new GenericResult();
        try {
            billService.checkBill(diningTableId);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
        }
        return result;
    }
}
