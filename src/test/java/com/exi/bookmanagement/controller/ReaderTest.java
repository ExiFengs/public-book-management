package com.exi.bookmanagement.controller;

import com.alibaba.fastjson.JSON;
import com.exi.bookmanagement.entity.Book;
import com.exi.bookmanagement.entity.Category;
import com.exi.bookmanagement.entity.Reader;
import com.exi.bookmanagement.mapper.BookMapper;
import com.exi.bookmanagement.mapper.CategoryMapper;
import com.exi.bookmanagement.mapper.EBookMapper;
import com.exi.bookmanagement.mapper.ReaderMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *  Copyright: Copyright (c) 2021 Asiainfo
 *
 *  @ClassName: com.exi.bookmanagement.controller.ReaderTest
 *  @Description: 该类的功能描述
 * <p>
 *  @version: v1.0.0
 *  @author:   Fengsx
 *  @date: 1/8/21 3:46 PM
 * <p>
 *  Modification History:
 *  Date       Author    Version    Description
 * ----------------------------------------------------------
 *  1/8/21    Fengsx     v1.0.0      修改原因
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ReaderTest {
    @Autowired
    private ReaderMapper readerMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private EBookMapper eBookMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    Logger logger = LoggerFactory.getLogger(ReaderTest.class);

    @Test
    public void getBookBeanByCategory() {

        List<Book> bookBeanByCategory = bookMapper.getBookBeanByCategory(1L);
        Category categoryByIdForBook = categoryMapper.getCategoryByIdForBook(1L);

        List<Book> allBookBean = bookMapper.getAllBookBean();
        log.info("allBookBean :{}", JSON.toJSONString(allBookBean));
//        log.info("categoryByIdForBook :{}",JSON.toJSONString(categoryByIdForBook));

    }
    @Test
    public void testQuery() throws Exception {
        List<Reader> readerList = readerMapper.getAllReaderBean();
        log.info(readerList.toString());

    }

    @Test
    public void testSelectById() throws Exception{
        Reader reader = readerMapper.getOneReaderBean(100L);
        log.info("reader:{}",reader);
    }

    @Test
    public void testUpdate() throws Exception {
        Reader reader = readerMapper.getOneReaderBean(1L);
        System.out.println(reader.toString());
        reader.setReaderName("neo");
        readerMapper.updateReaderBean(reader);
        System.out.println(reader.toString());
    }

    @Test
    public void testDelete() throws Exception{
        readerMapper.deleteReaderBean(1L);
        testQuery();
    }

    @Test
    public void testInsert() throws Exception{
        Reader reader = new Reader();
        reader.setReaderName("嗯啊");
        reader.setReaderAccount("嗯啊");
        reader.setReaderPassword("123");
        reader.setReaderSex("girl");
        Date time = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String current = sdf.format(time);
        reader.setRegistrationTime(current);
        reader.setRoleId(333L);
        int i = readerMapper.insertReaderBean(reader);
        log.error(i + "============");
        testQuery();
    }
}
