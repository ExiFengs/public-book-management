package com.exi.bookmanagement.controller;

import com.exi.bookmanagement.entity.EBook;
import com.exi.bookmanagement.mapper.EBookMapper;
import com.exi.bookmanagement.response.EBookResponse;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.controller.EBookMapper
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/1/20 20:29
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/1/20    Fengsx     v1.0.0      修改原因
 */
@Api(tags = "电子图书管理")
@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/eBook")
public class EBookController {
    @Autowired
    private EBookMapper eBookMapper;

    @ApiOperation("分页查询电子图书信息")
    @GetMapping(value = "/getEbooksPage/{pageNum}/{pageSize}")
    public EBookResponse getEbooksPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize){
        Page<EBook> pageInfo = PageHelper.startPage(pageNum, pageSize);
        if (pageInfo.getPageNum() == 0 || pageInfo.getPageSize() == 0) {
            log.info("pageNum || pageSize 有值为空");
        }
        //并查询
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        List<EBook> eBookList = eBookMapper.getAllEBookBean();
        // 如果在获取到数据之后就对数据进行转dto操作的话，会获取不到total数据，所以又定义了一个PageInfo类然后将数据进行属性复制，来获取数据
        PageInfo<EBook> pageInfo1 = new PageInfo<>();
        BeanUtils.copyProperties(new PageInfo<>(eBookList), pageInfo1);
        log.info("封装后的 pageInfo:{}",pageInfo1);
        // 定义一个 response 把状态码和 message 加到 response 里面，不然前台会拒绝请求
        EBookResponse eBookResponse = new EBookResponse();
        eBookResponse.setCode(20000);
        eBookResponse.setMessage("返回 date 为 eBookList 的分页List");
        eBookResponse.setPageInfo(pageInfo1);
        return eBookResponse;
    }

    @ApiOperation("按名称查询电子图书信息")
    @GetMapping(value = "/getEBookLikeNameList/{eBookName}")
    public EBookResponse getEBookLikeNameList(@PathVariable("eBookName") String eBookName){
        List<EBook> eBookList = eBookMapper.getEBookLikeNameList(eBookName);
        log.info("eBookList: {}",eBookList);
        EBookResponse eBookResponse = new EBookResponse();
        eBookResponse.setCode(20000);
        eBookResponse.setMessage("返回 date 为 eBookList 的按名字模糊查询");
        eBookResponse.seteBookList(eBookList);
        return eBookResponse;
    }

    @ApiOperation("获取所有电子图书信息")
    @GetMapping("/getEBooks")
    public EBookResponse getEBooks() {
        List<EBook> eBookList=eBookMapper.getAllEBookBean();
        EBookResponse eBookResponse = new EBookResponse();
        eBookResponse.seteBookList(eBookList);
        eBookResponse.setCode(20000);
        eBookResponse.setMessage("返回 date 为 eBookList 全部数据");
        return eBookResponse;
    }

    @ApiOperation("按id查询电子图书信息")
    @GetMapping(value = "/getEBookById/{eBookId}")
    public EBookResponse getEBookById(@PathVariable("eBookId") Long eBookId) {
        EBook ebook =eBookMapper.getOneEBookBeanById(eBookId);
        EBookResponse eBookResponse = new EBookResponse();
        eBookResponse.seteBook(ebook);
        eBookResponse.setCode(20000);
        eBookResponse.setMessage("返回 date 为 ebook 的按id查询");
        return eBookResponse;
    }

    @ApiOperation("添加电子图书信息")
    @PostMapping("/addEBook")
    public EBookResponse save(@RequestBody EBook ebook){
        EBookResponse eBookResponse = new EBookResponse();
        try {
            //返回的 id 总为 1 ,result 影响条数, 代码是返回自增主键的？
            int id = eBookMapper.insertEBookBean(ebook);
            eBookResponse.seteBook(ebook);
            eBookResponse.setResult(id);
            eBookResponse.setCode(20000);
            eBookResponse.setMessage("返回 date 为 eBookResponse");
            if(id != 0){
                return eBookResponse;
            }else{
                return null;
            }
        }catch (Exception e){
            log.info("添加电子图书信息出问题啦");
        }
        return eBookResponse;
    }

    @ApiOperation("更新电子图书信息")
    @PutMapping(value="/updateEBook")
    public EBookResponse update(@RequestBody EBook ebook) {
        EBookResponse eBookResponse = new EBookResponse();
        try {
            int result = eBookMapper.updateEBookBean(ebook);
            log.info("ebook:{}",ebook);
            eBookResponse.setResult(result);
            eBookResponse.seteBook(ebook);
            eBookResponse.setCode(20000);
            eBookResponse.setMessage("返回 date 为 updateEBookResponse");
            System.out.println("======" + result);
            if(result != 0){
                return eBookResponse;
            }else{
                return null;
            }
        }catch (Exception e){
            log.info("更新电子图书信息出问题啦");
        }
        return eBookResponse;
    }

    @ApiOperation("删除电子图书信息")
    @DeleteMapping(value="/deleteEBook/{id}")
    public EBookResponse delete(@PathVariable("id") Long id) {
        EBookResponse eBookResponse = new EBookResponse();
        try {
            int result = eBookMapper.deleteEBookBean(id);
            eBookResponse.setResult(result);
            eBookResponse.setCode(20000);
            eBookResponse.setMessage("返回 date 为 deleteEBookBeanResponse");
            if (result != 0){
                return eBookResponse;
            }else {
                return null;
            }
        }catch (Exception e){
            log.info("删除电子图书信息出问题啦");
        }
        return eBookResponse;
    }

    /**
     * 图片上传
     */
    @ApiOperation("图片上传")
    @PostMapping("/uploadEBookImg")
    public EBookResponse uploadEBookImg(@RequestParam("uploadFile") MultipartFile uploadFile ) throws IOException {
        EBookResponse eBookResponse = new EBookResponse();
        //获得项目的类路径
        String path = ResourceUtils.getURL("classpath:").getPath();
        //空文件夹在编译时不会打包进入target中
        File uploadDir = new File(path+"/static/img/ebook");
        if (!uploadDir.exists()) {
            System.out.println("上传图片路径不存在，正在创建...");
            uploadDir.mkdirs();
            System.out.println(uploadDir.getPath());
        }
        if ( uploadFile != null) {
            //获得上传文件的文件名
            String oldName = uploadFile.getOriginalFilename();
            System.out.println("[上传的文件名]：" + oldName);
            File avatar = new File(path + "/static/img/ebook/" , oldName);
            try {
                //保存图片
                uploadFile.transferTo(avatar);
                //返回成功结果，附带文件的相对路径
                //http://localhost:8888/bookManagement/img/ad/WechatIMG33.jpg
                eBookResponse.setCode(20000);
                eBookResponse.setMessage("返回 date 为 上传图片文件的相对路径");
                eBookResponse.setFileName("/img/ebook/"+oldName);
                return eBookResponse;
            }catch (IOException e) {
                e.printStackTrace();
                eBookResponse.setCode(888888);
                eBookResponse.setMessage("上传失败");
                return eBookResponse;
            }
        }else {
            System.out.println("上传的文件为空");
            eBookResponse.setCode(888888);
            eBookResponse.setMessage("文件传输错误");
            return eBookResponse;
        }

    }
}
