package com.kingdee.apusic.spider.common.xml;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.kingdee.apusic.spider.news.bean.RssBean;
import com.kingdee.apusic.spider.news.bean.SegmentBean;
import com.kingdee.apusic.spider.news.bean.WebBean;


/**
 * @author 陈庆钊
 * @Email parkme210@hotmail.com
 * @date 2011-2-17 02:04:54
 * @Description TODO
 */
public class XmlLoader {
	
	private static final String TABLE="table";
	private static final String CLAZZ="class";
	
	private static final String NAME="name";
	private static final String URL="url";
	private static final String BASE="base";
	private static final String SOURCE="source";
	private static final String CHARSET="charset";
	private static final String OPEN="open";
	
	private static final String TAG="tag";
	private static final String ATTR="attr";
	private static final String ATTR_VALUE="attrValue";
	private static final String CHANNEL="channel";
	private static final String SORT="sort";
	private static final String TYPE="type";
	private static final String AREA="area";
	private static final String LEVEL="level";
	
	private String fileName;

	public XmlLoader(String fileName){
		//this.readXml(fileName);
		this.fileName = fileName;
	}
	
	/**
	 * 解析xml文件
	 * @author:陈庆钊
	 * @param fileName
	 * 2011-2-17 04:27:38
	 */
	public List<RssBean> readRssXML() {
		List<RssBean> list = null;
		try{ 
			list = new ArrayList<RssBean>();
			Document doc = getDocument(fileName);
			Element root = doc.getRootElement();
			String rootName = root.attributeValue(NAME);
			Iterator it = root.elementIterator(); 
		    while(it.hasNext()) {    
	           Element el = (Element)it.next(); 
			   String name = el.attributeValue(NAME);
	           String url = el.attributeValue(URL);
	           String channel = el.attributeValue(CHANNEL);
	           String sort = el.attributeValue(SORT);
	           String open = el.attributeValue(OPEN);
	           String source = el.attributeValue(SOURCE);
	           String charset = el.attributeValue(CHARSET);
	           RssBean rb = new RssBean(name,url,channel,sort,open,source,charset);
	           list.add(rb);
		    }
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 解析xml文件
	 * @author:陈庆钊
	 * @param fileName
	 * 2011-2-17 04:27:38
	 */
	public List<WebBean> readXml() {
		List<WebBean> webBeanList = new ArrayList<WebBean>();
		try{
			Document doc = getDocument(fileName);
			Element root = doc.getRootElement();
			String rootName = root.attributeValue(NAME);
			Iterator it = root.elementIterator(); 
		    while(it.hasNext()) {    
	           Element el = (Element)it.next(); 
			   String pageName = el.attributeValue(NAME);
	           String pageUrl = el.attributeValue(URL);
	           String pageBase = el.attributeValue(BASE);
	           String pageSource = el.attributeValue(SOURCE);
	           String pageCharset = el.attributeValue(CHARSET);
	           String open = el.attributeValue(OPEN);
	           
	           WebBean webBean = new WebBean(pageName, pageUrl, pageBase, pageSource,pageCharset,open);
	           
	           List<SegmentBean> segmentBeanList = new ArrayList<SegmentBean>();
	           
	           Iterator subIt = el.elementIterator(); 
	           while(subIt.hasNext()){
	        	   Element subEl =(Element)subIt.next();
	        	   //递归
	        	   SegmentBean segment = setSegmentBean(subEl);
	        	   segmentBeanList.add(segment);
	        	   webBean.setSegmentList(segmentBeanList);
	           }
	           webBeanList.add(webBean);
		    }
		}catch (Exception e) {
			e.printStackTrace();
		}
		return webBeanList;
	}
	
	/**
	 * @author:陈庆钊
	 * @功能：递归获取SegmentBean 对象
	 * @param el
	 * @return
	 * 2011-8-23下午08:39:08
	 */
	private SegmentBean setSegmentBean(Element el){
		SegmentBean segmentBean = null;
		List<SegmentBean> segmentBeanList = new ArrayList<SegmentBean>();
		if(el!=null){
			String segmentName = el.attributeValue(NAME);
			String segmentType = el.attributeValue(TYPE);
			String segmentTag = el.attributeValue(TAG);
			String segmentAttr = el.attributeValue(ATTR);
			String segmentAttrValue = el.attributeValue(ATTR_VALUE);
			
			String segmentChannel = el.attributeValue(CHANNEL);
			String segmentSort = el.attributeValue(SORT);
			//String segmentArea = el.attributeValue(AREA);
			//String segmentLevel = el.attributeValue(LEVEL);
			segmentBean = new SegmentBean(segmentName, segmentType, segmentTag, segmentAttr,segmentAttrValue, segmentChannel, segmentSort);
			
			Iterator subIt = el.elementIterator(); 
            while(subIt.hasNext()){
        	    Element subEl =(Element)subIt.next();
        	    //递归
        	    segmentBeanList.add(setSegmentBean(subEl));
        	    segmentBean.setSegmentBeanList(segmentBeanList);
            }
			
		}
		return segmentBean;
	}
	/**
	 * 获取Document对象
	 * @param fileName
	 * @return
	 */
	public Document getDocument(String fileName){
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
	
	public static void main(String[] args) {
		String[] fileNames={"extractor.xml"};
		//XmlLoader x = new XmlLoader(fileNames);
		//System.out.println(x.getSiteBeanList());
		//List<WebBean> list = x.getWebBeanList();
		//x.outData(list);
	}
}
