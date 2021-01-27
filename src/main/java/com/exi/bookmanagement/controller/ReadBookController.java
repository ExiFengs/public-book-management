package com.exi.bookmanagement.controller;

import com.alibaba.fastjson.JSON;
import com.exi.bookmanagement.entity.ReadBook;
import com.exi.bookmanagement.entity.ReadBookHis;
import com.exi.bookmanagement.mapper.ReadBookHisMapper;
import com.exi.bookmanagement.mapper.ReadBookMapper;
import com.exi.bookmanagement.response.ReadBookResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.controller.ReadBookController
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/1/27 14:34
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/1/27    Fengsx     v1.0.0      修改原因
 */
@Api(tags = "读者阅读电子书管理")
@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/readBook")
public class ReadBookController {
    @Autowired
    private ReadBookMapper readBookMapper;

    @Autowired
    private ReadBookHisMapper readBookHisMapper;

    //读者查看自己的阅读记录
    @ApiOperation("查询某个读者的阅读电子书记录")
    @GetMapping(value = "/getReaderReadEbookHis/{readerId}")
    public ReadBookResponse getReaderReadEbookHis(@PathVariable("readerId")Long readerId){
        ReadBookResponse readBookResponse = new ReadBookResponse();
        List<ReadBook> readBookMapperReadBookListByEbookIdAndReadId = readBookMapper.getReadBookListByEbookIdAndReadId(readerId);
        if (CollectionUtils.isEmpty(readBookMapperReadBookListByEbookIdAndReadId)){
            readBookResponse.setCode(20000);
            readBookResponse.setMessage("你没有阅读电子书的记录哦~");
            return readBookResponse;
        }
        readBookResponse.setReadBookList(readBookMapperReadBookListByEbookIdAndReadId);
        readBookResponse.setCode(20000);
        readBookResponse.setMessage("返回的是读者阅读电子书的记录");
        return readBookResponse;
    }

    @ApiOperation("后台查询全部电子书的阅读数据")
    @GetMapping(value = "/getAllReaderReadEbookHis")
    public ReadBookResponse getAllReaderReadEbookHis(){
        ReadBookResponse readBookResponse = new ReadBookResponse();
        List<ReadBook> readBookList = readBookMapper.getAllReadBookByEbookIdAndReadIdAndReaderId();
        readBookResponse.setReadBookList(readBookList);
        readBookResponse.setCode(20000);
        readBookResponse.setMessage("返回的是后台查询全部电子书的阅读数据");
        return readBookResponse;
    }

    //读者点击阅读电子书按钮
    @ApiOperation("生成或更新阅读记录")
    @GetMapping(value = "/updateReadEbookReadHis/{readerId}/{eBookId}")
    public ReadBookResponse updateReadEbookReadHis(@PathVariable("readerId")Long readerId, @PathVariable("eBookId")Long eBookId){
        ReadBookResponse readBookResponse = new ReadBookResponse();
        ReadBook readBook = new ReadBook();
        ReadBookHis readBookHis = new ReadBookHis();
        ReadBook readBookBeanByReaderId = readBookMapper.getReadBookBeanByReaderIdAndEbookId(readerId,eBookId);
        //判断是否是第一次阅读
        if (ObjectUtils.isEmpty(readBookBeanByReaderId)){
            try {
                readBook.setReaderId(readerId);
                readBook.setEBookId(eBookId);
                readBookMapper.insertReadBookBean(readBook);
                log.info("readBook:{}", JSON.toJSONString(readBook));
                //获取插入自增主键的 id
                long readId = readBook.getReadId();
                readBookHis.setReadId(readId);
                readBookHis.setReadNum(1L);
                readBookHisMapper.insertReadBookHisBean(readBookHis);
                log.info("readBookHis:{}",JSON.toJSONString(readBookHis));
                readBookResponse.setReadBook(readBook);
                readBookResponse.setReadBookHis(readBookHis);
                readBookResponse.setCode(20000);
                readBookResponse.setMessage("返回的是生成第一次的阅读记录");
                return readBookResponse;
            }catch (Exception e){
                readBookResponse.setCode(888888);
                readBookResponse.setMessage("生成第一次阅读记录出错啦");
                return readBookResponse;
            }
        }
        //更新阅读记录，num+1
        try {
            long readId = readBookBeanByReaderId.getReadId();
            ReadBookHis oneReadBookHisBean = readBookHisMapper.getOneReadBookHisBean(readId);
            oneReadBookHisBean.setReadNum(oneReadBookHisBean.getReadNum() + 1);
            int i = readBookHisMapper.updateReadBookHisBean(oneReadBookHisBean);
            if (i != 0){
                readBookResponse.setReadBook(readBookBeanByReaderId);
                readBookResponse.setReadBookHis(oneReadBookHisBean);
                readBookResponse.setCode(20000);
                readBookResponse.setMessage("返回的是更新后的阅读记录");
                return readBookResponse;
            }

        }catch (Exception e){
            readBookResponse.setMessage("更新阅读记录出错啦");
            readBookResponse.setCode(88888);
            return readBookResponse;
        }
        readBookResponse.setMessage("更新阅读记录出错啦");
        readBookResponse.setCode(88888);
        return readBookResponse;
    }
}
