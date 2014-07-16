package com.ebay.api;

import java.util.HashMap;
import java.util.Map;

public class ItemSearch {
  
  private static final String ENDPOINT = "svcs.ebay.com";
  private static final String appId = "shelfcar-f142-4a1b-92a3-d6c942d322b5";
  
  public static String getUrl(String keyword) {
    	
	String requestUrl = null;
	SignedRequestHelper helper;
    
	try {
      helper = SignedRequestHelper.getInstance(ENDPOINT, appId);
    } catch (Exception e) {
    	e.printStackTrace();
	    return null;
    }      
    
    Map<String, String> params = new HashMap<String, String>();
    params.put("OPERATION-NAME", "findItemsByKeywords");
    params.put("SERVICE-VERSION", "1.0.0");
    params.put("RESPONSE-DATA-FORMAT", "XML");
    params.put("keywords", keyword);
    requestUrl = helper.sign(params);
  return requestUrl;
  }
  
}
