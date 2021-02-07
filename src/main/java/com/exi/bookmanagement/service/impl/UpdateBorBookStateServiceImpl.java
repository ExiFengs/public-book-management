package com.exi.bookmanagement.service.impl;

import com.exi.bookmanagement.entity.BorrowBookHis;
import com.exi.bookmanagement.mapper.BorrowBookHisMapper;
import com.exi.bookmanagement.service.IUpdateBorBookStateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.service.impl.UpdateBorBookStateServiceImpl
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/1/29 17:16
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/1/29    Fengsx     v1.0.0      修改原因
 */
@Service
@Slf4j
public class UpdateBorBookStateServiceImpl implements IUpdateBorBookStateService {
    @Autowired
    private BorrowBookHisMapper borrowBookHisMapper;

    //每月每天凌晨3点触发
    @Scheduled(cron = "0 0 3 * * ?")
    @Override
    public int updateBookState() throws ParseException {
        log.info("执行定时更新借阅状态代码");
        List<BorrowBookHis> allBorrowBookHis = borrowBookHisMapper.getAllBorrowBookHis();
        for (BorrowBookHis b:
             allBorrowBookHis) {
            String expectGetBackTime = b.getExpectGetBackTime();
            Date time = new Date(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date expectGetBackTime1 = sdf.parse(expectGetBackTime);
            if (expectGetBackTime1.before(time) && b.getState() == 0){
                b.setState(3);
                b.setBooleanLate(1);
                b.setGetBackBookTime(null);
                try {
                    borrowBookHisMapper.updateBorrowBookHisBean(b);
                }catch (Exception e){
                    e.printStackTrace();
                    log.error("更新借阅状态出错啦");
                }
            }
        }
        return 1;
    }
}
