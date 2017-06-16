package com.kingdee.apusic.spider.recruit.process;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.htmlparser.jericho.Source;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.kingdee.apusic.spider.common.context.SpringContextUtil;
import com.kingdee.apusic.spider.common.decode.CodecFactory;
import com.kingdee.apusic.spider.common.decode.HessionCodecFactory;
import com.kingdee.apusic.spider.common.utils.MD5;
import com.kingdee.apusic.spider.context.Page;
import com.kingdee.apusic.spider.context.SpringUtil;
import com.kingdee.apusic.spider.recruit.bean.Company;
import com.kingdee.apusic.spider.recruit.bean.RecruitBeans;
import com.kingdee.apusic.spider.recruit.bean.Title;

public class ZhaoPinRunner implements Runnable {
	private static Logger logger = Logger.getLogger(ZhaoPinRunner.class);
	static String baseUrl = "http://zhaopin.baidu.com/api/quanzhiasync?";
	static String sub_param = "&sort_type=1&detailmode=close&rn=20&pn=0";
	Map<String, List<RecruitBeans>> map = new LinkedHashMap<String, List<RecruitBeans>>();
	String city_name = "";
	public ZhaoPinRunner(Map<String, List<RecruitBeans>> map,String city_name) {
		this.city_name = city_name;
		this.map = map;
	}

	@Override
	public void run() {
			long start = System.currentTimeMillis();
			RabbitTemplate rabbitTemplate = (RabbitTemplate) SpringContextUtil
					.getBean("rabbitTemplate");
			CodecFactory code = new HessionCodecFactory();
			loopRecruit(rabbitTemplate,code);
			long end = System.currentTimeMillis();
			long period = (end - start) / (1000 * 60);
			System.err.println("recruit-over....共" + period + "分钟");
	}

