package com.exi.bookmanagement;

import lombok.extern.slf4j.Slf4j;

/**
 *  Copyright: Copyright (c) 2021 Asiainfo
 *
 *  @ClassName: com.exi.bookmanagement.Slf4jTest
 *  @Description: 该类的功能描述
 * <p>
 *  @version: v1.0.0
 *  @author:   Fengsx
 *  @date: 1/8/21 7:09 PM
 * <p>
 *  Modification History:
 *  Date       Author    Version    Description
 * ----------------------------------------------------------
 *  1/8/21    Fengsx     v1.0.0      修改原因
 */

@Slf4j
public class Slf4jTest {

    public static void main(String[] args) {
        // 记录info级别的信息
        log.info("This is info message.");
        // 记录error级别的信息
        log.error("This is error message.");
        // 记录debug级别的信息
        log.debug("This is debug message.");
    }

}
