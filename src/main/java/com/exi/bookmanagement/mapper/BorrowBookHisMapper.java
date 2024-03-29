package com.exi.bookmanagement.mapper;

import com.exi.bookmanagement.entity.BorrowBookHis;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.mapper.BorrowBookMapper
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/1/28 14:25
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/1/28    Fengsx     v1.0.0      修改原因
 */
public interface BorrowBookHisMapper {

    @Select("SELECT * FROM borrow_book_his")
    @Results(id = "borrowBookHisMap", value = {
            @Result(property = "borBookId",  column = "bor_book_id"),
            @Result(property = "borBookNum", column = "bor_book_num"),
            @Result(property = "booleanLate", column = "boolean_late"),
            @Result(property = "expectGetBackTime", column = "expect_get_back_time"),
            @Result(property = "giveBookTime", column = "give_book_time"),
            @Result(property = "getBackBookTime", column = "get_back_book_time"),
            @Result(property = "subscribeTime", column = "subscribe_time"),
            @Result(property = "state", column = "state"),
    })
    List<BorrowBookHis> getAllBorrowBookHis();

    @Insert("INSERT INTO borrow_book_his(bor_book_id, bor_book_num, boolean_late, expect_get_back_time, give_book_time, get_back_book_time, subscribe_time, state)" +
            " VALUES(#{borBookId}, #{borBookNum}, #{booleanLate}, #{expectGetBackTime}, #{giveBookTime}, #{getBackBookTime}, #{subscribeTime}, #{state} )")
    int insertBorrowBookHisBean(BorrowBookHis borrowBookHis);

    @Select("SELECT * FROM borrow_book_his WHERE bor_book_id = #{borBookId}")
    @ResultMap(value = "borrowBookHisMap")
    BorrowBookHis getOneBorrowBookHisBean(Long borBookId);

    /**
     * 借书状态加一层筛选，状态为 4：已借书,0：已预约
     **/
    @Select("SELECT * FROM borrow_book_his WHERE state = 4")
    @ResultMap(value = "borrowBookHisMap")
    List<BorrowBookHis> getStateForFour();

    @Select("SELECT * FROM borrow_book_his WHERE state = 0")
    @ResultMap(value = "borrowBookHisMap")
    List<BorrowBookHis> getStateForOne();

    /**
     * 更新读者的借阅状态、还书时间、是否逾期
     **/
    @Update("UPDATE borrow_book_his " +
            "SET state=#{state}, give_book_time=#{giveBookTime}, get_back_book_time=#{getBackBookTime}, boolean_late=#{booleanLate} " +
            "WHERE bor_book_id =#{borBookId}")
    int updateBorrowBookHisBean(BorrowBookHis borrowBookHis);
}
