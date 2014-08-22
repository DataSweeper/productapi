<%@ taglib prefix="s" uri="/struts-tags" %>
<table>
<s:iterator value="amazonItems"  var="arr">
      <tr><td> <img src='<s:property value="#arr[1]"/>'> </td></tr>
      <tr><td> <s:property value="#arr[2]"/> <BR /> </td></tr>
      <tr><td><a href=lookup?Asin=<s:property value="#arr[0]"/>><input type="button" value="Look"/></a></td></tr>
</s:iterator>
</table>

<p>Ebay Search.</p>

<table>
<s:iterator value="ebayItems"  var="arr">
      <tr><td> <img src='<s:property value="#arr[1]"/>'> </td></tr>
      <tr><td> <s:property value="#arr[2]"/> <BR /> </td></tr>
</s:iterator>
</table>

<table>
<s:iterator value="lookup"  var="arr">
	  <tr><td> <s:property value="#arr[0]"/> <BR /> </td></tr>
      <tr><td> <img src='<s:property value="#arr[1]"/>'> </td></tr>
      <tr><td> <s:property value="#arr[2]"/> <BR /> </td></tr>
</s:iterator>
</table>

 <s:property value="tomail" />
 <s:property value="desiredprice" />
 <s:property value="asin" />