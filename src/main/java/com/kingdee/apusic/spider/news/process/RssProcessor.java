package com.kingdee.apusic.spider.news.process;

import java.util.Collections;
import java.util.List;

import com.kingdee.apusic.spider.common.pool.ThreadPoolManager;
import com.kingdee.apusic.spider.common.process.AbstractProcessor;
import com.kingdee.apusic.spider.common.xml.XmlLoader;
import com.kingdee.apusic.spider.news.bean.ChannelBean;
import com.kingdee.apusic.spider.news.bean.RssBean;

public class RssProcessor extends AbstractProcessor{

	@Override
	public void process(ChannelBean channel) {
		XmlLoader xmlLoader = new XmlLoader(channel.getFile());
		List<RssBean> rssBeanList = xmlLoader.readRssXML();
		ThreadPoolManager pool = ThreadPoolManager.getInstance();
				RssRunner runner = new RssRunner(rssBeanList);
				pool.addThread(runner);
	}
}
