package com.kingdee.apusic.spider.house.process;

import java.util.Collections;
import java.util.List;

import com.kingdee.apusic.spider.common.pool.ThreadPoolManager;
import com.kingdee.apusic.spider.common.process.AbstractProcessor;
import com.kingdee.apusic.spider.common.xml.XmlLoader;
import com.kingdee.apusic.spider.news.bean.ChannelBean;
import com.kingdee.apusic.spider.news.bean.RssBean;

public class ZuFangProcessor extends AbstractProcessor{

	@Override
	public void process(ChannelBean channel) {
		XmlLoader xmlLoader = new XmlLoader(channel.getFile());
		List<RssBean> rssBeanList = xmlLoader.readRssXML();
		String table = channel.getTable();
		
		//打乱抓取顺序
		Collections.shuffle(rssBeanList);
		
		//NewsService newsService = (NewsService)SpringContext.getContext().getBean("newsService");
		//每张表只保留最近7天的新闻
		//newsService.deleteNews(table, Time.getLastDate(-7));
		
		ThreadPoolManager pool = ThreadPoolManager.getInstance();
		for(int i=0;i<rssBeanList.size();i++){
			RssBean rb = rssBeanList.get(i);
			String open = rb.getOpen();
			if(open != null && open.toLowerCase().equals("on")){
				ZuFangRunner runner = new ZuFangRunner(rb,table);
				pool.addThread(runner);
			}
		}
	}
}
