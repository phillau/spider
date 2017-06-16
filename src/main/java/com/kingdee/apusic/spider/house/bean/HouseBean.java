package com.kingdee.apusic.spider.house.bean;

import java.io.Serializable;

/** 
 * @author 陈庆钊
 * @version 2017-5-7 上午11:35:35
 * @Email parkme210@126.com
 */
public class HouseBean implements Serializable{
	
	private String city;//城市
	private String district;//区域
	private String title;//标题
	private String xiaoquName;//小区名字
	private String huxing;//户型
	private String mianji;//面积
	private String chaoxiang;//朝向
	private String area;//区域
	private String floor;//楼层
	private String years;//年代
	private String price;//价格
	private String looked;//看过人数
	private String updateDate;//更新日期
	private String note;//备注
	private String md5Str;//md5串
	private String sources;//源网站
	private String type;//来源类型
	
	public HouseBean(String city, String district, String title,
			String xiaoquName, String huxing, String mianji, String chaoxiang,
			String area, String floor, String years, String price,
			String looked, String updateDate, String note,String md5Str,String sources,String type) {
		super();
		this.city = city;
		this.district = district;
		this.title = title;
		this.xiaoquName = xiaoquName;
		this.huxing = huxing;
		this.mianji = mianji;
		this.chaoxiang = chaoxiang;
		this.area = area;
		this.floor = floor;
		this.years = years;
		this.price = price;
		this.looked = looked;
		this.updateDate = updateDate;
		this.note = note;
		this.md5Str = md5Str;
		this.sources = sources;
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSources() {
		return sources;
	}
	public void setSources(String sources) {
		this.sources = sources;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getXiaoquName() {
		return xiaoquName;
	}
	public void setXiaoquName(String xiaoquName) {
		this.xiaoquName = xiaoquName;
	}
	public String getHuxing() {
		return huxing;
	}
	public void setHuxing(String huxing) {
		this.huxing = huxing;
	}
	public String getMianji() {
		return mianji;
	}
	public void setMianji(String mianji) {
		this.mianji = mianji;
	}
	public String getChaoxiang() {
		return chaoxiang;
	}
	public void setChaoxiang(String chaoxiang) {
		this.chaoxiang = chaoxiang;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getLooked() {
		return looked;
	}
	public void setLooked(String looked) {
		this.looked = looked;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getMd5Str() {
		return md5Str;
	}
	public void setMd5Str(String md5Str) {
		this.md5Str = md5Str;
	}
}
