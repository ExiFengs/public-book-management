package com.exi.bookmanagement.service;

import java.text.ParseException;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.service.IReadBookService
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/1/27 14:32
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/1/27    Fengsx     v1.0.0      修改原因
 */
public interface IUpdateBorBookStateService {
    int updateBookState() throws ParseException;
}
