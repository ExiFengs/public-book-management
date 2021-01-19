package com.exi.bookmanagement.controller; 

import com.exi.bookmanagement.entity.BookManager;
import com.exi.bookmanagement.mapper.BookManagerMapper;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/** 
* BookMangerController Tester. 
* 
* @author <Authors name> 
* @since <pre>1月 18, 2021</pre> 
* @version 1.0 
*/ 
public class BookMangerControllerTest {

    @Autowired
    private BookManagerMapper bookManagerMapper;

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getBookManagersPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) 
* 
*/ 
@Test
public void testGetBookManagersPage() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getBookManagerLikeNameList(@PathVariable("readerName") String readerName) 
* 
*/ 
@Test
public void testGetBookManagerLikeNameList() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getUsers() 
* 
*/ 
@Test
public void testGetUsers() throws Exception { 
//TODO: Test goes here...
    List<BookManager> allBookManagerBean = bookManagerMapper.getAllBookManagerBean();
    System.out.println(allBookManagerBean);
} 

/** 
* 
* Method: getBookManagerById(@PathVariable("BookManagerId") Long bookManagerId) 
* 
*/ 
@Test
public void testGetBookManagerById() throws Exception { 
//TODO: Test goes here...
    BookManager bookManagerBeanById = bookManagerMapper.getOneBookManagerBeanById(2L);
    System.out.println(bookManagerBeanById);
} 

/** 
* 
* Method: save(@RequestBody BookManager bookManager) 
* 
*/ 
@Test
public void testSave() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: update(@RequestBody BookManager bookManager) 
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

/** 
* 
* Method: login(@RequestBody BookManager bookManager) 
* 
*/ 
@Test
public void testLogin() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getBookManagerInfo(@RequestBody HttpServletRequest request) 
* 
*/ 
@Test
public void testGetBookManagerInfo() throws Exception { 
//TODO: Test goes here... 
} 


} 
