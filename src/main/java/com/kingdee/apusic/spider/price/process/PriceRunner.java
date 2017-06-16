package com.kingdee.apusic.spider.price.process;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import com.kingdee.apusic.spider.common.context.SpringContextUtil;
import com.kingdee.apusic.spider.common.utils.JsonUtils;
import com.kingdee.apusic.spider.common.utils.MD5;
import com.kingdee.apusic.spider.context.Page;
import com.kingdee.apusic.spider.news.bean.RssBean;
import com.kingdee.apusic.spider.price.bean.PriceBean;

public class PriceRunner implements Runnable {
	private static Logger logger = Logger.getLogger(PriceRunner.class);

	protected static Format format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private List<RssBean> rssBeanList;

	public PriceRunner(List<RssBean> rssBeanList) {
		this.rssBeanList = rssBeanList;
	}

	@Override
	public void run() {
		long start = System.currentTimeMillis();
		RabbitTemplate rabbitTemplate = (RabbitTemplate) SpringContextUtil
				.getBean("rabbitTemplate");
		// 打乱抓取顺序
		Collections.shuffle(rssBeanList);
		for (int i = 0; i < rssBeanList.size(); i++) {
			RssBean rb = rssBeanList.get(i);
			toPage(rabbitTemplate, rb);
		}
		long end = System.currentTimeMillis();
		long period = (end - start) / (1000 * 60);
		System.out.println("price-over....共" + period + "分钟");
	}

	public static void toPage(RabbitTemplate rabbitTemplate, RssBean rb) {
		try {
			Thread.currentThread().sleep((long) (10000 + 5000 * Math.random()));
			Source source = Page.getSource(rb.getUrl());
			Element city_El = source.getFirstElementByClass("connect container clearfix");
			Element tbody = city_El.getFirstElement("tbody");
			List<Element> trList = tbody.getAllElements("tr");
			Element province_El = source.getFirstElementByClass("notable t_table");
			String province = source.getFirstElementByClass("pt_tit").getFirstElement("em").getTextExtractor().toString().trim();
			String date = source.getFirstElementByClass("pt_tit").getTextExtractor().toString().replace(province, "").replace("(", "").replace(")", "").trim();
			String province_price = province_El.getFirstElement("td").getFirstElement("strong").getTextExtractor().toString();
//			System.err.println("trList.size()="+trList.size());
			for(Element tr:trList){
				String city = tr.getFirstElement("td").getTextExtractor().toString();
				String city_price = tr.getAllElements("td").get(1).getFirstElement("span").getTextExtractor().toString();
				String md5Str = new MD5().getMD5ofStr(city+city_price+date+province_price);
				PriceBean priceBean = new PriceBean(province ,province_price, city, city_price, date, md5Str);
				priceBean.setType("生猪(外三元)");
				String price_json = JsonUtils.objectToJson(priceBean);
//				System.out.println("price_json="+price_json);
				rabbitTemplate.send("apusic.data.topic.exchange", "hqb.price.web",
						new Message(price_json.getBytes("utf-8"),
								new MessageProperties()));
			}
			
//			public void insert(final PriceBean priceBean){
//				getJdbcTemplate().update("INSERT INTO T_PRICE (PROVINCE,PROVINCE_PRICE,CITY,CITY_PRICE,PUB_DATE,TYPE,MD5) VALUES(?,?,?,?,?,?,?)",                       
//				        new PreparedStatementSetter(){   
//				               public void setValues(PreparedStatement ps) throws SQLException{        
//				                   ps.setString(1, priceBean.getProvince());
//				                   ps.setString(2, priceBean.getProvince_price());
//				                   ps.setString(3, priceBean.getCity());
//				                   ps.setString(4, priceBean.getCity_price());
//				                   ps.setString(5, priceBean.getDate());
//				                   ps.setString(6, priceBean.getType());
//				                   ps.setString(7, priceBean.getMd5Str());
//				               }
//				        }  
//				); 
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
