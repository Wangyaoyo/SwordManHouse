package com.project.swordsmanhouse.dao;

import com.project.swordsmanhouse.pojo.DiningTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author wy
 * @version 1.0
 */
public interface DiningTableRepository extends JpaRepository<DiningTable, Integer> {
    @Modifying
    @Query(value = "INSERT INTO diningtable (state) VALUES(0)",nativeQuery = true)
    public int initializeTable();

}
