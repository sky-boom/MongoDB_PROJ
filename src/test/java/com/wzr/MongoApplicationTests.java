package com.wzr;

import com.wzr.pojo.mongo.RentInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@SpringBootTest
class MongoApplicationTests
{
    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    void contextLoads()
    {
        int start = 10;     //分页初始页数
        String area = null;
        int price = 0;
        String roomType = null;
        String position = null;


        //分页
        Query query = new Query();
//        query.addCriteria(Criteria.where("a").is("b"));
        query.skip(10);
        query.limit(10);

        //多条件
        Criteria criteria = Criteria.where("area")
                .is(area);
        List<RentInfo> list = mongoTemplate.
                find(query, RentInfo.class);

//        System.out.println(list);
        for (RentInfo rentinfo : list)
        {
            System.out.println(rentinfo);
        }
    }

    @Test
    void test()
    {
        List<RentInfo> object = mongoTemplate.findAll(RentInfo.class);
        System.out.println(object);
    }

}
