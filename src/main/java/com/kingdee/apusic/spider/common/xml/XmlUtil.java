package com.kingdee.apusic.spider.common.xml;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.kingdee.apusic.spider.news.bean.ChannelBean;

/**
 * @author 陈庆钊
 * @Email parkme210@hotmail.com
 * @date 2011-3-23 下午04:20:13
 * @Description: TODO
 */
public class XmlUtil {
	
	private static final String NAME="name";
	private static final String TABLE="table";
	private static final String CLAZZ="class";
	private static final String FILE="file";
	private static final String PERIOD="period";
	private static final String OPEN="open";

	private SAXReader reader;
	private Document document;

	public XmlUtil(String fileName) throws Exception {
		reader = new SAXReader();
		document = getDocument(fileName);
	}

	/**
	 * 读取XML 文件.
	 * @param filePath
	 * @return
	 * @throws DocumentException
	 */
	private Document getDocument(String fileName) throws DocumentException {
		URL xmlPath = this.getClass().getClassLoader().getResource(fileName);
		Document document = null;
		try {
			document = reader.read(xmlPath);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
	}
	
	public List<ChannelBean> readChannel(){
		List<ChannelBean> channelList = new ArrayList<ChannelBean>();
		try{
			Element root = document.getRootElement();
			Iterator it = root.elementIterator(); 
		    while(it.hasNext()) {    
	           Element el = (Element)it.next(); 
			   String name = el.attributeValue(NAME);
	           String table = el.attributeValue(TABLE);
	           String clazz = el.attributeValue(CLAZZ);
	           String file = el.attributeValue(FILE);
	           String period = el.attributeValue(PERIOD);
	           String open = el.attributeValue(OPEN);
	           
	           ChannelBean channel = new ChannelBean(name, table, clazz, file, Long.valueOf(period),open);
	           channelList.add(channel);
		    }
		}catch (Exception e) {
			e.printStackTrace();
		}
		return channelList;
	}


}
