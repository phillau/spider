package com.kingdee.apusic.spider.recruit.process;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.kingdee.apusic.spider.common.pool.ThreadPoolManager;
import com.kingdee.apusic.spider.common.process.AbstractProcessor;
import com.kingdee.apusic.spider.news.bean.ChannelBean;
import com.kingdee.apusic.spider.recruit.bean.RecruitBeans;

public class ZhaoPinProcessor extends AbstractProcessor {
	private static final String NAME = "name";
	private static final String URL = "url";
	private static final String SOURCE = "source";
	private static final String CHARSET = "charset";
	private static final String OPEN = "open";
	private static final String CHANNEL = "channel";
	private static final String SORT = "sort";
	static String baseUrl = "http://zhaopin.baidu.com/api/quanzhiasync?";
	static String sub_param = "&sort_type=1&detailmode=close&rn=20&pn=0";
	List<RecruitBeans> recruitList = new ArrayList<RecruitBeans>();
	Map<String, List<RecruitBeans>> RecruitBeanMap = new LinkedHashMap<String, List<RecruitBeans>>();

	@Override
	public void process(ChannelBean channel) {
		try {
			ThreadPoolManager pool = ThreadPoolManager.getInstance();
				Document doc = getDocument("recruit.xml");
				Element root = doc.getRootElement();
				List<Element> childs_list = root.elements();
				for (Element childs : childs_list) {
					String name = childs.attributeValue(NAME);
					String open = childs.attributeValue(OPEN);
					recruitList = getElement(childs, name);
					RecruitBeanMap = new LinkedHashMap<String, List<RecruitBeans>>();
					RecruitBeanMap.put(name, recruitList);
					
					if (open != null && open.toLowerCase().equals("on")) {
						ZhaoPinRunner runner = new ZhaoPinRunner(RecruitBeanMap,"北京");
						pool.addThread(runner);
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<String, List<RecruitBeans>> readRcruitXML(String xml) {
		Map<String, List<RecruitBeans>> RecruitBeanMap = null;
		try {
			Document doc = getDocument(xml);
			Element root = doc.getRootElement();
			List<Element> childs_list = root.elements();
			for (Element childs : childs_list) {
				List<RecruitBeans> recruitList = new ArrayList<RecruitBeans>();
				String name = childs.attributeValue("name");
				recruitList = getElement(childs, name);
				RecruitBeanMap = new LinkedHashMap<String, List<RecruitBeans>>();
				RecruitBeanMap.put(name, recruitList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RecruitBeanMap;
	}

	public static List<RecruitBeans> getElement(Element element, String name) {
		List<Element> list = element.elements();
		List<RecruitBeans> recruitList = new ArrayList<RecruitBeans>();
		for (Element childs : list) {
			String child_name = childs.attributeValue(NAME);
			String url = childs.attributeValue(URL);
			String channel = childs.attributeValue(CHANNEL);
			String sort = childs.attributeValue(SORT);
			String open = childs.attributeValue(OPEN);
			String source = childs.attributeValue(SOURCE);
			String charset = childs.attributeValue(CHARSET);
			RecruitBeans rb = new RecruitBeans(child_name, url, channel, sort,
					open, source, charset);
			recruitList.add(rb);
		}
		return recruitList;
	}

	private Document getDocument(String fileName) {
		SAXReader saxReader = new SAXReader();
		URL xmlPath = this.getClass().getClassLoader().getResource(fileName);
		Document document = null;
		try {
			document = saxReader.read(xmlPath);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
	}
}
