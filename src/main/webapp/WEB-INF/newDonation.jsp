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
		<h1>New Donation</h1>
		<form:form action="/donations/new" method="post" modelAttribute="newDonation" class="form" >
		    <p>
		        <form:label path="donationName">Donation Name</form:label>
		        <form:input path="donationName" class="form-control"/>
		        <form:errors path="donationName"/>

		    </p>
		    <p>
		        <form:label path="quantity">Quantity</form:label>
		        <form:input type="number" path="quantity" class="form-control"/>
		        <form:errors path="quantity"/>

		    </p>
		    <p>
		        <form:label path="description">Description</form:label> 
		        <form:textarea path="description" class="form-control"/>
		 		<form:errors path="description"/>    
		    </p>
				<form:hidden path="donor" value="${userId}"/>
		    <button class="btn btn-primary" type="submit" value="Submit">Add New Donation</button>
		</form:form>
	</div>  
</body>
</html>