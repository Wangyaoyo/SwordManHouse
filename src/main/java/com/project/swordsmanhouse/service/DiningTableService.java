package com.project.swordsmanhouse.service;
import com.project.swordsmanhouse.dao.DiningTableRepository;
import com.project.swordsmanhouse.pojo.DiningTable;
import com.project.swordsmanhouse.utils.BaseUtil;
import com.project.swordsmanhouse.utils.GenericException;
import com.project.swordsmanhouse.utils.StateConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author wy
 * @version 1.0
 */
@Service
@Transactional
public class DiningTableService {
    private Logger logger = LoggerFactory.getLogger(DiningTableService.class);
    @Autowired
    private DiningTableRepository diningTableRepository;

    /* 初始化一定数量的桌子 */
    public void initTable(int num) {
        for (int i = 0; i < num; i++) {
            diningTableRepository.initializeTable();
        }
    }

    /* 预定 */
    public void save(DiningTable table) {
        Optional<DiningTable> oldTable = diningTableRepository.findById(table.getId());
        if(oldTable.get().getState().equals(StateConstant.USING)){
            logger.error("该餐桌已在使用中");
            throw new GenericException("该餐桌已在使用中");
        }
        //设置预定的状态
        table.setState(StateConstant.USING);
        table.setOrdertime(new Date());

        DiningTable save = diningTableRepository.save(table);
        if(BaseUtil.objectNull(save)){
            logger.error("预定餐桌失败");
            throw new GenericException("预定失败，请检查");
        }
    }

    /* 清空餐桌 */
    public void update(DiningTable table){
        Optional<DiningTable> oldTable = diningTableRepository.findById(table.getId());
        if(oldTable.get().getState().equals(StateConstant.NOTUSE)){
            logger.error("该餐桌无需清空");
            throw new GenericException("该餐桌无需清空");
        }
        DiningTable save = diningTableRepository.save(table);
        if(BaseUtil.objectNull(save)){
            logger.error("清空餐桌失败");
            throw new GenericException("清空失败，请检查");
        }
    }
    /* 查询所有餐桌状态 */
    public List<DiningTable> getAllTable(){
        List<DiningTable> all = diningTableRepository.findAll();
        if(BaseUtil.listNull(all)){
            logger.info("无餐桌显示");
            throw new GenericException("无餐桌显示");
        }
        return all;
    }
}
