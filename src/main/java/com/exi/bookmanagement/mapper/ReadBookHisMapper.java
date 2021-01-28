package com.exi.bookmanagement.mapper;

import com.exi.bookmanagement.entity.ReadBookHis;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.mapper.ReadBookHisMapper
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/1/27 14:27
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/1/27    Fengsx     v1.0.0      修改原因
 */
@CacheNamespace(blocking = true)
public interface ReadBookHisMapper {
    @Select("SELECT * FROM read_book_his")
    @Results(id = "readBookHisMap", value = {
            @Result(property = "readId",  column = "read_id"),
            @Result(property = "readNum", column = "read_num"),
    })
    List<ReadBookHis> getAllReadBookHisBean();

    @Insert("INSERT INTO read_book_his(read_id, read_num) VALUES(#{readId}, #{readNum} )")
    int insertReadBookHisBean(ReadBookHis readBookHis);

    @Select("SELECT * FROM read_book_his WHERE read_id = #{readId}")
    @ResultMap(value = "readBookHisMap")
    ReadBookHis getOneReadBookHisBean(Long readId);

    @Update("UPDATE read_book_his SET read_num=#{readNum} WHERE read_id =#{readId}")
    int updateReadBookHisBean(ReadBookHis readBookHis);

}
