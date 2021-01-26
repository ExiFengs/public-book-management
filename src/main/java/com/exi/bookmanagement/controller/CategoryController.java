package com.exi.bookmanagement.controller;

import com.alibaba.fastjson.JSON;
import com.exi.bookmanagement.entity.Book;
import com.exi.bookmanagement.entity.Category;
import com.exi.bookmanagement.entity.EBook;
import com.exi.bookmanagement.mapper.CategoryMapper;
import com.exi.bookmanagement.response.CategoryResponse;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.controller.CategoryController
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/1/21 11:28
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/1/21    Fengsx     v1.0.0      修改原因
 */
@Api(tags = "纸质图书分类管理")
@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/category")
public class CategoryController {
    @Autowired
    private CategoryMapper categoryMapper;

    @ApiOperation("分页查询纸质图书分类信息")
    @GetMapping(value = "/getCategoryPage/{pageNum}/{pageSize}")
    public CategoryResponse getCategoryPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize){
        Page<Category> pageInfo = PageHelper.startPage(pageNum, pageSize);
        if (pageInfo.getPageNum() == 0 || pageInfo.getPageSize() == 0) {
            log.info("pageNum || pageSize 有值为空");
        }
        //并查询
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        List<Category> categoryList = categoryMapper.getAllCategoryBean();
        // 如果在获取到数据之后就对数据进行转dto操作的话，会获取不到total数据，所以又定义了一个PageInfo类然后将数据进行属性复制，来获取数据
        PageInfo<Category> pageInfo1 = new PageInfo<>();
        BeanUtils.copyProperties(new PageInfo<>(categoryList), pageInfo1);
        log.info("封装后的 pageInfo:{}",pageInfo1);
        // 定义一个 response 把状态码和 message 加到 response 里面，不然前台会拒绝请求
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setCode(20000);
        categoryResponse.setMessage("返回 date 为 categoryList 的分页List");
        categoryResponse.setPageInfo(pageInfo1);
        return categoryResponse;
    }

    @ApiOperation("按分类id查询对应纸质书和电子书的信息")
    @GetMapping(value = "/getOneCategoryByBookCategoryId/{categoryId}")
    public CategoryResponse getOneCategoryByBookCategoryId(@PathVariable("categoryId") Long categoryId){
        CategoryResponse categoryResponse = new CategoryResponse();
        Category oneCategoryByBookCategoryId = categoryMapper.getOneCategoryByBookCategoryId(categoryId);
        List<Book> bookList = oneCategoryByBookCategoryId.getBookList();
        List<EBook> eBooks = oneCategoryByBookCategoryId.geteBookList();
        categoryResponse.setCategoryId(categoryId);
        categoryResponse.setBookList(bookList);
        categoryResponse.seteBookList(eBooks);
        categoryResponse.setCode(20000);
        categoryResponse.setMessage("返回的 data 为 bookList 和 ebookList 和 CategoryId");
        return categoryResponse;
    }

    @ApiOperation("按名称查询纸质图书分类信息")
    @GetMapping(value = "/getCategoryLikeNameList/{categoryName}")
    public CategoryResponse getCategoryLikeNameList(@PathVariable("categoryName") String categoryName){
        List<Category> categoryList = categoryMapper.getCategoryLikeNameList(categoryName);
        log.info("categoryList: {}",categoryList);
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setCode(20000);
        categoryResponse.setMessage("返回 date 为 categoryList 的按名字模糊查询");
        categoryResponse.setCategoryList(categoryList);
        return categoryResponse;
    }

    @ApiOperation("获取所有纸质图书分类信息")
    @GetMapping("/getAllCategory")
    public CategoryResponse getAllCategory() {
        List<Category> categoryList=categoryMapper.getAllCategoryBean();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setCategoryList(categoryList);
        categoryResponse.setCode(20000);
        categoryResponse.setMessage("返回 date 为 categoryList 全部数据");
        log.info("categoryResponse:{}", JSON.toJSONString(categoryResponse));
        return categoryResponse;
    }

    @ApiOperation("按id查询纸质图书分类信息")
    @GetMapping(value = "/getCategoryById/{categoryId}")
    public CategoryResponse getCategoryById(@PathVariable("categoryId") Long categoryId) {
        Category category =categoryMapper.getOneCategoryById(categoryId);
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setCategory(category);
        categoryResponse.setCode(20000);
        categoryResponse.setMessage("返回 date 为 category 的按id查询");
        return categoryResponse;
    }

    @ApiOperation("添加纸质图书分类信息")
    @PostMapping("/addCategory")
    public CategoryResponse save(@RequestBody Category category){
        CategoryResponse categoryResponse = new CategoryResponse();
        try {
            //返回的 id 总为 1 ,result 影响条数, 代码是返回自增主键的？
            int id = categoryMapper.insertCategoryBean(category);
            categoryResponse.setCategory(category);
            categoryResponse.setResult(id);
            categoryResponse.setCode(20000);
            categoryResponse.setMessage("返回 date 为 addCategoryResponse");
            if(id != 0){
                return categoryResponse;
            }else{
                return null;
            }
        }catch (Exception e){
            log.info("添加纸质图书分类信息出问题啦");
        }
        return categoryResponse;
    }

    @ApiOperation("更新纸质图书分类信息")
    @PutMapping(value="/updateCategory")
    public CategoryResponse update(@RequestBody Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();
        try {
            int result = categoryMapper.updateCategoryBean(category);
            log.info("更新后的bookManager:{}",category);
            categoryResponse.setResult(result);
            categoryResponse.setCategory(category);
            categoryResponse.setCode(20000);
            categoryResponse.setMessage("返回 date 为 updateCategoryResponse");
            System.out.println("======" + result);
            if(result != 0){
                return categoryResponse;
            }else{
                return null;
            }
        }catch (Exception e){
            log.info("更新纸质图书分类信息出问题啦");
        }
        return categoryResponse;
    }

    @ApiOperation("删除纸质图书分类信息")
    @DeleteMapping(value="/deleteCategory/{id}")
    public CategoryResponse delete(@PathVariable("id") Long id) {
        CategoryResponse categoryResponse = new CategoryResponse();
        try {
            Category oneCategoryById = categoryMapper.getOneCategoryByBookCategoryId(id);
            //如果此时电子书和纸质书都没使用到该类别，则该类别可以进行删除
            if (CollectionUtils.isEmpty(oneCategoryById.getBookList()) && CollectionUtils.isEmpty(oneCategoryById.geteBookList())){
                int result = categoryMapper.deleteCategoryBean(id);
                categoryResponse.setResult(result);
                categoryResponse.setCode(20000);
                categoryResponse.setMessage("返回 date 为 deleteCategoryBeanResponse");
                if (result != 0){
                    return categoryResponse;
                }else {
                    categoryResponse.setCode(88888);
                    categoryResponse.setMessage("删除纸质图书分类信息出问题啦");
                    return null;
                }
            }
            categoryResponse.setCode(88888);
            categoryResponse.setMessage("该图书分类还有图书关联，不能删除！");
            return categoryResponse;
        }catch (Exception e){
            log.info("删除纸质图书分类信息出问题啦");
        }
        categoryResponse.setCode(88888);
        categoryResponse.setMessage("删除纸质图书分类信息出问题啦");
        return categoryResponse;
    }

}
