package com.kingdee.apusic.spider.recruit.bean;

import java.io.Serializable;

/** 
 * @author 陈庆钊
 * @version 2017-5-3 下午4:21:55
 * @Email parkme210@126.com
 */
public class Company implements Serializable{
	
	private String name;//名字
	private String address;// 地点
	private String companySize;//公司规模 1000人
	private String email; //邮箱
	private String employertype;//公司性质 -- 民营 私企 国企
	private String industry;//行业 --计算机、互联网
	private String md5Str;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCompanySize() {
		return companySize;
	}
	public void setCompanySize(String companySize) {
		this.companySize = companySize;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmployertype() {
		return employertype;
	}
	public void setEmployertype(String employertype) {
		this.employertype = employertype;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getMd5Str() {
		return md5Str;
	}
	public void setMd5Str(String md5Str) {
		this.md5Str = md5Str;
	}
	public Company(String name, String address,
			String companySize, String email, String employertype,
			String industry,String md5Str) {
		this.name = name;
		this.address = address;
		this.companySize = companySize;
		this.email = email;
		this.employertype = employertype;
		this.industry = industry;
		this.md5Str = md5Str;
	}
}
