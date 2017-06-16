package com.kingdee.apusic.spider.news.process;

import java.net.URLConnection;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kingdee.apusic.spider.common.context.SpringContextUtil;
import com.kingdee.apusic.spider.common.data.FeedParse;
import com.kingdee.apusic.spider.common.decode.CodecFactory;
import com.kingdee.apusic.spider.common.decode.HessionCodecFactory;
import com.kingdee.apusic.spider.common.utils.JsonUtils;
import com.kingdee.apusic.spider.common.utils.MD5;
import com.kingdee.apusic.spider.common.utils.Time;
import com.kingdee.apusic.spider.common.xml.XmlLoader;
import com.kingdee.apusic.spider.news.bean.NewsBean;
import com.kingdee.apusic.spider.news.bean.RssBean;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class RssRunner implements Runnable {
	private static Logger logger = Logger.getLogger(RssRunner.class);

    protected static Format format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private List<RssBean> rssBeanList;

	public RssRunner(List<RssBean> rssBeanList) {
		this.rssBeanList = rssBeanList;
	}

	@Override
	public void run() {
		long start = System.currentTimeMillis();
		RabbitTemplate rabbitTemplate = (RabbitTemplate) SpringContextUtil
				.getBean("rabbitTemplate");
		//打乱抓取顺序
		Collections.shuffle(rssBeanList);
		for(int i=0;i<rssBeanList.size();i++){
			RssBean rb = rssBeanList.get(i);
			toPage(rabbitTemplate, rb);
		}
		long end = System.currentTimeMillis();
		long period = (end - start) / (1000 * 60);
//		System.out.println("新闻抓取完毕...........");
		System.out.println("news-over....共" + period + "分钟");
	}
	
	public static void toPage(RabbitTemplate rabbitTemplate, RssBean rb){
		List<NewsBean> nbList = new ArrayList<NewsBean>();
		try {
			Thread.currentThread().sleep((long) (10000 + 5000 * Math.random()));
			// String urlStr =
			// "http://news.baidu.com/n?cmd=1&class=internews&tn=rss";
			FeedParse fp = new FeedParse(rb.getUrl());
			URLConnection conn = fp.getConnetion();

			// 读取远程RSS文件信息
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(conn));

			String type = feed.getFeedType();

			// 把读取的结果打印到标准输出
			List list = feed.getEntries();
			for (int i = 0; i < list.size(); i++) {
				SyndEntry entry = (SyndEntry) list.get(i);
				String title = entry.getTitle();
				title.replaceAll("<.*?>", "");
				String link = entry.getLink();
				String source = entry.getAuthor();
				String content = entry.getDescription().getValue();
				String imgSrc = getImgSrc(content);
				imgSrc = coverURL(imgSrc);
				content = coverDesc(content);
				String time = Time.getNow();
				MD5 md = new MD5();
				String md5Str = md.getMD5ofStr(link);// News
				// System.out.println("title="+title);
				String publishDate = format.format(entry.getPublishedDate());

				NewsBean newsBean = new NewsBean(link, title, source, time,
						imgSrc, content, md5Str);
				newsBean.setType("1");
				newsBean.setPublishDate(publishDate);
				newsBean.setIndustry(rb.getChannel());
				String news_json = JsonUtils.objectToJson(newsBean);
//				System.out.println("news_json="+news_json);
				rabbitTemplate.send("apusic.data.topic.exchange","baidu.news.rss", new Message(news_json.getBytes("utf-8"),
								new MessageProperties()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getImgSrc(String htmlStr) {
		String img = "";
		Pattern p_image;
		Matcher m_image;

		String regEx_img = "<img.*src=(.*?)[^>]*?>"; // 图片链接地址
		p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
		m_image = p_image.matcher(htmlStr);
		while (m_image.find()) {
			img = img + "," + m_image.group();
			Matcher m = Pattern.compile("src=\"?(.*?)(\"|>|\\s+)").matcher(img); // 匹配src
			while (m.find()) {
				return m.group(1);
			}
		}
		return null;
	}

	private static String coverURL(String str) {
		if (str != null && !str.equals("")) {
			if (str.indexOf("u=http") != -1) {
				String[] strs = str.split("=");
				String url = strs[1];
				url = url.replaceAll("%3A", ":");
				url = url.replaceAll("%2F", "/");
				url = url.replaceAll("%3D", "=");
				url = url.replaceAll("%26", "&");
				url = url.replaceAll("%2C", ",");
				if (url.indexOf("&") != -1) {
					url = url.substring(0, url.indexOf("&"));
				}
				return url;
			}
		}
		return null;
	}

	private static String coverDesc(String desc) {
		desc = desc.replaceAll("<a.*?>.*?</a>", "");
		desc = desc.replaceAll("<br>", "");
		desc = desc.replaceAll("<br />", "");
		desc = desc.replaceAll("<font>.*?</font>", "");
		desc = desc.replaceAll("<span>.*?</span>", "");
		desc = desc.replaceAll("<nobr>.*?</nobr>", "");
		desc = desc.replaceAll("-", "");
		desc = desc.trim();
		return desc;
	}
}
