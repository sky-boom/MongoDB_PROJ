package com.wzr.pojo.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("RentInfo")
public class Location
{
    private double Lng;	//经度
    private double Lat;	//纬度
    private int count;	//模拟访问次数, 1-150

    public Location()
    {
    }

    public Location(double lng, double lat, int count)
    {
        Lng = lng;
        Lat = lat;
        this.count = count;
    }

    public double getLng()
    {
        return Lng;
    }

    public void setLng(double lng)
    {
        Lng = lng;
    }

    public double getLat()
    {
        return Lat;
    }

    public void setLat(double lat)
    {
        Lat = lat;
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
        return " [Lng=" + Lng + ", Lat=" + Lat + ", count=" + count + "]";
    }


}
