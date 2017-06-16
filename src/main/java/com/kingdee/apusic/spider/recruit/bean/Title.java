package com.kingdee.apusic.spider.recruit.bean;

import java.io.Serializable;

/** 
 * @author 陈庆钊
 * @version 2017-5-3 下午5:24:19
 * @Email parkme210@126.com
 */
public class Title implements Serializable{
	
	private String titleSort;
	private String titleType;
	private String title;
	private String commonname;
	private String type;//类型 ：全职 兼职
	private String age;
	private String city;
	private String district;
	private String education;
	private String jobfirstclass;
	private String jobsecondclass;
	private String jobthirdclass;
	private String jobfourthclass;
	private String salary;
	private String description;
	private String startdate;
	private String enddate;
	private String experience;//经验
	private String source;//来源
	private String number; //数量
	private String welfare;//福利
	private String industry;
	private String officialname;
	private String md5Str;
	
	public Title(String titleSort, String titleType, String title,
			String commonname, String type, String age, String city,
			String district, String education, String jobfirstclass,
			String jobsecondclass, String jobthirdclass, String jobfourthclass,
			String salary, String description, String startdate,
			String enddate, String experience, String source, String number,
			String welfare, String industry, String officialname,String md5Str) {
		this.titleSort = titleSort;
		this.titleType = titleType;
		this.title = title;
		this.commonname = commonname;
		this.type = type;
		this.age = age;
		this.city = city;
		this.district = district;
		this.education = education;
		this.jobfirstclass = jobfirstclass;
		this.jobsecondclass = jobsecondclass;
		this.jobthirdclass = jobthirdclass;
		this.jobfourthclass = jobfourthclass;
		this.salary = salary;
		this.description = description;
		this.startdate = startdate;
		this.enddate = enddate;
		this.experience = experience;
		this.source = source;
		this.number = number;
		this.welfare = welfare;
		this.industry = industry;
		this.officialname = officialname;
		this.md5Str = md5Str;
	}
	
	public String getTitleSort() {
		return titleSort;
	}
	public void setTitleSort(String titleSort) {
		this.titleSort = titleSort;
	}
	public String getTitleType() {
		return titleType;
	}
	public void setTitleType(String titleType) {
		this.titleType = titleType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCommonname() {
		return commonname;
	}
	public void setCommonname(String commonname) {
		this.commonname = commonname;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
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
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getJobfirstclass() {
		return jobfirstclass;
	}
	public void setJobfirstclass(String jobfirstclass) {
		this.jobfirstclass = jobfirstclass;
	}
	public String getJobsecondclass() {
		return jobsecondclass;
	}
	public void setJobsecondclass(String jobsecondclass) {
		this.jobsecondclass = jobsecondclass;
	}
	public String getJobthirdclass() {
		return jobthirdclass;
	}
	public void setJobthirdclass(String jobthirdclass) {
		this.jobthirdclass = jobthirdclass;
	}
	public String getJobfourthclass() {
		return jobfourthclass;
	}
	public void setJobfourthclass(String jobfourthclass) {
		this.jobfourthclass = jobfourthclass;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getWelfare() {
		return welfare;
	}
	public void setWelfare(String welfare) {
		this.welfare = welfare;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getOfficialname() {
		return officialname;
	}
	public void setOfficialname(String officialname) {
		this.officialname = officialname;
	}
	public String getMd5Str() {
		return md5Str;
	}
	public void setMd5Str(String md5Str) {
		this.md5Str = md5Str;
	}
}
