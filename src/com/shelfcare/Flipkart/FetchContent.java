package com.shelfcare.Flipkart;

import com.jaunt.*;
import com.jaunt.component.*;

import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;

public class FetchContent{
  public static String[][] item(String url) {
    String[][] list = new String[20][3];
    try{
    
      UserAgent userAgent = new UserAgent();
      userAgent.setProxyHost("192.168.5.100");
      userAgent.setProxyPort(80);
      userAgent.visit(url);
      Element div = userAgent.doc.findFirst("<div id=products>");
//      Element element = div.findEvery("<div class=list-unit>");
      List<Element> imgEle = div.findEvery("<img").toList();
      List<Element> titleEle = div.findEach("<div class=.*title.*").toList();
      List<Element> priceEle = div.findEach("<div class=pu-price>").toList();
      for (int i = 0; i < imgEle.size(); i++) {
    	  list[i][0] = imgEle.get(i).toString().replaceAll("data-src", "src");
          list[i][1] = titleEle.get(i).outerHTML();
          list[i][2] = priceEle.get(i).outerHTML();
      } 
    }
    catch(Exception e){         //if an HTTP/connection error occurs, handle JauntException.
     
    } 
    return list;
  }
}