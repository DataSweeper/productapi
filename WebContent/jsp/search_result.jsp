<%@ taglib prefix="s" uri="/struts-tags" %>
<table>
<s:iterator value="key1"  var="arr">
      <tr><td> <img src='<s:property value="#arr[1]"/>'> </td></tr>
      <tr><td> <s:property value="#arr[2]"/> <BR /> </td></tr>
</s:iterator>
</table>

<p>Ebay Search.</p>

<table>
<s:iterator value="key4"  var="arr">
      <tr><td> <img src='<s:property value="#arr[1]"/>'> </td></tr>
      <tr><td> <s:property value="#arr[2]"/> <BR /> </td></tr>
</s:iterator>
</table>
