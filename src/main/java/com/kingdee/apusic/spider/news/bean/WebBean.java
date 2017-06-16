package com.kingdee.apusic.spider.news.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈庆钊
 * @Email parkme210@hotmail.com
 * @date 2011-8-18下午10:10:06
 * @Description TODO
 */
public class WebBean{
	
	private String name;
	private String url;
	private String base;
	private String source;
	private String charset;
	private String open;

	private List<SegmentBean> segmentList = new ArrayList<SegmentBean>();
	
	public WebBean(){}
	public WebBean(String name,String url, String base, String source,String charset,String open) {
		this.url = url;
		this.base = base;
		this.name = name;
		this.source = source;
		this.charset = charset;
		this.open = open;
	}
	
	public List<SegmentBean> getSegmentList() {
		return segmentList;
	}
	public void setSegmentList(List<SegmentBean> segmentList) {
		this.segmentList = segmentList;
	}	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
}
