package com.wzr.pojo.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class RentAreaCount
{
    private String area;
    private int count;

    public RentAreaCount()
    {
    }

    public RentAreaCount(String area, int count)
    {
        this.area = area;
        this.count = count;
    }

    public String getArea()
    {
        return area;
    }

    public void setArea(String area)
    {
        this.area = area;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    @Override
    public String toString()
    {
        return "RentAreaCount{" +
                "area='" + area + '\'' +
                ", count=" + count +
                '}';
    }
}
