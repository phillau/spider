package com.kingdee.apusic.spider.news.bean;  

import java.util.ArrayList;
import java.util.List;

public class SegmentBean {

	private String name;
	private String type;
	private String tag;
	private String attr;
	private String attrValue;
	private String channel;
	private String sort;
	//private String area;
	//private String level;
	private List<SegmentBean> segmentBeanList = new ArrayList<SegmentBean>();
	
	public SegmentBean(String name, String type, String tag, String attr,String attrValue,
			String channel, String sort) {
		this.name = name;
		this.type = type;
		this.tag = tag;
		this.attr = attr;
		this.attrValue = attrValue;
		this.channel = channel;
		this.sort = sort;
	//	this.area = area;
	//	this.level = level;
	}

	public SegmentBean(){}
	
	public List<SegmentBean> getSegmentBeanList() {
		return segmentBeanList;
	}
	public void setSegmentBeanList(List<SegmentBean> segmentBeanList) {
		this.segmentBeanList = segmentBeanList;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}
}