	public void loopRecruit(RabbitTemplate rabbitTemplate,CodecFactory code) {
		try {
			Iterator iter = map.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<String, List<RecruitBeans>> entry = (Entry) iter.next();
				String name = entry.getKey();
				List<RecruitBeans> list = entry.getValue();
				Collections.shuffle(list);
				for (RecruitBeans rb : list) {
					String href = rb.getUrl().replace("needcity", city_name);
					String type = href.split("query=")[1];
					String param = href.split("\\?")[1];
					String catchUrl = baseUrl + param + sub_param;
					catchweb(name, type, catchUrl,rabbitTemplate,code);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void catchweb(String sort, String type, String catchUrl,RabbitTemplate rabbitTemplate,CodecFactory code) {
//		System.out.println("catchUrl=" + catchUrl);
		try {
			Thread.currentThread().sleep(
					(long) (15000 * Math.random()));
			Source source = Page.getSource(catchUrl);
			String json = source.toString();
			JSONObject obj = JSONObject.fromObject(json);
			JSONObject data = obj.getJSONObject("data");
			JSONObject main = data.getJSONObject("main");
			JSONObject mainData = main.getJSONObject("data");
			if (mainData.has("disp_data")) {
				JSONArray jsonArray = mainData.getJSONArray("disp_data");
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject object = jsonArray.getJSONObject(i);
					panduan(object);
					String name = object.getString("officialname").trim();
					String address = object.getString("companyaddress").trim();
					String companySize = object.getString("ori_size").trim();
					String email = object.getString("email").trim();
					String employertype = object.getString("employertype").trim();
					String industry = object.getString("industry").trim();
					String title = object.getString("title").trim();
					String commonname = object.getString("commonname").trim();
					String type1 = object.getString("@type").trim();
					String age = object.getString("age").trim();
					String city = object.getString("city").trim();
					String district = object.getString("district").trim();
					String education = object.getString("education").trim();
					String jobfirstclass = object.getString("jobfirstclass").trim();
					String jobsecondclass = object.getString("jobsecondclass").trim();
					String jobthirdclass = object.getString("jobthirdclass").trim();
					String jobfourthclass = object.getString("jobfourthclass").trim();
					String salary = object.getString("salary").trim();
					String description = object.getString("description").trim();
					String startdate = object.getString("startdate").trim();
					String enddate = object.getString("enddate").trim();
					String experience = object.getString("experience").trim();
					String source1 = object.getString("source").trim();
					String number = object.getString("number").trim();
					String welfare = object.getString("ori_welfare").trim();
					String industry1 = object.getString("industry").trim();
					String officialname = object.getString("officialname").trim();
					String t_md5Str = new MD5().getMD5ofStr(title);//Title
					String c_md5Str = new MD5().getMD5ofStr(name + address);//Company

					Title t = new Title(sort, type, title, commonname, type1,
							age, city, district, education, jobfirstclass,
							jobsecondclass, jobthirdclass, jobfourthclass,
							salary, description, startdate, enddate,
							experience, source1, number, welfare, industry1,
							officialname, t_md5Str);

					Company c = new Company(name, address, companySize, email,
							employertype, industry, c_md5Str);
					
					String t_json = JSONObject.fromObject(t).toString();
					String c_json = JSONObject.fromObject(c).toString();
//					System.out.println("t_json="+t_json);
//					System.out.println("c_json="+c_json);
//					byte[] obj_t = code.serialize(t_json);
//					byte[] obj_c = code.serialize(c_json);
					rabbitTemplate.send("apusic.data.topic.exchange","baidu.recruit.title", new Message(t_json.getBytes("utf-8"),
									new MessageProperties()));
					rabbitTemplate.send("apusic.data.topic.exchange","baidu.recruit.company", new Message(c_json.getBytes("utf-8"),
							new MessageProperties()));
				}
			}
			int pn = Integer.parseInt(catchUrl.substring(
					catchUrl.lastIndexOf("=") + 1, catchUrl.length()));
			int dispNum = 0;

			try {
				dispNum = Integer.parseInt(mainData.getString("dispNum"));
			} catch (Exception e) {
				dispNum = 0;
			}

			int page = 0;
			if (dispNum > 20) {
				page = dispNum / 20 + 1;
				int curPage = pn / 20;
//				System.out.println("共有" + dispNum + "条,总共" + page + "页,当前页："
//						+ curPage);
				if (curPage + 1 < page) {
					String subUrl = catchUrl.substring(0,
							catchUrl.lastIndexOf("=") + 1)
							+ (pn + 20);
//					System.out.println("curPage="+curPage);
					if(curPage>1){
//						System.out.println("curPage>1");
						return;
					}
					catchweb(sort, type, subUrl,rabbitTemplate,code);
				}
			}
		} catch (net.sf.json.JSONException e) {
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}

	private static void panduan(JSONObject object) {
		if (!object.has("officialname"))
			object.put("officialname", "");
		if (!object.has("companyaddress"))
			object.put("companyaddress", "");
		if (!object.has("ori_size"))
			object.put("ori_size", "");
		if (!object.has("email"))
			object.put("email", "");
		if (!object.has("employertype"))
			object.put("employertype", "");
		if (!object.has("industry"))
			object.put("industry", "");
		if (!object.has("title"))
			object.put("title", "");
		if (!object.has("commonname"))
			object.put("commonname", "");
		if (!object.has("@type"))
			object.put("@type", "");
		if (!object.has("age"))
			object.put("age", "");
		if (!object.has("city"))
			object.put("city", "");
		if (!object.has("district"))
			object.put("district", "");
		if (!object.has("education"))
			object.put("education", "");
		if (!object.has("jobfirstclass"))
			object.put("jobfirstclass", "");
		if (!object.has("jobsecondclass"))
			object.put("jobsecondclass", "");
		if (!object.has("jobthirdclass"))
			object.put("jobthirdclass", "");
		if (!object.has("jobfourthclass"))
			object.put("jobfourthclass", "");
		if (!object.has("salary"))
			object.put("salary", "");
		if (!object.has("description"))
			object.put("description", "");
		if (!object.has("startdate"))
			object.put("startdate", "");
		if (!object.has("enddate"))
			object.put("enddate", "");
		if (!object.has("experience"))
			object.put("experience", "");
		if (!object.has("source"))
			object.put("source", "");
		if (!object.has("number"))
			object.put("number", "");
		if (!object.has("welfare"))
			object.put("welfare", "");
		if (!object.has("officialname"))
			object.put("officialname", "");
	}

}
