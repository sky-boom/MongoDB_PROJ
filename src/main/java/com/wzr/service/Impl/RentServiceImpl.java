package com.wzr.service.Impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.wzr.pojo.LngLat;
import com.wzr.pojo.mongo.Location;
import com.wzr.pojo.mongo.RentAreaCount;
import com.wzr.pojo.mongo.RentInfo;
import com.wzr.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class RentServiceImpl implements RentService
{
    //service层调用dao层
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<RentInfo> queryComb(int pageStart,
                                    String area,
                                    int priceStart,
                                    int priceEnd,
                                    String roomType,
                                    String position
    )
    {
        System.out.println("起始页:" + pageStart);
        System.out.println("地区:" + area);
        System.out.println("价格开始:" + priceStart);
        System.out.println("价格结束:" + priceEnd);
        System.out.println("房型:" + roomType);
        System.out.println("朝向:" + position);

        Criteria c1 = null;
        Criteria c2 = null;
        Criteria c3 = null;
        Criteria c4 = null;

        ArrayList<Criteria> clist = new ArrayList<Criteria>();
        //地区
        if (!"".equals(area)){
            c1 = Criteria.where("area").is(area);
            clist.add(c1);
        }
        //价格，默认执行
        c2 = Criteria.where("price").gte(priceStart).lte(priceEnd);
        clist.add(c2);

        //房型
        if(!"".equals(roomType))
        {
            c3 = Criteria.where("roomType").regex("^" + roomType + ".*");
            clist.add(c3);
        }

        //朝向
        if(!"".equals(position))
        {
            c4 = Criteria.where("position").is(position);
            clist.add(c4);
        }

        Criteria[] arr = new Criteria[clist.size()];
        clist.toArray(arr);
        Criteria criteria = new Criteria().andOperator(arr);

        Query query = new Query(criteria);
        query.skip(pageStart);
        query.limit(10);

        List<RentInfo> list = mongoTemplate.find(query, RentInfo.class);
//        System.out.println(list);
        for (RentInfo rentinfo : list)
        {
            System.out.println(rentinfo);
        }
        return list;
    }

    @Override
    public List queryAreaCount()
    {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.group("area").count().as("count") //分组字段
//                Aggregation.group("area").sum("1").as("area")
        );
        AggregationResults<BasicDBObject> output = mongoTemplate.aggregate(agg,
                                                                           "RentInfo",
                                                                           BasicDBObject.class);
        List<BasicDBObject> list = output.getMappedResults();

        System.out.println(list);

        return list;
    }

    @Override
    public List queryPrice()
    {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.group("area").avg("price").as("avg_price") //分组字段
//                Aggregation.group("area").sum("1").as("area")
        );
        AggregationResults<BasicDBObject> output = mongoTemplate.aggregate(agg,
                                                                           "RentInfo",
                                                                           BasicDBObject.class);
        List<BasicDBObject> list = output.getMappedResults();

        System.out.println(list);
        return list;
    }

    @Override
    public List<Object> getLngLat()
    {
        Query query = new Query();

        query.fields().include("location");
        List<RentInfo> list = mongoTemplate.findAll(RentInfo.class, "RentInfo");  //可行

        List<Object> lnglats = new ArrayList<>();
        for (RentInfo item : list)
        {
            lnglats.add(item.getLocation());
        }
//        String ans = new Gson().toJson(lnglats);
//
//        System.out.println(ans);

        return lnglats;
    }
}
