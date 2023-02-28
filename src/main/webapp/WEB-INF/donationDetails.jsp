<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>   
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/css/style.css"/>

</head>
<body>
<div class="container mt-5">
	<h1>Welcome <c:out value="${userName}"/></h1>
	<a href="/logout"><button>Logout</button></a>
	<table class="table table-striped">
		<tr>
			<td>Donation name:</td>
			<td><c:out value="${donation.donationName}"/></td>
		</tr>
		<tr>
			<td>Quantity:</td>
			<td><c:out value="${donation.quantity}"/></td>
		</tr>
		<tr>
			<td>Description:</td>
			<td><c:out value="${donation.description}"/></td>
		</tr>
		<tr>
			<td>Donor: </td>
			<td><c:out value="${donation.donor.userName}"/></td>
		</tr>
		<tr>
			<td>Contact:</td>
			<td><c:out value="${donation.donor.email}"/></td>
		</tr>
	</table>
</div>
</body>
</html>