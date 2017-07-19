<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>

<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

<!-- 
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />

</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Service</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#about">About</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">

		<div class="starter-template">
			<h1>ParkCompass</h1>
			<h2>Park it! Track it! Move it!</h2>
		</div>
		<div>
		<form:form class="form-horizontal" method="POST"  action="/parkhere" modelAttribute="usersSerachCriteria">
  		 	<div class="col-sm-10">
  		 		<form:label path="whereto">Where are you going?</form:label>
				<form:input path="whereto" class="form-control" />				
			</div>
	   	  <input type="submit" value="Go" />
 		</form:form>
		</div>

		<%-- <div>
		<form:form class="form-horizontal" method="POST"  action="/parkhere" modelAttribute="parkingPlace">
  		 	<div class="col-sm-10">
  		 		<form:label path="addressLine1">Where are you going?</form:label>
				<form:input path="addressLine1" class="form-control" />				
			</div>
	   	  <input type="submit" value="Go" />
 		</form:form>
		</div> --%>
		
	</div>
	<!-- /.container -->

	<script type="text/javascript" 	src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
