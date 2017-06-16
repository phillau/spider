package com.kingdee.apusic.spider.common.pool;

import java.util.ResourceBundle;


/**
 * @author 陈庆钊
 * @Email parkme210@hotmail.com
 * @date 2012-2-3下午11:15:09
 * @Description TODO
 */
public class ThreadPoolManager{

	private ThreadPool threadPool;
	private static ThreadPoolManager instance;
	private static ResourceBundle bundle=null;
	private static Object lock = new Object();  
	
	private static int corePoolSize =3;//默认最少3个
	private static int maxPoolSize =10;//默认最大10个
	private static int keepAliveTime =900; //默认900秒，15分钟
	
	static{
		/*System.out.println("线程池初始化。。。");
		bundle=PropertyResourceBundle.getBundle("config");
		
		corePoolSize = Integer.valueOf(bundle.getString("corePoolSize"));
		maxPoolSize = Integer.valueOf(bundle.getString("maxPoolSize"));
		keepAliveTime = Integer.valueOf(bundle.getString("keepAliveTime"));*/
		
	}
	
	private ThreadPoolManager(){
		threadPool =  new ThreadPool();
	}
	public static ThreadPoolManager getInstance(){   
        if (instance == null){   
            synchronized( lock ){   
                if (instance == null){   
                    instance = new ThreadPoolManager();   
                }   
            }   
        }   
        return instance;   
    }
	
	public void addThread(Runnable r){
		threadPool.addTask(r);
	}

}
