package com.project.swordsmanhouse.controller;

import com.project.swordsmanhouse.pojo.Menu;
import com.project.swordsmanhouse.service.MenuService;
import com.project.swordsmanhouse.utils.GenericException;
import com.project.swordsmanhouse.utils.GenericResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wy
 * @version 1.0
 */
@RestController
public class MenuController {

    private Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    @GetMapping(value = "/order/menu")
    public List<Menu> getMenuList() {
        logger.info("查询菜单...");
        return menuService.getList();
    }

    @DeleteMapping(value = "/check/menu/{menuId}")
    public GenericResult deleteMenu(@PathVariable int menuId) {
        logger.info("删除id为" + menuId + "的菜单");
        GenericResult result = new GenericResult();
        try {
            menuService.deleteById(menuId);
        } catch (Exception e) {
            result.setMsg("删除id为" + menuId + "的菜单出错");
            logger.info("删除id为" + menuId + "的菜单出错");
        }
        return result;
    }

    /* 修改菜单和添加菜单的区别就是是否传入了id : 但要注意数据库字段not null*/
    @PostMapping(value = "/check/menu")
    public GenericResult addMenu(@RequestBody Menu menu) {
        GenericResult result = new GenericResult();
        if (menu.getId() == null){
            logger.info("添加菜单");
            try {
                menuService.addMenu(menu);
            } catch (Exception e) {
                result.setMsg(e.getMessage());
            }
        }else {
            logger.info("修改菜单");
            try {
                menuService.updateMenu(menu);
            } catch (Exception e) {
                result.setMsg(e.getMessage());
            }
        }
        return result;
    }

}
