/**
 * this is model that will host the search bean value and give SearchBean datatype
 * @author sivagnanam Wed Jun 11 18:51:26 IST 2014.
 */

package com.shelfcare.myapp.model;

public class SearchBean {
	private String pageno;
	private String type;
	private String text;

	
	public String getPageno() {
		return pageno;
	}
	public void setPageno(String pageno) {
		this.pageno = pageno;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
