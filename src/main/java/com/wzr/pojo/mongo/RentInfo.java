package com.wzr.pojo.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("RentInfo")
public class RentInfo
{
    private int _id;
    private String address;
    private String area;
    private int floor;
    private String position;
    private int price;
    private String roomType;
    private int square;
    private String title;
    private Object location;

    public int get_id()
    {
        return _id;
    }

    public void set_id(int _id)
    {
        this._id = _id;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getArea()
    {
        return area;
    }

    public void setArea(String area)
    {
        this.area = area;
    }

    public int getFloor()
    {
        return floor;
    }

    public void setFloor(int floor)
    {
        this.floor = floor;
    }

    public String getPosition()
    {
        return position;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public String getRoomType()
    {
        return roomType;
    }

    public void setRoomType(String roomType)
    {
        this.roomType = roomType;
    }

    public int getSquare()
    {
        return square;
    }

    public void setSquare(int square)
    {
        this.square = square;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Object getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    @Override
    public String toString()
    {
        return "RentInfo{" +
                "_id=" + _id +
                ", address='" + address + '\'' +
                ", area='" + area + '\'' +
                ", floor=" + floor +
                ", position='" + position + '\'' +
                ", price=" + price +
                ", roomType='" + roomType + '\'' +
                ", square=" + square +
                ", title='" + title + '\'' +
                ", location=" + location +
                '}';
    }
}
