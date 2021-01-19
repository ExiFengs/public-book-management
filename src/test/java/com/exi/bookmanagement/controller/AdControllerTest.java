package com.exi.bookmanagement.controller;

import com.exi.bookmanagement.entity.Advertisement;
import com.exi.bookmanagement.mapper.AdMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/** 
* AdController Tester. 
* 
* @author <Authors name> 
* @since <pre>1月 19, 2021</pre> 
* @version 1.0 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AdControllerTest { 
    @Autowired
    private AdMapper adMapper;

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getAdsPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) 
* 
*/ 
@Test
public void testGetAdsPage() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getAdLikeNameList(@PathVariable("adName") String adName) 
* 
*/ 
@Test
public void testGetAdLikeNameList() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getAds() 
* 
*/ 
@Test
public void testGetAds() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getAdById(@PathVariable("adId") Long adId) 
* 
*/ 
@Test
public void testGetAdById() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: save(@RequestBody Advertisement advertisement) 
* 
*/ 
@Test
public void testSave() throws Exception { 
//TODO: Test goes here...
    Advertisement advertisement = new Advertisement();
    advertisement.setAdId(1L);
    advertisement.setAdDetails("这是第一条广告");
    advertisement.setAdName("one");
    advertisement.setAdPicture("/test");
    int i = adMapper.insertAdvertisementBean(advertisement);
    log.info("返回值为：：：{}", i);
} 

/** 
* 
* Method: update(@RequestBody Advertisement advertisement) 
* 
*/ 
@Test
public void testUpdate() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: delete(@PathVariable("id") Long id) 
* 
*/ 
@Test
public void testDelete() throws Exception { 
//TODO: Test goes here... 
} 


} 
