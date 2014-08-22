/**********************************************************************************************
 * Copyright 2009 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file 
 * except in compliance with the License. A copy of the License is located at
 *
 *       http://aws.amazon.com/apache2.0/
 *
 * or in the "LICENSE.txt" file accompanying this file. This file is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under the License. 
 *
 * ********************************************************************************************
 *
 *  Amazon Product Advertising API
 *  Signed Requests Sample Code
 *
 *  API Version: 2009-03-31
 *
 */

package com.amazon.advertising.api.sample;

import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class ItemLookup {

  private static final String AWS_ACCESS_KEY_ID = "AKIAJ2OMST4P7XEJS7JQ";
  private static final String AWS_SECRET_KEY = "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";

    /*
     * Use one of the following end-points, according to the region you are
     * interested in:
     * 
     *      US: ecs.amazonaws.com 
     *      CA: ecs.amazonaws.ca 
     *      UK: ecs.amazonaws.co.uk 
     *      DE: ecs.amazonaws.de 
     *      FR: ecs.amazonaws.fr 
     *      JP: ecs.amazonaws.jp
     * 
     */
  private static final String ENDPOINT = "webservices.amazon.in";
  public static String look (String asin ) {
    
    SignedRequestsHelper helper;
    try {
      helper = SignedRequestsHelper.getInstance(ENDPOINT, AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
    }
    catch (Exception e) {
      e.printStackTrace();
      return null;
    }
        
  String requestUrl = null;
  String title = null;

  Map<String, String> params = new HashMap<String, String>();
  params.put("Service", "AWSECommerceService");
  params.put("Version", "2011-08-01");
  params.put("Operation", "ItemLookup");
  params.put("ItemId", asin);
  params.put("ResponseGroup", "Medium");
  params.put("AssociateTag", "myshef-20");

  requestUrl = helper.sign(params);
  
  return requestUrl;
  }
  
//test
  public static void main(String[] args) {
    
	String url;
	url = look("059035342X");
	System.out.println(url);
  }
}
