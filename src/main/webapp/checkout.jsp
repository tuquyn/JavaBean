<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 4/28/2023
  Time: 9:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Check Out</title>
</head>
<body>
	<jsp:useBean id="acc" scope="session" class="com.example.demo.AccountBean"/>
	<p><b>Customer: <jsp:getProperty name="acc" property="name"/></b></p>
	<p><b>VISA Number: <jsp:getProperty name="acc" property="visaCardNum"/></b></p>
	<p><b>Address: <jsp:getProperty name="acc" property="address"/></b></p>
	<p><b>productList</b></p><br>

	<hr>
	<br>

	<%
		List<List<String>> productList = (List<List<String>>)request.getAttribute("productList");
		String total = (String)request.getAttribute("total");
	%>

	<table style="width:100%" >
		<tbody>
		<tr>
			<th>PRODUCT</th>
			<th>MANUFACTURER</th>
			<th>MADE IN</th>
			<th>PRICE</th>
			<th>QUANTITY</th>
			<th>SUBTOTAL</th>
		</tr>
		<%
			if(productList != null){
				for(List<String> product : productList){
		%>
		<tr>
			<td style="text-align: center">Product <%=product.get(1)%></td>
			<td style="text-align: center">Manufacturer <%=product.get(2)%></td>
			<td style="text-align: center">Country <%=product.get(3)%></td>
			<td style="text-align: center"><%=product.get(4)%></td>
			<td style="text-align: center"><%=product.get(5)%></td>
			<td style="text-align: center"><%=product.get(6)%></td>
		</tr>
		<%
				}
			}
		%>
		<tr>
			<td></td>
			<td></td>
			<td style="text-align: center">TOTAL</td>
			<td style="text-align: center">$<%=total%></td>
			<td></td>
		</tr>
		</tbody>
	</table>

	<a href="ShoppingServlet">Shop some more!</a> | <a href="account.jsp">Logout</a>
</body>
</html>
