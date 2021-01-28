package com.exi.bookmanagement.mapper;

import com.exi.bookmanagement.entity.Advertisement;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.mapper.AdMapper
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/1/19 11:37
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/1/19    Fengsx     v1.0.0      修改原因
 */
@CacheNamespace(blocking = true)
public interface AdMapper {

    @Select("SELECT * FROM advertisement")
    @Results(id = "adMap", value = {
            @Result(property = "adId",  column = "ad_id"),
            @Result(property = "adName", column = "ad_name"),
            @Result(property = "adDetails", column = "ad_details"),
            @Result(property = "adPicture", column = "ad_picture"),
    })
    List<Advertisement> getAllAdvertisementBean();

    @Select("SELECT * FROM advertisement WHERE ad_id = #{adId}")
    @ResultMap(value = "adMap")
    Advertisement getOneAdvertisementById(Long adId);


    @Insert("INSERT INTO advertisement(ad_name,ad_details,ad_picture) " +
            "VALUES(#{adName}, #{adDetails}, #{adPicture} )")
    @Options(useGeneratedKeys = true, keyProperty = "ad_id")
    int insertAdvertisementBean(Advertisement advertisement);

    @Update("UPDATE advertisement SET ad_name=#{adName},ad_details=#{adDetails}," +
            "ad_picture=#{adPicture} WHERE ad_id =#{adId}")
    int updateAdvertisementBean(Advertisement advertisement);

    @Delete("DELETE FROM advertisement WHERE ad_id =#{adId}")
    int deleteAdvertisementBean(Long adId);

    @Select("SELECT * FROM advertisement WHERE advertisement.ad_name LIKE CONCAT('%',#{adName},'%')")
    @ResultMap(value = "adMap")
    List<Advertisement> getAdLikeNameList(String adName);

}
