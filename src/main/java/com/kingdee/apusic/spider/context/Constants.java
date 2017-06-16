package com.kingdee.apusic.spider.context;

/**
 * @author 陈庆钊
 * @version 2017-5-2 下午6:05:21
 * @Email parkme210@126.com
 */
public class Constants {
	public final static String baseUrl = "http://zhaopin.baidu.com/";

	public final static String[] titles = new String[] { "名称", "链接" };

	public final static String[] titles1 = new String[] { "职位分类", "职位类型",
			"招聘标题", "区域", "公司名称", "工资", "发布日期", "来源" };

	public final static String[] companyTitle = new String[] { "公司名称", "公司地址",
			"公司规模", "公司邮箱", "公司性质", "所属行业" };

	public final static String[] title2 = new String[] { "职位分类", "职位类型", "行业",
			"公司名称", "职位标题", "职位名称", "类型", "年龄要求", "城市", "区域", "学历要求", "第一分类",
			"第二分类", "第三分类", "第四分类", "工资", "详细描述", "开始时间", "有效期", "经验要求",
			"信息来源", "职位数量", "福利" };

	public final static String[] district = new String[] { "东城", "西城", "朝阳", 
			"海淀", "丰台", "石景山", "通州", "昌平", "大兴", "亦庄开发区", "顺义", "房山", "门头沟",
			"平谷", "怀柔", "密云", "延庆", "燕郊" };
	
	public final static String[] xianDistrict = new String[] {"曲江","高新","经开","长安","浐灞","城北","城西","城南","城东","西咸","城内","高陵","临潼","阎良","蓝田","户县","周至","渭南"};

	public final static String[] houseTitle1 = new String[] { "标题", "小区名字",
			"户型", "面积", "朝向", "区域", "楼层", "年代", "价格", "看过人数", "更新日期", "备注" };
	
	public final static String[] industries = new String[] { "销售", "市场", "客服",
		"公关", "技工+工人", "生产+研发", "机械+仪表", "司机", "物流+仓储", "贸易+采购", "保险+理赔", "餐饮类",
		"酒店+旅游", "超市+销售", "美容+美发", "保健+按摩", "娱乐+影视", "技术开发", "产品管理", "电子+电气",
		"家政+安保", "物业管理", "淘宝+网店", "财务+会计", "教育+培训", "银行+证券", "律师+法务", "广告+咨询", "美术+设计", "编辑+出版"
		, "翻译+语言", "医疗+药剂", "运动+健身", "环境保护", "政府公务", "房地产", "建筑+装修", "人事+行政", "高级管理", "农林渔牧", "好玩职业"};
}
