<%@ taglib prefix="s" uri="/struts-tags" %>
<table>
<s:iterator value="lookup"  var="arr">
      <tr><td><a href=<s:property value="#arr[0]"/> > <img src='<s:property value="#arr[1]"/>'> </a></td></tr>
</s:iterator>
</table>

<table>
<thead><tr>
<th>PriceType</th>
<th>Price</th>
<th>DesiredPrice</th>
<th>Email</th>
<th>Submit</th>
</tr></thead>
<tbody>
<tr>
<form action="tracker" method="post"> 
<input type="hidden" name="asin" value="${asin}" />
<input type="hidden" name="vendor_flag" value="AZ" />
<td>Amazon</td>
<td><s:if test="%{lookup[0][3] == 0 }">Not available</s:if><s:else>${lookup[0][3]}</s:else></td>
<td><input type="text" name="desiredprice"/></td>
<td><input type="text" name="tomail" /></td>
<td><input type="submit" value="track" /></td>
</form>
</tr>

<tr>
<form action="tracker" method="post">
 <input type="hidden" name="asin" value="${asin}" />
 <input type="hidden" name="vendor_flag" value="OS" />
<td>3rd Party New</td>
<td><s:if test="%{lookup[0][2] == 0 }">Not available</s:if><s:else>${lookup[0][2]}</s:else></td>
<td><input type="text" name="desiredprice"/></td>
<td><input type="text" name="tomail" /></td>
<td><input type="submit" value="track" /></td>
</form>
</tr>
</tbody>
</table>