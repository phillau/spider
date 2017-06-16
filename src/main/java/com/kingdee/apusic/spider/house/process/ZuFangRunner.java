package com.kingdee.apusic.spider.house.process;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.kingdee.apusic.spider.common.context.SpringContextUtil;
import com.kingdee.apusic.spider.common.decode.CodecFactory;
import com.kingdee.apusic.spider.common.decode.HessionCodecFactory;
import com.kingdee.apusic.spider.context.Constants;
import com.kingdee.apusic.spider.context.Shananxi;
import com.kingdee.apusic.spider.context.ZhiXiaShi;
import com.kingdee.apusic.spider.news.bean.RssBean;

public class ZuFangRunner implements Runnable {

	private static Logger logger = Logger.getLogger(ZuFangRunner.class);

	private RssBean rb;
	private String table;

	public ZuFangRunner(RssBean rb, String table) {
		this.rb = rb;
		this.table = table;
	}

	@Override
	public void run() {
//		synchronized (this) {
//			System.err.println("house_rb=" + rb);
//			System.err.println("house_table=" + table);

			String old_url = rb.getUrl();

//			System.err.println("old_url=" + old_url);

			long start = System.currentTimeMillis();
			RabbitTemplate rabbitTemplate = (RabbitTemplate) SpringContextUtil
					.getBean("rabbitTemplate");
			CodecFactory code = new HessionCodecFactory();
			if (old_url.contains("bj.lianjia.com")) {
				Lianjia.toPage(old_url, Constants.district);
			} else if (old_url.contains("xa.fang.lianjia.com")) {
				Lianjia.toPage(old_url, Constants.xianDistrict);
			} else if (old_url.contains("xa.58.com")) {
				TongCheng58.toPage(old_url, Shananxi.xianDistrictMap_58, "西安市",rabbitTemplate,code);
			} else if (old_url.contains("xianyang.58.com")) {
				TongCheng58.toPage(old_url, Shananxi.xianyangDistrictMap_58, "咸阳市",rabbitTemplate,code);
			} else if (old_url.contains("baoji.58.com")) {
				TongCheng58.toPage(old_url, Shananxi.baojiDistrictMap_58, "宝鸡市",rabbitTemplate,code);
			} else if (old_url.contains("wn.58.com")) {
				TongCheng58.toPage(old_url, Shananxi.weinanDistrictMap_58, "渭南市",rabbitTemplate,code);
			} else if (old_url.contains("hanzhong.58.com")) {
				TongCheng58.toPage(old_url, Shananxi.hanzhongDistrictMap_58, "汉中市",rabbitTemplate,code);
			} else if (old_url.contains("yl.58.com")) {
				TongCheng58.toPage(old_url, Shananxi.yulinDistrictMap_58, "榆林市",rabbitTemplate,code);
			} else if (old_url.contains("yanan.58.com")) {
				TongCheng58.toPage(old_url, Shananxi.yananDistrictMap_58, "延安市",rabbitTemplate,code);
			} else if (old_url.contains("ankang.58.com")) {
				TongCheng58.toPage(old_url, Shananxi.ankangDistrictMap_58, "安康市",rabbitTemplate,code);
			} else if (old_url.contains("sl.58.com")) {
				TongCheng58.toPage(old_url, Shananxi.shangluoDistrictMap_58, "商洛市",rabbitTemplate,code);
			} else if (old_url.contains("tc.58.com")) {
				TongCheng58.toPage(old_url, Shananxi.tongchuanDistrictMap_58, "铜川市",rabbitTemplate,code);
			} else if (old_url.contains("bj.58.com")) {
				TongCheng58.toPage(old_url, ZhiXiaShi.beijingDistrictMap_58, "北京市",rabbitTemplate,code);
			} else if (old_url.contains("sh.58.com")) {
				TongCheng58.toPage(old_url, ZhiXiaShi.shanghaiDistrictMap_58, "上海市",rabbitTemplate,code);
			} else if (old_url.contains("tj.58.com")) {
				TongCheng58.toPage(old_url, ZhiXiaShi.tianjinDistrictMap_58, "天津市",rabbitTemplate,code);
			} else if (old_url.contains("cq.58.com")) {
				TongCheng58.toPage(old_url, ZhiXiaShi.chongqingDistrictMap_58, "重庆市",rabbitTemplate,code);
			}
			long end = System.currentTimeMillis();
			long period = (end - start) / (1000 * 60);
			System.out.println("house-over....共" + period + "分钟");
//		}
	}
}
