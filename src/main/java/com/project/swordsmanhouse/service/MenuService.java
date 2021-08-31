package com.project.swordsmanhouse.service;

import com.project.swordsmanhouse.dao.MenuRepository;
import com.project.swordsmanhouse.pojo.Menu;
import com.project.swordsmanhouse.utils.BaseUtil;
import com.project.swordsmanhouse.utils.GenericException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author wy
 * @version 1.0
 */
//默认将类中的所有函数纳入事务管理
@Transactional
@Service
public class MenuService {

    @Autowired
    MenuRepository menuRepository;

    public List<Menu> getList() {
        return menuRepository.findAll();
    }

    public void deleteById(int id) {
        menuRepository.deleteById(id);
    }

    public void addMenu(Menu menu) {
        Menu save = menuRepository.save(menu);
        if (BaseUtil.objectNull(save)) {
            throw new GenericException("添加菜单失败");
        }
    }

    /**
     * 未知被修改的菜单项数，遍历修改
     * 数据库又不允许为null写入
     *
     * @param menu
     * @return
     */
    public void updateMenu(Menu menu) {
        Menu m = menuRepository.getById(menu.getId());
        if (menu.getPrice() != null) {
            m.setPrice(menu.getPrice());
        }
        if (menu.getName() != null) {
            m.setName(menu.getName());
        }
        if (menu.getType() != null) {
            m.setType(menu.getType());
        }
        if(BaseUtil.objectNull(menuRepository.save(m)))
            throw new GenericException("更新菜单失败");
    }

    public Menu getById(int id) {
        Menu m = menuRepository.getById(id);
        if(BaseUtil.objectNull(m)){
            throw new GenericException("菜单不存在");
        }
        return m;
    }
}
