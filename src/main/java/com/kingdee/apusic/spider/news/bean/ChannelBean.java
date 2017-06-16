package com.kingdee.apusic.spider.news.bean;

public class ChannelBean {
	
	private String name;
	private String table;
	private String clazz;
	private String file;
	private long period;
	private String open;
	
	public ChannelBean(String name, String table, String clazz, String file,
			long period,String open) {
		this.name = name;
		this.table = table;
		this.clazz = clazz;
		this.file = file;
		this.period = period;
		this.open = open;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public long getPeriod() {
		return period;
	}
	public void setPeriod(long period) {
		this.period = period;
	}

	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}

}
