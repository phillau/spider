package com.kingdee.apusic.spider.common.timer;

import java.util.TimerTask;

import com.kingdee.apusic.spider.common.process.AbstractProcessor;
import com.kingdee.apusic.spider.news.bean.ChannelBean;

public class MyTimer extends TimerTask{
	
	private ChannelBean channel;
	
	public MyTimer(ChannelBean channel){
		this.channel = channel;
	}
	
	@Override
	public void run() {
		try {
			ClassLoader loader = this.getClass().getClassLoader();
			Class clazz = loader.loadClass(channel.getClazz());
//			System.err.println("clazz="+clazz);
			AbstractProcessor processor = (AbstractProcessor)clazz.newInstance();
			processor.process(channel);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
