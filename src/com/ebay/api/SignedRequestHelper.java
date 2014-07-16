package com.ebay.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class SignedRequestHelper {
  
  private static final String UTF8_CHARSET = "UTF-8";
  private static final String REQUEST_URI = "/services/search/FindingService/v1";
  private static final String REQUEST_METHOD = "GET";
  private String endpoint = null;
  private String appId = null;
  
  public static SignedRequestHelper getInstance( String endpoint, String appId) 
    throws IllegalArgumentException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
      if (null == endpoint || endpoint.length() == 0) { throw new IllegalArgumentException("endpoint is null or empty"); }
      if (null == appId || appId.length() == 0) { throw new IllegalArgumentException("awsAccessKeyId is null or empty"); }
      SignedRequestHelper instance = new SignedRequestHelper();
      instance.endpoint = endpoint.toLowerCase();
      instance.appId = appId;
      return instance;
  }
  
  public String sign(Map<String, String> params) {
    params.put("SECURITY-APPNAME", this.appId);
    SortedMap<String, String> sortedParamMap = new TreeMap<String, String>(params);
    String canonicalQS = this.canonicalize(sortedParamMap);
    // construct the URL
    String url = 
      "http://" + this.endpoint + REQUEST_URI + "?" + canonicalQS + "&REST-PAYLOAD";
    return url;
  }
  
  private String canonicalize(SortedMap<String, String> sortedParamMap) {
    if (sortedParamMap.isEmpty()) {
      return "";
    }
    
    StringBuffer buffer = new StringBuffer();
    Iterator<Map.Entry<String, String>> iter = sortedParamMap.entrySet().iterator();

    while (iter.hasNext()) {
      Map.Entry<String, String> kvpair = iter.next();
      buffer.append(percentEncodeRfc3986(kvpair.getKey()));
      buffer.append("=");
      buffer.append(percentEncodeRfc3986(kvpair.getValue()));
      if (iter.hasNext()) {
        buffer.append("&");
      }
    }
    String cannoical = buffer.toString();
    return cannoical;
  }
  
  private String percentEncodeRfc3986(String s) {
    String out;
    try {
      out = URLEncoder.encode(s, UTF8_CHARSET).replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
    } catch (UnsupportedEncodingException e) {
        out = s;
      }
  return out;
  }
  
}
