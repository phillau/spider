package com.kingdee.apusic.spider;

import java.util.List;
import java.util.Timer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import com.kingdee.apusic.spider.common.context.SpringContextUtil;
import com.kingdee.apusic.spider.common.timer.MyTimer;
import com.kingdee.apusic.spider.common.xml.XmlUtil;
import com.kingdee.apusic.spider.news.bean.ChannelBean;

@SpringBootApplication
@ComponentScan
@ImportResource({ "classpath:applicationContext.xml" })
public class Application {

	private static volatile boolean running = true;

	public static void main(String[] args) throws Exception {
		ApplicationContext context = SpringApplication.run(Application.class,
				args);
		SpringContextUtil.setApplicationContext(context);
		fromXml();
		System.out.println("spider启动起来了....");
		synchronized (Application.class) {
			while (running) {
				try {
					Application.class.wait();
				} catch (Throwable e) {
				}
			}
		}
	}

	public static void fromXml() {
		try {
			XmlUtil xml = new XmlUtil("channels.xml");
			List<ChannelBean> channelList = xml.readChannel();
			for (int i = 0; i < channelList.size(); i++) {
				ChannelBean channel = channelList.get(i);
				String open = channel.getOpen();
				if (open.equalsIgnoreCase("on")) {
					new Timer().schedule(new MyTimer(channel), 2000,
							channel.getPeriod());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}