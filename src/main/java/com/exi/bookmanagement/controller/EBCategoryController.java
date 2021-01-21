package com.exi.bookmanagement.controller;

import com.exi.bookmanagement.entity.EBookCategory;
import com.exi.bookmanagement.mapper.EBCategoryMapper;
import com.exi.bookmanagement.response.EBookCategoryResponse;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.controller.EBCategoryController
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/1/21 15:24
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/1/21    Fengsx     v1.0.0      修改原因
 */
@Api(tags = "电子图书分类管理")
@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/eBCategory")
public class EBCategoryController {
    @Autowired
    private EBCategoryMapper ebCategoryMapper;

    @ApiOperation("分页查询电子图书分类信息")
    @GetMapping(value = "/getEBookCategoryPage/{pageNum}/{pageSize}")
    public EBookCategoryResponse getEBookCategoryPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize){
        Page<EBookCategory> pageInfo = PageHelper.startPage(pageNum, pageSize);
        if (pageInfo.getPageNum() == 0 || pageInfo.getPageSize() == 0) {
            log.info("pageNum || pageSize 有值为空");
        }
        //并查询
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        List<EBookCategory> eBookCategoryList = ebCategoryMapper.getAllEBCategoryBean();
        // 如果在获取到数据之后就对数据进行转dto操作的话，会获取不到total数据，所以又定义了一个PageInfo类然后将数据进行属性复制，来获取数据
        PageInfo<EBookCategory> pageInfo1 = new PageInfo<>();
        BeanUtils.copyProperties(new PageInfo<>(eBookCategoryList), pageInfo1);
        log.info("封装后的 pageInfo:{}",pageInfo1);
        // 定义一个 response 把状态码和 message 加到 response 里面，不然前台会拒绝请求
        EBookCategoryResponse cBookCategoryResponse = new EBookCategoryResponse();
        cBookCategoryResponse.setCode(20000);
        cBookCategoryResponse.setMessage("返回 date 为 eBookCategoryList 的分页List");
        cBookCategoryResponse.setPageInfo(pageInfo1);
        return cBookCategoryResponse;
    }

    @ApiOperation("按名称查询电子图书分类信息")
    @GetMapping(value = "/getEBookCategoryLikeNameList/{eBookCategoryName}")
    public EBookCategoryResponse getEBookCategoryLikeNameList(@PathVariable("eBookCategoryName") String eBookCategoryName){
        List<EBookCategory> eBookCategoryList = ebCategoryMapper.getEBCategoryLikeNameList(eBookCategoryName);
        log.info("eBookCategoryList: {}",eBookCategoryList);
        EBookCategoryResponse cBookCategoryResponse = new EBookCategoryResponse();
        cBookCategoryResponse.setCode(20000);
        cBookCategoryResponse.setMessage("返回 date 为 eBookCategoryList 的按名字模糊查询");
        cBookCategoryResponse.seteBookCategoryList(eBookCategoryList);
        return cBookCategoryResponse;
    }

    @ApiOperation("获取所有电子图书分类信息")
    @GetMapping("/getAllEBookCategory")
    public EBookCategoryResponse getAllEBookCategory() {
        List<EBookCategory> eBookCategoryList=ebCategoryMapper.getAllEBCategoryBean();
        EBookCategoryResponse cBookCategoryResponse = new EBookCategoryResponse();
        cBookCategoryResponse.seteBookCategoryList(eBookCategoryList);
        cBookCategoryResponse.setCode(20000);
        cBookCategoryResponse.setMessage("返回 date 为 eBookCategoryList 全部数据");
        return cBookCategoryResponse;
    }

    @ApiOperation("按id查询电子图书分类信息")
    @GetMapping(value = "/getEBookCategoryById/{eBookCategoryId}")
    public EBookCategoryResponse getEBookCategoryById(@PathVariable("eBookCategoryId") Long eBookCategoryId) {
        EBookCategory eBookCategory =ebCategoryMapper.getOneEBCategoryById(eBookCategoryId);
        EBookCategoryResponse cBookCategoryResponse = new EBookCategoryResponse();
        cBookCategoryResponse.seteBookCategory(eBookCategory);
        cBookCategoryResponse.setCode(20000);
        cBookCategoryResponse.setMessage("返回 date 为 eBookCategory 的按id查询");
        return cBookCategoryResponse;
    }

    @ApiOperation("添加电子图书分类信息")
    @PostMapping("/addEBookCategory")
    public EBookCategoryResponse save(@RequestBody EBookCategory eBookCategory){
        EBookCategoryResponse cBookCategoryResponse = new EBookCategoryResponse();
        try {
            //返回的 id 总为 1 ,result 影响条数, 代码是返回自增主键的？
            int id = ebCategoryMapper.insertEBCategoryBean(eBookCategory);
            cBookCategoryResponse.seteBookCategory(eBookCategory);
            cBookCategoryResponse.setResult(id);
            cBookCategoryResponse.setCode(20000);
            cBookCategoryResponse.setMessage("返回 date 为 addEBookCategoryResponse");
            if(id != 0){
                return cBookCategoryResponse;
            }else{
                return null;
            }
        }catch (Exception e){
            log.info("添加电子图书分类信息出问题啦");
        }
        return cBookCategoryResponse;
    }

    @ApiOperation("更新电子图书分类信息")
    @PutMapping(value="/updateEBookCategory")
    public EBookCategoryResponse update(@RequestBody EBookCategory eBookCategory) {
        EBookCategoryResponse cBookCategoryResponse = new EBookCategoryResponse();
        try {
            int result = ebCategoryMapper.updateEBCategoryBean(eBookCategory);
            log.info("更新后的bookManager:{}",eBookCategory);
            cBookCategoryResponse.setResult(result);
            cBookCategoryResponse.seteBookCategory(eBookCategory);
            cBookCategoryResponse.setCode(20000);
            cBookCategoryResponse.setMessage("返回 date 为 updateEBookCategoryResponse");
            System.out.println("======" + result);
            if(result != 0){
                return cBookCategoryResponse;
            }else{
                return null;
            }
        }catch (Exception e){
            log.info("更新电子图书分类信息出问题啦");
        }
        return cBookCategoryResponse;
    }

    @ApiOperation("删除电子图书分类信息")
    @DeleteMapping(value="/deleteEBookCategory/{id}")
    public EBookCategoryResponse delete(@PathVariable("id") Long id) {
        EBookCategoryResponse cBookCategoryResponse = new EBookCategoryResponse();
        try {
            int result = ebCategoryMapper.deleteEBCategoryBean(id);
            cBookCategoryResponse.setResult(result);
            cBookCategoryResponse.setCode(20000);
            cBookCategoryResponse.setMessage("返回 date 为 deleteEBookCategoryBeanResponse");
            if (result != 0){
                return cBookCategoryResponse;
            }else {
                return null;
            }
        }catch (Exception e){
            log.info("删除电子图书分类信息出问题啦");
        }
        return cBookCategoryResponse;
    }

}
