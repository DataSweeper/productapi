/**
 * interceptor to check user logged in or not
 * @author sivagnanam <Tue Jun 17 17:56:41 IST 2014>
 */
package com.shelfcare.myapp.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor implements Interceptor{
	
	@Override
	public void destroy() {
		
	}
	
	@Override
	public void init() {
		
	}
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map <String, Object> sessionAttributs = invocation.getInvocationContext().getSession();
		if (!sessionAttributs.containsKey("email")) {
			return "login";
		} else {
			if (sessionAttributs.containsKey("email")) {
				return invocation.invoke();
			} else {
				return "login";
			}
		}
	}
}
