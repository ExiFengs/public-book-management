package com.exi.bookmanagement.mapper;

import com.exi.bookmanagement.entity.AppleEBook;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.mapper.AppleEBookMapper
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/2/1 17:52
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/2/1    Fengsx     v1.0.0      修改原因
 */
public interface AppleEBookMapper {
    @Select("SELECT * FROM apple_e_book")
    @Results(id = "EbookMap", value = {
            @Result(property = "eBookId",  column = "e_book_id"),
            @Result(property = "eBookAuthor", column = "e_book_author"),
            @Result(property = "eBookName", column = "e_book_name"),
            @Result(property = "eBookPicture", column = "e_book_picture"),
            @Result(property = "eBookIsbn", column = "e_book_isbn"),
            @Result(property = "eBookIntro", column = "e_book_intro"),
            @Result(property = "eBookPress", column = "e_book_press"),
            @Result(property = "eBookFileUrl", column = "e_book_file_url"),
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "state", column = "state"),
            @Result(property = "readerId", column = "reader_id"),
            @Result(column="category_id",property="category",
                    one=@One(select="com.exi.bookmanagement.mapper.CategoryMapper.getOneCategoryById")),
            @Result(column="reader_id",property="reader",
                    one=@One(select="com.exi.bookmanagement.mapper.ReaderMapper.getOneReaderBean"))
    })
    List<AppleEBook> getAllEBookBean();

    @Select("SELECT * FROM apple_e_book WHERE reader_Id = #{readerId}")
    @ResultMap(value = "EbookMap")
    List<AppleEBook> getAppleEBookById(Long readerId);


    @Select("SELECT * FROM apple_e_book WHERE e_book_id = #{eBookId}")
    @ResultMap(value = "EbookMap")
    AppleEBook getOneEBookBeanById(Long eBookId);

    @Select("SELECT * FROM apple_e_book WHERE category_id = #{category_id}")
    @ResultMap(value = "EbookMap")
    AppleEBook getOneEBookBeanByCategotyId(Long category_id);

    @Insert("INSERT INTO apple_e_book(e_book_author, e_book_name, e_book_picture, e_book_isbn, e_book_intro, e_book_press, " +
            "e_book_file_url, state, reader_id, category_id) " +
            "VALUES(#{eBookAuthor}, #{eBookName}, #{eBookPicture}, #{eBookIsbn}, #{eBookIntro}, #{eBookPress}, #{eBookFileUrl}," +
            " #{state}, #{readerId}, #{categoryId})")
    @Options(useGeneratedKeys = true, keyProperty = "e_book_id")
    int insertEBookBean(AppleEBook eBook);

    @Update("UPDATE apple_e_book SET e_book_author=#{eBookAuthor},e_book_name=#{eBookName}," +
            "e_book_picture=#{eBookPicture}, e_book_isbn=#{eBookIsbn}, e_book_intro=#{eBookIntro}," +
            "e_book_press=#{eBookPress}, e_book_file_url=#{eBookFileUrl}, category_id=#{categoryId}" +
            " WHERE e_book_id =#{eBookId}")
    int updateEBookBean(AppleEBook eBook);

    @Delete("DELETE FROM apple_e_book WHERE e_book_id =#{eBookId}")
    int deleteEBookBean(Long eBookId);

    @Select("SELECT * FROM apple_e_book WHERE apple_e_book.e_book_name LIKE CONCAT('%',#{eBookName},'%')")
    @ResultMap(value = "EbookMap")
    List<AppleEBook> getEBookLikeNameList(String eBookName);

    @Update("UPDATE apple_e_book SET state=#{state} WHERE e_book_id = #{eBookId}")
    int updateEBookBeanByState(AppleEBook appleBook);
}
