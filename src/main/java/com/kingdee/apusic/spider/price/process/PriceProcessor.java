package com.kingdee.apusic.spider.price.process;

import java.util.List;
import com.kingdee.apusic.spider.common.pool.ThreadPoolManager;
import com.kingdee.apusic.spider.common.process.AbstractProcessor;
import com.kingdee.apusic.spider.common.xml.XmlLoader;
import com.kingdee.apusic.spider.news.bean.ChannelBean;
import com.kingdee.apusic.spider.news.bean.RssBean;

public class PriceProcessor extends AbstractProcessor{
	@Override
	public void process(ChannelBean channel) {
		XmlLoader xmlLoader = new XmlLoader(channel.getFile());
		List<RssBean> rssBeanList = xmlLoader.readRssXML();
		ThreadPoolManager pool = ThreadPoolManager.getInstance();
				PriceRunner runner = new PriceRunner(rssBeanList);
				pool.addThread(runner);
	}
}
