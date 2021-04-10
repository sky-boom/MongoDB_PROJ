package com.wzr.controller;

import com.wzr.pojo.mongo.RentAreaCount;
import com.wzr.pojo.mongo.RentInfo;
import com.wzr.service.RentService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/mongo")
public class MongoController
{
    //Controller调用service层
    @Autowired
    private RentService rentService;

    @RequestMapping("/query")
    @ResponseBody
    public List<RentInfo> queryComb(int pageStart,
                                String area,
                                int priceStart,
                                int priceEnd,
                                String roomType,
                                String position
                                )
    {

        return rentService.queryComb(pageStart, area, priceStart, priceEnd, roomType, position);
    }

    @RequestMapping("/queryarea")
    @ResponseBody
    public List queryArea()
    {
        return rentService.queryAreaCount();
    }

    @RequestMapping("/queryprice")
    @ResponseBody
    public List queryAvgPrice()
    {
        return rentService.queryPrice();
    }

    @RequestMapping("/getLngLat")
    @ResponseBody
    public List<Object> getLngLat()
    {
        return rentService.getLngLat();
    }

}
