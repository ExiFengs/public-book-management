package com.exi.bookmanagement.service.impl;

import com.exi.bookmanagement.entity.Admin;
import com.exi.bookmanagement.entity.BookManager;
import com.exi.bookmanagement.entity.Reader;
import com.exi.bookmanagement.mapper.AdminMapper;
import com.exi.bookmanagement.mapper.BookManagerMapper;
import com.exi.bookmanagement.mapper.ReaderMapper;
import com.exi.bookmanagement.service.IUserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.service.impl.ReaderServiceImpl
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/1/17 15:47
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/1/17    Fengsx     v1.0.0      修改原因
 */
@Service
public class UserLoginServiceImpl implements IUserLoginService {
    @Autowired
    private ReaderMapper readerMapper;

    @Autowired
    private BookManagerMapper bookManagerMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Reader getUserByNameAndPassword(Reader reader) {
        //校验读者账号和密码
        String readerAccount = reader.getReaderAccount();
        String readerPassword = reader.getReaderPassword();
        Reader reader1 = readerMapper.selectReaderAccountAndPassword(readerAccount, readerPassword);

        return reader1;
    }

    @Override
    public BookManager getBookMangerByNameAndPassword(BookManager bookManager) {
        String readerAccount = bookManager.getReaderAccount();
        String readerPassword = bookManager.getReaderPassword();
        BookManager bookManager1 = bookManagerMapper.selectBookManagerAccountAndPassword(readerAccount, readerPassword);

        return bookManager1;
    }

    @Override
    public Admin getAdminByNameAndPassword(Admin admin) {
        String readerAccount = admin.getReaderAccount();
        String readerPassword = admin.getReaderPassword();
        Admin admin1 = adminMapper.selectAdminAccountAndPassword(readerAccount, readerPassword);

        return admin1;
    }
}
