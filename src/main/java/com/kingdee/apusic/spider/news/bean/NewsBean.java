package com.kingdee.apusic.spider.news.bean;

import java.io.Serializable;
import java.util.Date;

public class NewsBean implements Serializable{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long newsId;
	private String link;
	private String title;
	private String source;
	private String industry;
	private String type;
	private String time;
	private String charset;
	private String imgSrc;
	private String content;
	private String md5Str;
	private String url;
	private String publishDate;
	
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public NewsBean(String link, String title, String source,String time,String imgSrc,
			String content, String md5Str) {
		this.link = link;
		this.title = title;
		this.source = source;
		this.time = time;
		this.imgSrc = imgSrc;
		this.content = content;
		this.md5Str = md5Str;
	}
	public NewsBean(){}
	
	public NewsBean(String link, String title, String source, String industry,
			String type, String time, String charset, String imgSrc,
			String content, String md5Str, String url) {
		this.link = link;
		this.title = title;
		this.source = source;
		this.industry = industry;
		this.type = type;
		this.time = time;
		this.charset = charset;
		this.imgSrc = imgSrc;
		this.content = content;
		this.md5Str = md5Str;
		this.url = url;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMd5Str() {
		return md5Str;
	}
	public void setMd5Str(String md5Str) {
		this.md5Str = md5Str;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getNewsId() {
		return newsId;
	}
	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}
	
	

	
}
