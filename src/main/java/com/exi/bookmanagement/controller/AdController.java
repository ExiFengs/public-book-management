package com.exi.bookmanagement.controller;

import com.exi.bookmanagement.entity.Advertisement;
import com.exi.bookmanagement.mapper.AdMapper;
import com.exi.bookmanagement.response.AdResponse;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.controller.AdController
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/1/19 11:55
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/1/19    Fengsx     v1.0.0      修改原因
 */
@Api(tags = "广告栏管理")
@RestController
@CrossOrigin
@Transactional
@Slf4j
@RequestMapping(value = "/adManagement")
public class AdController {
    @Autowired
    private AdMapper adMapper;

    @ApiOperation("分页查询广告栏信息")
    @GetMapping(value = "/getAdsPage/{pageNum}/{pageSize}")
    public AdResponse getAdsPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize){
        Page<Advertisement> pageInfo = PageHelper.startPage(pageNum, pageSize);
        if (pageInfo.getPageNum() == 0 || pageInfo.getPageSize() == 0) {
            log.info("pageNum || pageSize 有值为空");
        }
        //并查询
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        List<Advertisement> advertisementList = adMapper.getAllAdvertisementBean();
        // 如果在获取到数据之后就对数据进行转dto操作的话，会获取不到total数据，所以又定义了一个PageInfo类然后将数据进行属性复制，来获取数据
        PageInfo<Advertisement> pageInfo1 = new PageInfo<>();
        BeanUtils.copyProperties(new PageInfo<>(advertisementList), pageInfo1);
        log.info("封装后的 pageInfo:{}",pageInfo1);
        // 定义一个 response 把状态码和 message 加到 response 里面，不然前台会拒绝请求
        AdResponse adResponse = new AdResponse();
        adResponse.setCode(20000);
        adResponse.setMessage("返回 date 为 advertisementList 的分页List");
        adResponse.setPageInfo(pageInfo1);
        return adResponse;
    }

    @ApiOperation("按名称查询广告栏信息")
    @GetMapping(value = "/getAdLikeNameList/{adName}")
    public AdResponse getAdLikeNameList(@PathVariable("adName") String adName){
        List<Advertisement> advertisementList = adMapper.getAdLikeNameList(adName);
        log.info("advertisementList: {}",advertisementList);
        AdResponse adResponse = new AdResponse();
        adResponse.setCode(20000);
        adResponse.setMessage("返回 date 为 advertisementList 的按名字模糊查询");
        adResponse.setAdvertisementList(advertisementList);
        return adResponse;
    }

    @ApiOperation("获取所有广告栏信息")
    @GetMapping("/getAds")
    public AdResponse getAds() {
        List<Advertisement> advertisementList=adMapper.getAllAdvertisementBean();
        AdResponse adResponse = new AdResponse();
        adResponse.setAdvertisementList(advertisementList);
        adResponse.setCode(20000);
        adResponse.setMessage("返回 date 为 advertisementList 全部数据");
        return adResponse;
    }

    @ApiOperation("按id查询广告栏信息")
    @GetMapping(value = "/getAdById/{adId}")
    public AdResponse getAdById(@PathVariable("adId") Long adId) {
        Advertisement advertisement =adMapper.getOneAdvertisementById(adId);
        AdResponse adResponse = new AdResponse();
        adResponse.setAdvertisement(advertisement);
        adResponse.setCode(20000);
        adResponse.setMessage("返回 date 为 advertisement 的按id查询");
        return adResponse;
    }

    @ApiOperation("添加广告栏信息")
    @PostMapping("/addAd")
    public AdResponse save(@RequestBody Advertisement advertisement){
        AdResponse adResponse = new AdResponse();
        try {
            //返回的 id 总为 1 ,result 影响条数, 代码是返回自增主键的？
            int id = adMapper.insertAdvertisementBean(advertisement);
            adResponse.setAdvertisement(advertisement);
            adResponse.setResult(id);
            adResponse.setCode(20000);
            adResponse.setMessage("返回 date 为 addAdResponse");
            if(id != 0){
                return adResponse;
            }else{
                return null;
            }
        }catch (Exception e){
            log.info("添加广告栏信息出问题啦");
        }
        return adResponse;
    }

    @ApiOperation("更新广告栏信息")
    @PutMapping(value="/updateAd")
    public AdResponse update(@RequestBody Advertisement advertisement) {
        AdResponse adResponse = new AdResponse();
        try {
            int result = adMapper.updateAdvertisementBean(advertisement);
            log.info("更新后的bookManager:{}",advertisement);
            adResponse.setResult(result);
            adResponse.setAdvertisement(advertisement);
            adResponse.setCode(20000);
            adResponse.setMessage("返回 date 为 updateAdvertisementResponse");
            System.out.println("======" + result);
            if(result != 0){
                return adResponse;
            }else{
                return null;
            }
        }catch (Exception e){
            log.info("更新广告栏信息出问题啦");
        }
        return adResponse;
    }

    @ApiOperation("删除广告栏信息")
    @DeleteMapping(value="/deleteAd/{id}")
    public AdResponse delete(@PathVariable("id") Long id) {
        AdResponse adResponse = new AdResponse();
        try {
            int result = adMapper.deleteAdvertisementBean(id);
            adResponse.setResult(result);
            adResponse.setCode(20000);
            adResponse.setMessage("返回 date 为 deleteAdvertisementBeanResponse");
            if (result != 0){
                return adResponse;
            }else {
                return null;
            }
        }catch (Exception e){
            log.info("删除广告栏信息出问题啦");
        }
        return adResponse;
    }

    /**
     * 图片上传
     */
    @ApiOperation("图片上传")
    @PostMapping("/uploadImg")
    public AdResponse uploadAvatarHandler(@RequestParam("uploadFile") MultipartFile uploadFile ) throws IOException {
        AdResponse adResponse = new AdResponse();
        //获得项目的类路径
        String path = ResourceUtils.getURL("classpath:").getPath();
        //空文件夹在编译时不会打包进入target中
        File uploadDir = new File(path+"/static/img/ad");
        if (!uploadDir.exists()) {
            System.out.println("上传图片路径不存在，正在创建...");
            uploadDir.mkdirs();
            System.out.println(uploadDir.getPath());
        }
        if ( uploadFile != null) {
            //获得上传文件的文件名
            String oldName = uploadFile.getOriginalFilename();
            System.out.println("[上传的文件名]：" + oldName);
            //我的文件保存在static目录下的/static/img/ad/
            File avatar = new File(path + "/static/img/ad/" , oldName);
            try {
                //保存图片
                uploadFile.transferTo(avatar);
                //返回成功结果，附带文件的相对路径
                //http://localhost:8888/bookManagement/img/ad/WechatIMG33.jpg
                adResponse.setCode(20000);
                adResponse.setMessage("返回 date 为 上传图片文件的绝对路径");
                adResponse.setFileName("/img/ad/"+oldName);
                return adResponse;
            }catch (IOException e) {
                e.printStackTrace();
                adResponse.setCode(888888);
                adResponse.setMessage("上传失败");
                return adResponse;
            }
        }else {
            System.out.println("上传的文件为空");
            adResponse.setCode(888888);
            adResponse.setMessage("文件传输错误");
            return adResponse;
        }

    }
}
