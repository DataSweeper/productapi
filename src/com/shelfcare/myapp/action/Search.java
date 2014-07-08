/**
 *  Action for getting value from search form
 *  @author sivagnanam Wed Jun 11 18:57:00 IST 2014
 */

package com.shelfcare.myapp.action;

import java.util.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;










import com.shelfcare.Flipkart.FetchContent;
//import model for SearchBean datatype
import com.shelfcare.myapp.model.SearchBean;
import com.amazon.advertising.api.sample.ItemSearch;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Search extends ActionSupport {
	private SearchBean searchBean;
	
	@Override
	public String execute() {
		ValueStack stack = ActionContext.getContext().getValueStack();
	      Map<String, Object> context = new HashMap<String, Object>();
	      context.put("key1", GetItemInfo());
	      context.put("key2", FetchContent.item(ItemSearch.getItems(searchBean.getText()))); //FetchContent.item("samsung led tv")
	      context.put("key3", Geturl());
	      stack.push(context);
		return SUCCESS;
	} 
	
	public SearchBean getSearchBean() {
		return searchBean;
	}
	public void setSearchBean(SearchBean searchBean) {
		this.searchBean = searchBean;
	}
	

	private String Geturl() {
		String url = null;
		url = ItemSearch.getItems(searchBean.getType(), searchBean.getText(), searchBean.getPageno());
		return url;
	}
	
	
	
	private String[][][] GetItemInfo() {
		String[][][] Iteminfo = new String[10][3][3];
		try {
			
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.5.100", 80));
			HttpURLConnection connection =(HttpURLConnection)new URL(Geturl()).openConnection(proxy);
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestProperty("Content-type", "text/xml");
			connection.setRequestProperty("Accept", "text/xml, application/xml");
			connection.setRequestMethod("GET");
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(connection.getInputStream());
	 
	        //optional, but recommended
	        //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	        doc.getDocumentElement().normalize();
	        NodeList nList = doc.getElementsByTagName("Item");
	        //System.out.println(nList.getLength());
	 
	        for (int temp = 0; temp < nList.getLength(); temp++) {
	 
	                Node nNode = nList.item(temp);
	 
	                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	 
	                        Element eElement = (Element) nNode;
	                        Iteminfo[temp][0][0] = eElement.getElementsByTagName("ASIN").item(0).getTextContent();
	                        Iteminfo[temp][0][1] = eElement.getElementsByTagName("MediumImage").item(0).getFirstChild().getTextContent();
	                        Iteminfo[temp][0][2] = eElement.getElementsByTagName("Title").item(0).getTextContent();
	 
	                }   
	        }    

	}catch (Exception e) {
        e.printStackTrace();
    }
		return Iteminfo;
	} //End of the method GetItemInfo
	
 /*   private String fetchTitle(String requestUrl) {
        String title = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(requestUrl);
            Node titleNode = doc.getElementsByTagName("Title").item(0);
            title = titleNode.getTextContent();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return title;
    }*/
  
} //End of Search class.
