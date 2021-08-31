package com.project.swordsmanhouse;

import com.project.swordsmanhouse.dao.BillRepository;
import com.project.swordsmanhouse.dao.DiningTableRepository;
import com.project.swordsmanhouse.dao.MenuRepository;
import com.project.swordsmanhouse.pojo.Bill;
import com.project.swordsmanhouse.pojo.DiningTable;
import com.project.swordsmanhouse.pojo.Menu;
import com.project.swordsmanhouse.service.DiningTableService;
import com.project.swordsmanhouse.utils.StateConstant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class SwordsmanHouseApplicationTests {

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    DiningTableService diningTableService;

    @Autowired
    BillRepository billRepository;

    @Test
    void contextLoads() {
        System.out.println(menuRepository.findById(2));
    }

    @Test
    void saveTable(){
        diningTableService.initTable(3);
    }


    @Test
    void testDate() throws ParseException {
        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd");
        String time = dff.format(new Date());
        System.out.println(time);
        time += " 07:00:00";
        System.out.println(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parseTime = sdf.parse(time);
//        System.out.println(parseTime);
//        String format = sdf.format(parseTime);
//        System.out.println(format);
    }


    @Test
    void findBill(){
        List<Bill> billsByDiningtableid = billRepository.getBillsByDiningtableid(5);
        System.out.println(billsByDiningtableid);
    }
}
