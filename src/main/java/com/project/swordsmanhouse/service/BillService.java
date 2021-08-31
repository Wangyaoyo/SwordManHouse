package com.project.swordsmanhouse.service;

import com.project.swordsmanhouse.dao.BillRepository;
import com.project.swordsmanhouse.dao.DiningTableRepository;
import com.project.swordsmanhouse.dao.MenuRepository;
import com.project.swordsmanhouse.pojo.Bill;
import com.project.swordsmanhouse.pojo.DiningTable;
import com.project.swordsmanhouse.pojo.Menu;
import com.project.swordsmanhouse.utils.BaseUtil;
import com.project.swordsmanhouse.utils.GenericException;
import com.project.swordsmanhouse.utils.StateConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author wy
 * @version 1.0
 */
@Service
@Transactional
public class BillService {
    private Logger logger = LoggerFactory.getLogger(BillService.class);

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private DiningTableRepository diningTableRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    JavaMailSenderImpl javaMailSender;

    /* 查询所有账单 */
    public List<Bill> getBillList() {
        return billRepository.findAll();
    }

    /* 根据id查询账单 */
    public List<Bill> getBillByTableId(int tableId) {
        return billRepository.getBillsByDiningtableid(tableId);
    }

    /* 获得唯一订单号的方法 */
    public static String getUUID() {
        String replaceUUID = UUID.randomUUID().toString().replace("-", "");
        return replaceUUID;
    }

    /* 点餐 */
    public void addBill(Bill bill) {
        //账单表
        bill.setBillid(getUUID());
        //得到菜品价格
        Optional<Menu> menu = menuRepository.findById(bill.getMenuid());
        if (!menu.isPresent()) {
            logger.error("该菜品不存在");
            throw new GenericException("该菜品不存在");
        }
        bill.setMoney(bill.getNums() * menu.get().getPrice());
        billRepository.save(bill);

        //餐桌表
        Optional<DiningTable> optTable = diningTableRepository.findById(bill.getDiningtableid());
        if (!optTable.isPresent()) {
            logger.error("该餐桌不存在");
            throw new GenericException("该餐桌不存在");
        }
        DiningTable table = optTable.get();
        if (table.getState() == StateConstant.USING) {
            logger.error("该餐桌已在使用中");
            throw new GenericException("该餐桌已在使用中");
        }
        table.setState(StateConstant.USING);
        table.setOrdertime(new Date());
        diningTableRepository.save(table);
    }

    /* 结账 :修改餐桌状态和支付状态*/
    public void checkBill(int diningTableId) {
        List<Bill> bill = billRepository.findAllByDiningtableid(diningTableId);
        if (bill.isEmpty()) {
            logger.error("餐桌号为" + diningTableId + "的账单不存在或已结过");
            throw new GenericException("餐桌号为" + diningTableId + "的账单不存在或已结过");
        }
        for (Bill b : bill) {
            System.out.println(b);
            b.setState(StateConstant.PAID);
            billRepository.save(b);
        }

        //修改餐桌状态
        DiningTable newTable = new DiningTable(diningTableId);
        DiningTable save = diningTableRepository.save(newTable);
        if (BaseUtil.objectNull(save)) {
            logger.error("餐桌号为" + diningTableId + "的餐桌清空失败");
            throw new GenericException("餐桌号为" + diningTableId + "的餐桌清空失败");
        }
    }

    /* 定时发送当日销售额给店主 */
    /* 秒 分 时 日 月 星期 */
    @Scheduled(cron = "0 00 23 * * *")
    public void mail() {
        logger.info("查询今日订单");
        List<Bill> bills = billRepository.countTodayBill(startTime());
        //计算订单量和销售额
        int count = bills.size();
        Double money = 0.0;
        for (Bill b : bills) {
            money += b.getMoney();
        }
        logger.info("得到订单量：" + count + "销售额：" + money);
        //发送邮件
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("1368829476@qq.com");
        mailMessage.setTo("1368829476@qq.com");
        mailMessage.setSubject("今日账单总结");
        mailMessage.setText("掌柜的，今日完成订单量" + count + "份，总计销售额是" + money + "元。");
        mailMessage.setSentDate(new Date());
        javaMailSender.send(mailMessage);
        logger.info("发送邮件到掌柜的成功！请查收");
    }

    /* 获取今日七点的时间用于查询今日订单量 */
    public Date startTime() {
        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd");
        String time = dff.format(new Date());
        System.out.println(time);
        time += " 07:00:00";
        System.out.println(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(time);
        } catch (ParseException e) {
            throw new GenericException("转换日期格式出错");
        }

    }
}
