
   	<form action="search" id="search_form">
	<table>
	<tr>
	<td>
		<select name="country">
		<option value="ecs.amazonaws.com">US</option>
		<option value="ecs.amazonaws.ca">Canada</option>
	    <option value="ecs.amazonaws.co.uk">UK</option>
		<option value="ecs.amazonaws.de">Germen</option>
		<option value="ecs.amazonaws.fr">France</option>
		<option value="ecs.amazonaws.jp">Japan</option>		
		</select>
	</td>
	<td>
		<select name="searchBean.type">
		<option value="All">All</options>
		<option value="Apparel">Apparel</options>
		<option value="Appliances">Appliances</options>
		<option value="ArtsAndCrafts">ArtsAndCrafts</options>
		<option value="Automotive">Automotive</options>
		<option value="Baby">Baby</options>
		<option value="Beauty">Beauty</options>
		<option value="Blended">Blended</options>
		<option value="Books">Books</options>
		<option value="Classical">Classical</options>
		<option value="Collectibles">Collectibles</options>
		<option value="DigitalMusic">DigitalMusic</options>
		<option value="DVD">DVD</options>
		<option value="Electronics">Electronics</options>
		<option value="ForeignBooks">ForeignBooks</options>
		<option value="Garden">Garden</options>
		<option value="GourmetFood">GourmetFood</options> 	 	
		</select>
	</td>
	<td><input id="pageno" type="hidden" name="searchBean.pageno" value="1"></td>
	<td><input type="text" name="searchBean.text"></td>
	<td><input type="submit" value="search"></td>
	</tr>
	</table>
	</form>
	
	<form action = "Search" id = "ebay">
	  <table>
	    <tr>
		  <td><input type="text" name="searchBean.text"></td>
	      <td><input type="submit" value="search"></td>
	    </tr>
	  </table>
	</form>