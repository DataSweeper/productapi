/**
 *  Action for getting value from search form
 *  @author sivagnanam Wed Jun 11 18:57:00 IST 2014
 */

package com.shelfcare.myapp.action;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import com.shelfcare.myapp.form.ItemForm;
import com.shelfcare.myapp.model.SearchBean;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;


public class Search implements Action, ModelDriven<ItemForm>  {
  
  private String Asin;
  private SearchBean searchBean;
  private static final Logger logger = Logger.getLogger(Search.class);
  
  public SearchBean getSearchBean() {
    return searchBean;
  }
  
  public void setSearchBean(SearchBean searchBean) {
    this.searchBean = searchBean;
  }
	
  public String getAmazonInfo() {
	
	ValueStack stack = ActionContext.getContext().getValueStack();
	Map<String, Object> context = new HashMap<String, Object>();
	ItemForm item = new ItemForm();
	try {
	  Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.5.100", 80));
	  HttpURLConnection connection =(HttpURLConnection)new URL(
	  com.amazon.advertising.api.sample.ItemSearch.getItems(searchBean.getType(), searchBean.getText(), searchBean.getPageno())).openConnection(proxy);
	  connection.setDoOutput(true);
	  connection.setDoInput(true);
	  connection.setRequestProperty("Content-type", "text/xml");
	  connection.setRequestProperty("Accept", "text/xml, application/xml");
	  connection.setRequestMethod("GET");
	  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	  Document doc = dBuilder.parse(connection.getInputStream());
	  doc.getDocumentElement().normalize();
	  NodeList nList = doc.getElementsByTagName("Item");
	  String[][] Iteminfo = new String[ nList.getLength() ][3];
      for (int temp = 0; temp < nList.getLength(); temp++) {
	    Node nNode = nList.item(temp);
	    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	      Element eElement = (Element) nNode;
	      Iteminfo[temp][0] = eElement.getElementsByTagName("ASIN").item(0).getTextContent();
	      Iteminfo[temp][1] = eElement.getElementsByTagName("MediumImage").item(0).getFirstChild().getTextContent();
	      Iteminfo[temp][2] = eElement.getElementsByTagName("Title").item(0).getTextContent();
	    }   
	  }
	  
      context.put("amazonItems", Iteminfo);
      context.put("ebayItems", getEbayInfo());
	  stack.push(context);
	  logger.info("The content were fetched and put into the two dim array and the array export to value stack.");
	  item.setItemList(Iteminfo);
	  logger.info("items in bean" + item.getItemList()[0][1]);
	
	} catch (Exception e) {
        e.printStackTrace();
    }		
	return "searchResult";
  }
	
  public List  getEbayInfo() {
	String[][] Iteminfo = new String[10][3];
	List list = new ArrayList();
	try {
	  logger.info("getEbaycalled.:)");
	  Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.5.100", 80));
	  HttpURLConnection connection =(HttpURLConnection)new URL( 
	  com.ebay.api.ItemSearch.getUrl(searchBean.getText()) ).openConnection(proxy);
	  logger.info("url in ebay : " + com.ebay.api.ItemSearch.getUrl(searchBean.getText()));
	  connection.setDoOutput(true);
	  connection.setDoInput(true);
	  connection.setRequestProperty("Content-type", "text/xml");
	  connection.setRequestProperty("Accept", "text/xml, application/xml");
	  connection.setRequestMethod("GET");
	  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	  Document doc = dBuilder.parse(connection.getInputStream());
	  doc.getDocumentElement().normalize();
	  NodeList nList = doc.getElementsByTagName("item");

	  for (int temp = 0; temp < nList.getLength(); temp++) {
	    Node nNode = nList.item(temp);
	    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	      String[] arr = new String[3];
	      Element eElement = (Element) nNode;
	      arr[0] = eElement.getElementsByTagName("itemId").toString();
	      arr[1] = eElement.getElementsByTagName("galleryURL").item(0).getFirstChild().getTextContent();
	      arr[2] = eElement.getElementsByTagName("title").item(0).getTextContent();
	      list.add(arr);
	    }   
	  }
      logger.info("Thecontent were fetched are: " + list);
	} catch (Exception e) {
        e.printStackTrace();
    }
    return list;
  }
  
  public String lookUp() {
	
    ValueStack stack = ActionContext.getContext().getValueStack();
    Map<String, Object> context = new HashMap<String, Object>();
    List list = new ArrayList();
	
	try {
	  logger.info("lookUp called asin = " + getAsin());
	  Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.5.100", 80));
	  HttpURLConnection connection =(HttpURLConnection)new URL( 
	  com.amazon.advertising.api.sample.ItemLookup.look(getAsin())).openConnection(proxy);
	  logger.info("url in lookup : " + com.amazon.advertising.api.sample.ItemLookup.look(getAsin()));
	  connection.setDoOutput(true);
	  connection.setDoInput(true);
	  connection.setRequestProperty("Content-type", "text/xml");
	  connection.setRequestProperty("Accept", "text/xml, application/xml");
	  connection.setRequestMethod("GET");
	  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	  Document doc = dBuilder.parse(connection.getInputStream());
	  doc.getDocumentElement().normalize();
	  NodeList nList = doc.getElementsByTagName("Item");
	  logger.info("nList length: " + nList.getLength());
	  
	  logger.info("nList o th item" + nList.item(0).getFirstChild().getTextContent());
	  for (int temp = 0; temp < nList.getLength(); temp++) {
	    
		Node nNode = nList.item(temp);
		
	    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	    Object[] arr = new Object[5];
		Element eElement = (Element) nNode;
		arr[0] = eElement.getElementsByTagName("DetailPageURL").item(0).getTextContent();
		arr[1] = eElement.getElementsByTagName("MediumImage").item(0).getFirstChild().getTextContent();
		
		if (eElement.getElementsByTagName("OfferSummary").getLength() != 0) {
		  arr[2] = Double.parseDouble(eElement.getElementsByTagName("OfferSummary").item(0).getFirstChild().getTextContent().split(" ")[1].replace(",",""));
		  arr[4] = Double.parseDouble(eElement.getElementsByTagName("TotalUsed").item(0).getTextContent());
		}
		else {
		  arr[2] = 0;
		  arr[4] = 0;
		}
		
		if (eElement.getElementsByTagName("ListPrice").getLength() != 0)
		  arr[3] = Double.parseDouble(eElement.getElementsByTagName("ListPrice").item(0).getTextContent().split(" ")[1].replace(",",""));
		else
		  arr[3] = 0;
		
		logger.info(arr[2]);
		list.add(arr);
		}   
      }
	  
      context.put("lookup", list);
      context.put("asin", getAsin());
	  stack.push(context);	  
	  logger.info("look up list price change The content were fetched are: " + list);
    } 
	catch (Exception e) {
	        e.printStackTrace();
	}
	return SUCCESS;	
	
  }
	
  @Override
  public ItemForm getModel() {
	// TODO Auto-generated method stub
	return null;
  }
	
  @Override
  public String execute() throws Exception {
    // TODO Auto-generated method stub
	return null;
  }
  
  public String getAsin() {
		return Asin;
  }
	
  public void setAsin(String asin) {
    this.Asin = asin;
  }
	
} //End of Search class.
