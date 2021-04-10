package com.wzr.pojo;

public class LngLat
{
    private double Lng;
    private double Lat;

    public LngLat()
    {
    }

    public LngLat(double lng, double lat)
    {
        Lng = lng;
        Lat = lat;
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

    @Override
    public String toString()
    {
        return "LngLat{" +
                "Lng=" + Lng +
                ", Lat=" + Lat +
                '}';
    }
}
