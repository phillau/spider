package com.kingdee.apusic.spider.price.bean;

import java.io.Serializable;

public class PriceBean implements Serializable{	
	private static final long serialVersionUID = 1L;
	private String province;//省份
	private String city;//城市
	private String province_price;//省份价格
	private String city_price;//城市价格
	private String date;//发布日期
	private String type;//种类
	private String md5Str;
	
	public PriceBean(String province, String province_price, String city, String city_price, String date, String md5Str) {
		this.province = province;
		this.city = city;
		this.province_price = province_price;
		this.city_price = city_price;
		this.date = date;
		this.md5Str = md5Str;
	}
	public PriceBean(){}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getMd5Str() {
		return md5Str;
	}
	public void setMd5Str(String md5Str) {
		this.md5Str = md5Str;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getProvince_price() {
		return province_price;
	}
	public void setProvince_price(String province_price) {
		this.province_price = province_price;
	}
	public String getCity_price() {
		return city_price;
	}
	public void setCity_price(String city_price) {
		this.city_price = city_price;
	}
}