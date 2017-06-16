package com.kingdee.apusic.spider.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** 
 * @author 陈庆钊
 * @version 2017-5-31 上午7:57:54
 * @Email parkme210@126.com
 */
public class SpringUtil {
	private static ApplicationContext  ctx = null;//new ClassPathXmlApplicationContext("applicationContext.xml");
	static {
		System.out.println("初始化容器...");
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	private SpringUtil(){
		System.out.println("初始化。。");
	}
	
	private static SpringUtil su = new SpringUtil();
	
	public ApplicationContext getContext(){
		return ctx;
	}
	
	public static SpringUtil getInstance(){
		return su;
	}

	public static void main(String[] args) {
		SpringUtil.getInstance();
	}
}
