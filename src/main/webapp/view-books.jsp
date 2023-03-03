<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Books</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<form method="post" action="bookNavigationServlet">
		<h1>Current List of Books</h1>
		<p>${requestScope.emptyMessage}</p>
		<table>
		<c:forEach items="${requestScope.allBooks}" var="currentbook">
		<tr>
			<td><input type="radio" name="id" value="${currentbook.bookId}"></td>
			<td>${currentbook.title}</td>
			<td>${currentbook.isbn}</td>
			<td>${currentbook.publishDate}</td>
		</tr>
		</c:forEach>
		</table>
		<input type="submit" value="edit" name="doThisToBook">
		<input type="submit" value="delete" name="doThisToBook">
	</form>
	<form method="post" action="addBookServlet">
		<h1>Add a Book</h1>
		<p>Title: <input type="text" name="title"></p>
		<p>ISBN: <input type="text" name="isbn"></p>
		<p>Publish Date: <input type="date" name="publishDate" min="1800-01-01" max="2024-12-31"></p>
		<input type="submit" value="Add Book">
	</form>
	<button onclick="window.location.href='index.html';">Home</button>
</body>
</html>