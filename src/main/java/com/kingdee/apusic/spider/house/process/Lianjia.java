package com.kingdee.apusic.spider.house.process;

import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;
import net.sf.json.JSONObject;

import com.kingdee.apusic.spider.common.utils.HanyuPinyinHelper;
import com.kingdee.apusic.spider.common.utils.MD5;
import com.kingdee.apusic.spider.common.utils.Time;
import com.kingdee.apusic.spider.context.Page;
import com.kingdee.apusic.spider.context.SpringUtil;
import com.kingdee.apusic.spider.house.bean.HouseBean;

public class Lianjia {
	protected static void toPage(String old_url, String[] districts) {
		String url = "";
		for (int i = 0; i < districts.length; i++) {
			String district = districts[i];
			String pinyin = HanyuPinyinHelper.getPinyinString(district);
			// url = "https://bj.lianjia.com/zufang/" + pinyin + "/";
			url = old_url + pinyin + "/";
			System.out.println("url=" + url);
			// if (district.equals(key))
			getPage(url, district);
		}
	}
	
	private static void getPage(String url, String district) {
		try {
			Thread.currentThread().sleep((long) (20000 + 5000 * Math.random()));
			Source source = Page.getSource(url);
			// System.out.println("source=" + source.toStri ng());
			Element pList = source
					.getFirstElementByClass("page-box house-lst-page-box");
			String json = pList.getAttributeValue("page-data");
			JSONObject obj = JSONObject.fromObject(json);
			int pageNum = obj.getInt("totalPage");
			System.out.println("一共" + pageNum + "页");
			// List<HouseBean> hbList = new ArrayList<HouseBean>();
			for (int i = 1; i < pageNum + 1; i++) {
				Thread.currentThread().sleep((long) (20000 + 5000 * Math.random()));
				String pageUrl = "";
				if (i == 1) {
					pageUrl = url + "pg";
					System.err.println("正在抓取第1页");
				} else {
					pageUrl = url + "pg" + i;
					System.err.println("正在抓取第" + i + "页");
				}
				if(pageUrl.contains("bj.lianjia.com")){
					beijing_catchWeb(pageUrl, district);
				}else if(pageUrl.contains("xa.fang.lianjia.com")){
					xian_catchWeb(pageUrl, district);					
				}
			}
			System.out.println("pageNum=" + pageNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void beijing_catchWeb(String url, String district) {
		try {
			Source source = Page.getSource(url);
			Element houseEl = source.getElementById("house-lst");
			List<Element> liList = houseEl.getAllElements("li");
//			ZuFangService zuFangService = (ZuFangService) SpringUtil
//					.getInstance().getContext().getBean("zuFangService");
			for (int i = 0; i < liList.size(); i++) {
				Element li = liList.get(i);
				String title = li.getAllElements("h2").get(0).getFirstElement()
						.getTextExtractor().toString();
				String xiaoquName = li.getAllElements("a").get(2)
						.getTextExtractor().toString();
				String huxing = li.getAllElementsByClass("zone").get(0)
						.getTextExtractor().toString();
				String mianji = li.getAllElementsByClass("meters").get(0)
						.getTextExtractor().toString();
				String chaoxiang = li.getAllElementsByClass("where").get(0)
						.getAllElements("span").get(4).getTextExtractor()
						.toString();
				String str = li.getAllElementsByClass("con").get(0)
						.getTextExtractor().toString();
				// String title = li.getTextExtractor().toString();
				String area = str.split("/")[0];
				String floor = str.split("/")[1];
				String years = "";
				if (str.split("/").length > 2) {
					years = str.split("/")[2];
				}

				String price = li.getAllElementsByClass("price").get(0)
						.getTextExtractor().toString();
				String updateDate = li.getAllElementsByClass("price-pre")
						.get(0).getTextExtractor().toString();
				String looked = li.getAllElementsByClass("square").get(0)
						.getTextExtractor().toString();
				String note = li.getAllElementsByClass("chanquan").get(0)
						.getTextExtractor().toString();
				String md5Str = new MD5().getMD5ofStr(title + mianji);
				String sources = "lianjia";
				HouseBean hb = new HouseBean("北京市", district, title,
						xiaoquName, huxing, mianji, chaoxiang, area, floor,
						years, price, looked, updateDate, note, md5Str,sources,"");
//				List<String> list = zuFangService.getLastNews(Time
//						.getLastDate(-3));
//				for (int k = 0; k < list.size(); k++) {
//					System.err.println("list.get(k)=" + list.get(k));
//				}
//				if (!list.contains(hb.getMd5Str())) {
////					zuFangService.insert(hb);
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void xian_catchWeb(String url, String district) {
		try {
			Source source = Page.getSource(url);
			Element houseEl = source.getElementById("house-lst");
//			List<Element> liList = houseEl.getAllElements("li");
			List<Element> liList = houseEl.getChildElements();
//			ZuFangService zuFangService = (ZuFangService) SpringUtil
//					.getInstance().getContext().getBean("zuFangService");
//			System.err.println(" liList.size()="+ liList.size());
			for (int i = 0; i < liList.size(); i++) {
//				System.err.println("i="+i);
				Element li = liList.get(i);
				String xiaoquName = li.getAllElements("a").get(1)
						.getTextExtractor().toString();
				System.out.println("xiaoquName="+xiaoquName);
				String huxing = li.getAllElementsByClass("area").get(0)
						.getTextExtractor().toString();
				System.out.println("huxing="+huxing);
				String mianji = li.getAllElementsByClass("area").get(0).getAllElements("span").get(0)
						.getTextExtractor().toString();
				System.out.println("mianji="+mianji);
				String price = li.getAllElementsByClass("average").get(0).getAllElements("span").get(0)
						.getTextExtractor().toString();
				System.out.println("price="+price);
				String area = li.getAllElementsByClass("where").get(0).getAllElements("span").get(0)
						.getTextExtractor().toString();
				System.out.println("area="+area);
				String note = li.getAllElementsByClass("type").get(0)
						.getTextExtractor().toString();
				System.out.println("note="+note);
				String title = xiaoquName+" "+huxing+" "+price;
				System.out.println("title="+title);
				String md5Str = new MD5().getMD5ofStr(title+mianji);
				System.out.println("md5Str="+md5Str);
				String chaoxiang = "",floor="",years="",looked="",updateDate="",sources = "lianjia";
				HouseBean hb = new HouseBean("西安市", district, title,
						xiaoquName, huxing, mianji, chaoxiang, area, floor,
						years, price, looked, updateDate, note, md5Str,sources,"");
//				List<String> list = zuFangService.getLastNews(Time
//						.getLastDate(-3));
//				for (int k = 0; k < list.size(); k++) {
//					System.err.println("xian_list.get(k)=" + list.get(k));x
//				}
//				if (!list.contains(hb.getMd5Str())) {
//					System.err.println("in...");
//					zuFangService.insert(hb);
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
