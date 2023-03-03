<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Book</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<form action="editBookServlet" method="post">
		<h1>Edit Book</h1>
		<p>Title: <input type="text" name="title" value="${bookToEdit.title}"></p>
		<p>ISBN: <input type="text" name="isbn" value="${bookToEdit.isbn}"></p>
		<p>Publish Date: <input type="date" name="publishDate" value="${bookToEdit.publishDate}" min="1800-01-01" max="2024-12-31"></p>
		<input type="hidden" name="id" value="${bookToEdit.bookId}">
		<input type="submit" value="Save Edited Book">
	</form>
</body>
</html>