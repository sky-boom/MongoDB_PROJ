package com.wzr.service;

import com.wzr.pojo.mongo.RentInfo;

import java.util.List;

public interface RentService
{
    //分页总查询
    List<RentInfo> queryComb(int pageStart,
                             String area,
                             int priceStart,
                             int priceEnd,
                             String roomType,
                             String position
    );

    //查询归属于area地区的租房个数
    List queryAreaCount();

    //查询area平均价格
    List queryPrice();

    //百度api将所有地区转化为经纬度
    // http://api.map.baidu.com/geocoder?address=地址&output=输出格式类型&key=用户密钥&city=城市名
    // http://api.map.baidu.com/geocoder?location=纬度,经度&output=输出格式类型&key=用户密钥
    //Qhvp16OHnpuAtul1ylfvWGVQKAiVZ3fE
    List<Object> getLngLat();
}
