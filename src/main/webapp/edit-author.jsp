<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Author</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<form action="editAuthorServlet" method="post">
		<h1>Edit Author</h1>
		<input type="hidden" name="id" value="${authorToEdit.authorId}">
		<p>First Name: <input type="text" name="firstName" value="${authorToEdit.firstName}"></p>
		<p>Last Name: <input type="text" name="lastName" value="${authorToEdit.lastName}"></p>
		<p>Available Books:</p>
		<select name="allBooksToAdd" multiple size="6">
			<c:forEach items="${requestScope.allBooks}" var="currentbook">
				<option value="${currentbook.bookId}">Title: ${currentbook.title} | ISBN: ${currentbook.isbn} | Publish Date: ${currentbook.publishDate}</option>
			</c:forEach>
		</select>
		<br>
		<input type="submit" value="Edit Author">
	</form>
</body>
</html>