package com.exi.bookmanagement.mapper;

import com.exi.bookmanagement.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.mapper.CategoryMapper
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/1/21 11:13
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/1/21    Fengsx     v1.0.0      修改原因
 */
public interface CategoryMapper {
    
    @Select("SELECT * FROM category")
    @Results(id = "categoryMap", value = {
            @Result(property = "categoryId",  column = "category_id"),
            @Result(property = "bookId", column = "book_id"),
            @Result(property = "categoryName", column = "category_name")
    })
    List<Category> getAllCategoryBean();

    @Select("SELECT * FROM category WHERE category_id = #{categoryId}")
    @ResultMap(value = "categoryMap")
    Category getOneCategoryById(Long categoryId);


    @Insert("INSERT INTO category(book_id,category_name) " +
            "VALUES(#{bookId}, #{categoryName})")
    @Options(useGeneratedKeys = true, keyProperty = "category_id")
    int insertCategoryBean(Category category);

    @Update("UPDATE category SET book_id=#{bookId},category_name=#{categoryName}" +
            " WHERE category_id =#{categoryId}")
    int updateCategoryBean(Category category);

    @Delete("DELETE FROM category WHERE category_id =#{categoryId}")
    int deleteCategoryBean(Long categoryId);

    @Select("SELECT * FROM category WHERE category.category_name LIKE CONCAT('%',#{categoryName},'%')")
    @ResultMap(value = "categoryMap")
    List<Category> getCategoryLikeNameList(String categoryName);
}
