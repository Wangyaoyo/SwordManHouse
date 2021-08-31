package com.project.swordsmanhouse.dao;

import com.project.swordsmanhouse.pojo.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * @author wy
 * @version 1.0
 */
public interface BillRepository extends JpaRepository<Bill, Integer> , JpaSpecificationExecutor<Bill> {
    @Query(value = "select * from bill where diningtableid = ?1 and state = 0",nativeQuery = true)
    List<Bill> findAllByDiningtableid(int diningtableId);

    @Query(value = "select * from bill where billtime >= ?1",nativeQuery = true)
    List<Bill> countTodayBill(Date time);

    List<Bill> getBillsByDiningtableid(int diningTableId);
}
