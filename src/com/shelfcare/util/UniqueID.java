package com.shelfcare.util;

import java.util.UUID;

public class UniqueID {
  static long current = System.currentTimeMillis();
  static public synchronized long get(){
    return current++;
  }
  
  public static int generateUniqueId() {    
    UUID idOne = UUID.randomUUID();
    String str=""+idOne;    
    int uid=str.hashCode();
    String filterStr=""+uid;
    str=filterStr.replaceAll("-", "");
    return Integer.parseInt(str);
  } 
  
  public static String generateUniqueString() {    
    UUID idOne = UUID.randomUUID();
    return idOne.toString().replace("-", "");
  }
}
