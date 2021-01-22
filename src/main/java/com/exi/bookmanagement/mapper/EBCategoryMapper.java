package com.exi.bookmanagement.mapper;

import com.exi.bookmanagement.entity.EBookCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.mapper.EBCategoryMapper
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/1/21 14:48
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/1/21    Fengsx     v1.0.0      修改原因
 */
public interface EBCategoryMapper {

    
    @Select("SELECT * FROM e_book_category")
    @Results(id = "eBCategoryMap", value = {
            @Result(property = "eCategoryId",  column = "e_category_id"),
            @Result(property = "eCategoryName", column = "e_category_name")
    })
    List<EBookCategory> getAllEBCategoryBean();

    @Select("SELECT * FROM e_book_category WHERE e_category_id = #{eCategoryId}")
    @ResultMap(value = "eBCategoryMap")
    EBookCategory getOneEBCategoryById(Long eCategoryId);



    @Insert("INSERT INTO e_book_category(e_category_name) " +
            "VALUES(#{eCategoryName})")
    @Options(useGeneratedKeys = true, keyProperty = "e_category_id")
    int insertEBCategoryBean(EBookCategory eBookCategory);

    @Update("UPDATE e_book_category SET e_category_name=#{eCategoryName}" +
            " WHERE e_category_id =#{eCategoryId}")
    int updateEBCategoryBean(EBookCategory eBookCategory);

    @Delete("DELETE FROM e_book_category WHERE e_category_id =#{eCategoryId}")
    int deleteEBCategoryBean(Long eCategoryId);

    @Select("SELECT * FROM e_book_category WHERE e_book_category.e_category_name LIKE CONCAT('%',#{eCategoryName},'%')")
    @ResultMap(value = "eBCategoryMap")
    List<EBookCategory> getEBCategoryLikeNameList(String eCategoryName);
}
