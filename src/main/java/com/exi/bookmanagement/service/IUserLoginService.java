package com.exi.bookmanagement.service;

import com.exi.bookmanagement.entity.BookManager;
import com.exi.bookmanagement.entity.Reader;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.service.IReaderService
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/1/17 15:44
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/1/17    Fengsx     v1.0.0      修改原因
 */
public interface IUserLoginService {
    Reader getUserByNameAndPassword(Reader reader);

    BookManager getBookMangerByNameAndPassword(BookManager bookManager);

}
