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
		<a href="/donations/new"><button>New Donations</button></a> | <a href="/logout"><button>Logout</button></a>
	
	
		<h1>Donation Dashboard</h1>
			<p><a href="/users/new">New User</a></p>
			<p><a href="/donations/new">New Donation</a></p>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>ID</th>
						<th>Donation</th>
						<th>Quantity</th>
						<th>Donor</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="eachDonation" items="${donationList}">
						<tr>
							<td> ${eachDonation.id} </td>
							<td><a href="/donations/${eachDonation.id}"><c:out value="${eachDonation.donationName}"/></a></td>
							<td><c:out value="${eachDonation.quantity}"/></td>
							<td><c:out value="${eachDonation.donor.userName}"/></td>
							
							<c:choose>
								<c:when test="${eachDonation.donor.id.equals(userId)}">
									<td><a href="/donations/edit/${eachDonation.id}"><button class="btn btn-success">Edit</button></a></td>
									<td>
										<form action="/donations/delete/${eachDonation.id}" method="post">
											<input type="hidden" name="_method" value="delete" />
											<button class="btn btn-danger" value="delete">Delete</button>
										</form>
									</td>
								</c:when>
								<c:when test="${eachDonation.receivers.contains(loggedInUser)}">
									<td>
										<form action="/donations/return/${eachDonation.id}" method="post">
										<input type="hidden" name="_method" value="put">
										<button type="submit">Return</button>
										</form>
									</td>
								</c:when>
								<c:otherwise>
									<td>
										<form action="/donations/receive/${eachDonation.id}" method="post">
										<input type="hidden" name="_method" value="put">
										<button type="submit">Receive</button>
										</form>
									</td>
									<td></td>
								</c:otherwise>
							</c:choose>
							

						</tr>
					</c:forEach>
				</tbody>
			</table>
	</div>

</body>
</html>