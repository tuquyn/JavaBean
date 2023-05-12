<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 4/28/2023
  Time: 9:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Account</title>
</head>
<body>
	<%
		request.setAttribute("acc", null);
		request.setAttribute("inventory", null);
	%>
	<h1>Please provide your account information</h1>
	<form method="post" action="ShoppingServlet">
		<table>
			<tbody>
			<tr>
				<td>Name: </td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>VISA Card Number:</td>
				<td><input type="number" name="visaCardNum"></td>
			</tr>
			<tr>
				<td>Address:</td>
				<td><input type="text" name="address"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" name="submit" value="Submit"></td>
			</tr>
			</tbody>
		</table>
	</form>
</body>
</html>
