<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Authors</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<form method="post" action="authorNavigationServlet">
		<h1>Current List of Authors</h1>
		<p>${requestScope.emptyMessage}</p>
		<table>
			<c:forEach items="${requestScope.allAuthors}" var="currentauthor">
				<tr>
					<td><input type="radio" name="id" value="${currentauthor.authorId}"></td>
					<td><h2>${currentauthor.firstName}</h2></td>
					<td><h2>${currentauthor.lastName}</h2></td>
				</tr>
				<c:forEach items="${currentauthor.titlesAuthored}" var="currenttitle">
					<tr>
						<td colspan="4">Title: ${currenttitle.title} | ISBN: ${currenttitle.isbn} | Publish Date: ${currenttitle.publishDate}</td>
					</tr>
				</c:forEach>
			</c:forEach>
		</table>
		<input type="submit" value="edit" name="doThisToAuthor">
		<input type="submit" value="delete" name="doThisToAuthor">
	</form>
	<form action="addAuthorServlet" method="post">
		<h1>Add an Author</h1>
		<p>First Name: <input type="text" name="firstName"></p>
		<p>Last Name: <input type="text" name="lastName"></p>
		<p>Available Books:</p>
		<select name="allBooksToAdd" multiple size="6">
			<c:forEach items="${requestScope.allBooks}" var="currentbook">
				<option value="${currentbook.bookId}">Title: ${currentbook.title} | ISBN: ${currentbook.isbn} | Publish Date: ${currentbook.publishDate}</option>
			</c:forEach>
		</select>
		<br>
		<input type="submit" value="Create Author">
	</form>
	<button onclick="window.location.href='index.html';">Home</button>
</body>
</html>