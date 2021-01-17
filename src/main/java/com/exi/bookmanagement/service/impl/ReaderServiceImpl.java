package com.exi.bookmanagement.service.impl;

import com.exi.bookmanagement.entity.Reader;
import com.exi.bookmanagement.mapper.ReaderMapper;
import com.exi.bookmanagement.service.IReaderService;
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
public class ReaderServiceImpl implements IReaderService {
    @Autowired
    private ReaderMapper readerMapper;
    @Override
    public Reader getMemberByNicknameAndPassword(Reader reader) {
        //校验读者账号和密码
        String readerAccount = reader.getReaderAccount();
        String readerPassword = reader.getReaderPassword();
        Reader reader1 = readerMapper.selectReaderAccountAndPassword(readerAccount, readerPassword);

        return reader1;
    }
}
