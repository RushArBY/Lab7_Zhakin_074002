package com.lab7zhakin;

public class Market {
    private String manufacturer;
    private String name;
    private String price;
    private String size;

    public Market(String name, String price, String manufacturer, String size) {
        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer;
        this.size = size;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public void setManufacturer(String manufacturer) {this.manufacturer = manufacturer;}
    public void setSize(String size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }
    public String getPrice() {
        return price;
    }
    public String getManufacturer() {
        return manufacturer;
    }
    public String getSize() {
        return size;
    }
}
