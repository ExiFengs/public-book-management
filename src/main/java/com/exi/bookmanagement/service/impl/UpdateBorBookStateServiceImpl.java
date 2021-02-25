package com.exi.bookmanagement.service.impl;

import com.exi.bookmanagement.entity.Book;
import com.exi.bookmanagement.entity.BorrowBookHis;
import com.exi.bookmanagement.mapper.BookMapper;
import com.exi.bookmanagement.mapper.BorrowBookHisMapper;
import com.exi.bookmanagement.mapper.BorrowBookMapper;
import com.exi.bookmanagement.service.IUpdateBorBookStateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.service.impl.UpdateBorBookStateServiceImpl
 * @Description: 定时调度任务
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
    private BookMapper bookMapper;

    @Autowired
    private BorrowBookMapper borrowBookMapper;

    @Autowired
    private BorrowBookHisMapper borrowBookHisMapper;

    /**
     * @Author fengsx
     * @Description //每月每天凌晨3点触发,更新读者借书后是否有逾期，每十秒执行一次（0/10 * * * * ? ）
     * @Date  10:09
     * @Param []
     * @return int
     **/
    @Scheduled(cron = "0 0 3 * * ?")
    @Override
    public int updateBookState() throws ParseException {
        log.info("执行定时更新借阅状态job");
        //用户逾期了预期还书时间，更新状态，状态为 4：已借书
        List<BorrowBookHis> allBorrowBookHis = borrowBookHisMapper.getStateForFour();
        for (BorrowBookHis b:
             allBorrowBookHis) {
            String expectGetBackTime = b.getExpectGetBackTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date expectGetBackTime1 = sdf.parse(expectGetBackTime);
            //当前时间
            Date time = new Date(System.currentTimeMillis());
            if (expectGetBackTime1.before(time)){
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

    @Override
    @Scheduled(cron = "0 0 3 * * ?")
    public void updateSubscribeState() throws ParseException {
        //用户预约图书有一天的期限，过了一天就施放图书资源，状态为 0：已预约
        log.info("执行用户预约图书定时 job");
        List<BorrowBookHis> stateForOne = borrowBookHisMapper.getStateForOne();
        for (BorrowBookHis b:
             stateForOne) {
            String subscribeTime = b.getSubscribeTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date subscribeTime1 = sdf.parse(subscribeTime);
            //预约时间加一天
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(subscribeTime1);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            Date tomorrow = calendar.getTime();
            //当前时间
            Date time = new Date(System.currentTimeMillis());
            if (tomorrow.before(time)){
                //6: '预约逾期'
                b.setState(6);
                //把库存施放出来
                Long bookId = borrowBookMapper.getBookByBorBookId(b.getBorBookId());
                Book oneBookBeanById = bookMapper.getOneBookBeanById(bookId);
                oneBookBeanById.setBookRepertory(oneBookBeanById.getBookRepertory() + b.getBorBookNum());
                try {
                    bookMapper.updateBookBean(oneBookBeanById);
                    borrowBookHisMapper.updateBorrowBookHisBean(b);
                }catch (Exception e){
                    e.printStackTrace();
                    log.error("更新用户预约图书状态出错啦");
                }
            }
        }

    }
}
