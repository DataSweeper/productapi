   <s:form action="search" method="post">
   <s:select name="searchBean.type" list="{'All','Apparel','Appliances','ArtsAndCrafts','Automotive','Baby','Beauty','Blended','Books','Classical','Collectibles','DigitalMusic','DVD','Electronics','ForeignBooks','Garden','GourmetFood'}" ></s:select>
    <s:hidden name="searchBean.pageno" value="1"></s:hidden>
    <s:textfield  name="searchBean.text" ></s:textfield> 
   <s:submit value="search" ></s:submit> </td>  
   </s:form>
