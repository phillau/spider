package com.kingdee.apusic.spider.house.process;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;
import net.sf.json.JSONObject;

import com.kingdee.apusic.spider.common.decode.CodecFactory;
import com.kingdee.apusic.spider.common.decode.HessionCodecFactory;
import com.kingdee.apusic.spider.common.utils.MD5;
import com.kingdee.apusic.spider.common.utils.Time;
import com.kingdee.apusic.spider.context.Page;
import com.kingdee.apusic.spider.context.SpringUtil;
import com.kingdee.apusic.spider.house.bean.HouseBean;

public class TongCheng58 {
	protected static void toPage(String old_url, Map map,String city,RabbitTemplate rabbitTemplate,CodecFactory code) {
//		System.err.println("正在抓取"+city+"的信息");
		String url = "";
		Iterator iter = map.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry entry = (Entry) iter.next();
			String key = (String) entry.getKey();
//			System.err.println("key="+key);
			String value = (String) entry.getValue();
//			System.err.println("value="+value);
			url = old_url+value;
//			System.out.println("url=" + url);
			getPage(url, key, city,rabbitTemplate,code);
		}
	}
	
	private static void getPage(String url, String district,String city,RabbitTemplate rabbitTemplate,CodecFactory code) {
		try {
			Thread.currentThread().sleep((long) (600000 * Math.random()));
			for (int i = 1; i < 10; i++) {
				Thread.currentThread().sleep((long) (600000 * Math.random()));
				String pageUrl = url+"/chuzu/pn"+i+"/?ClickID=1";
//				System.out.println("pageUrl=" + pageUrl);
				if(catchWeb(pageUrl, district, city, rabbitTemplate, code).contains("没有找到相关信息")){
//					System.out.println("没有找到相关信息");
	 				break;
				}else if(catchWeb(pageUrl, district, city, rabbitTemplate, code).contains("请输入验证码")){
					System.err.println("访问过于频繁，遇到验证码。。。");
					System.exit(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String catchWeb(String url, String district,String city,RabbitTemplate rabbitTemplate,CodecFactory code) {
		try {
			Source source = Page.getSource(url);
//			System.err.println("source="+source.toString());
			if(source.toString().contains("没有找到相关信息")||source.toString().contains("搜索暂无结果")) return "没有找到相关信息";
			if(source.toString().contains("请输入验证码")||source.toString().contains("访问过于频繁，本次访问需要输入验证码")) return "请输入验证码";
			Element houseEl = source.getFirstElementByClass("listUl");
			List<Element> liList = houseEl.getAllElements("li");
//			System.err.println("liList.size()="+ liList.size());
			for (int i = 0; i < liList.size(); i++) {
//				System.err.println("i="+i);
				Element li = liList.get(i);
				if(li.toString().contains("来自")){
				String xiaoquName = li.getAllElements("h2").get(0)
						.getTextExtractor().toString();
//				System.out.println("xiaoquName="+xiaoquName);
				String huxing = li.getAllElementsByClass("room").get(0)
						.getTextExtractor().toString();
//				System.out.println("huxing="+huxing);
				String mianji = li.getAllElementsByClass("room").get(0)
						.getTextExtractor().toString().replace("1室", "").replace("2室", "").replace("3室", "").replace("4室", "").replace("5室", "").replace("0厅", "").replace("1厅", "").replace("2厅", "").replace("3厅", "").replace("4厅", "").replace("1卫", "").replace("2卫", "").replace("3卫", "").replace("4卫", "").replace("5卫", "").replace("以上", "").replace("次卧", "").replace("主卧", "").replace("(", "").replace(")", "").replace(" ", "");
//				System.out.println("mianji="+mianji);
				String type = "";
				if(li.toString().contains("来自经纪人")){
					type = li.getAllElementsByClass("jjr").get(0)
							.getTextExtractor().toString();
				}else if(li.toString().contains("来自品牌公寓")){
//					System.err.println("in来自品牌公寓");
					type = li.getAllElementsByClass("gongyu").get(0)
							.getTextExtractor().toString();
				}else if(li.toString().contains("来自个人房源")){
					type = li.getAllElementsByClass("geren").get(0)
							.getTextExtractor().toString();
				}
				String price = li.getAllElementsByClass("money").get(0)
						.getTextExtractor().toString();
//				System.out.println("price="+price);
				String area = li.getAllElementsByClass("add").get(0)
						.getTextExtractor().toString();
//				System.out.println("area="+area);
				String title = xiaoquName+" "+huxing+" "+price;
//				System.out.println("title="+title);
				String md5Str = new MD5().getMD5ofStr(title+mianji);//HouseBean
//				System.out.println("md5Str="+md5Str);
				String chaoxiang = "",floor="",years="",looked="",updateDate="",note="",sources = "58";
				HouseBean houseBean = new HouseBean(city, district, title,
						xiaoquName, huxing, mianji, chaoxiang, area, floor,
						years, price, looked, updateDate, note, md5Str, sources,type);
				
				String hb_json = JSONObject.fromObject(houseBean).toString();
//				System.out.println("hb_json="+hb_json);
//				byte[] obj = code.serialize(hb_json);
				rabbitTemplate.send("apusic.data.topic.exchange","58.house.web", new Message(hb_json.getBytes("utf-8"),
						new MessageProperties()));;
				}
			}
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return "ok";
	}
}
