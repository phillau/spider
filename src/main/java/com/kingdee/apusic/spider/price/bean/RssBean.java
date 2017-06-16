package com.kingdee.apusic.spider.price.bean;

public class RssBean {
	
	private String name;
	private String url;
	private String channel;
	private String sort;
	private String open;
	private String source;
	private String charset;
	
	public RssBean() {}
	
	public RssBean(String name, String url, String channel, String sort,String open,String source,String charset ) {
		this.name = name;
		this.url = url;
		this.channel = channel;
		this.sort = sort;
		this.open = open;
		this.source = source;
		this.charset = charset;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
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
	
	

}
