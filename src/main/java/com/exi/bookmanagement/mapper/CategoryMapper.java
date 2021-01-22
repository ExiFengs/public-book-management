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
            @Result(property = "categoryName", column = "category_name")
    })
    List<Category> getAllCategoryBean();

    @Select("SELECT * FROM category WHERE category_id = #{categoryId}")
    @ResultMap(value = "categoryMap")
    Category getOneCategoryById(Long categoryId);


    //一个类别对应多本纸质书和电子书
    @Select("SELECT * FROM category WHERE category_id = #{categoryId}")
    @Results({
            @Result(property = "categoryId",  column = "category_id"),
            @Result(property="categoryName",column="category_name"),
            @Result(property="bookList",column="category_id",javaType=List.class,
                    many=@Many(select="com.exi.bookmanagement.mapper.BookMapper.getBookBeanByCategory")),
            @Result(property="eBookList",column="category_id",javaType=List.class,
                    many=@Many(select="com.exi.bookmanagement.mapper.EBookMapper.getEBookBeanByCategory"))
    })
    Category getCategoryByIdForBook(Long categoryId);

    @Insert("INSERT INTO category(category_name) " +
            "VALUES(#{categoryName})")
    @Options(useGeneratedKeys = true, keyProperty = "category_id")
    int insertCategoryBean(Category category);

    @Update("UPDATE category SET category_name=#{categoryName}" +
            " WHERE category_id =#{categoryId}")
    int updateCategoryBean(Category category);

    @Delete("DELETE FROM category WHERE category_id =#{categoryId}")
    int deleteCategoryBean(Long categoryId);

    @Select("SELECT * FROM category WHERE category.category_name LIKE CONCAT('%',#{categoryName},'%')")
    @ResultMap(value = "categoryMap")
    List<Category> getCategoryLikeNameList(String categoryName);
}
