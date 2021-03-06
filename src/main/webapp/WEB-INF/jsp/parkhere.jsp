<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  


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
				<a class="navbar-brand" href="#">Spring Boot</a>
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
    <div class="row">
		<div class="starter-template">
		<div class="col-sm-2">
		<h3>Destination: </h3>
		<h3>Destination: ${usersSerachCriteria.getWhereto()}</h3>
		<!--<c:out value='${jsonParkingLots}' />-->
		<%-- String json = '${jsonParkingLots}';
		String parsedJson = JSON.parse(json); --%>
		<c:set var="parkingLots" value = '${jsonParkingLots}' />
		<!--<c:set var="parkingLots" value = '${parkingLotsList}' />-->
		<c:if test="${fn:length(parkingLotsList) eq 0 }" >
			<h4> No nearby parking places found. </h4>
		</c:if>
		
		<c:if test= "${fn:length(parkingLotsList) gt 0}"> 
        <c:set var="noOfParkingPlaces" value = "${fn:length(parkingLotsList)}" />
		<c:forEach var="index" items="${parkingLotsList}">
		<c:out value='${index.streetName}' /><br />
		<%-- <c:out value='${index.lat}' />
		<c:out value='${index.lng }' /> --%>
		<%-- <c:set var="lati" value = '${index.lat}' />
		<c:set var="lngi" value = '${index.lng}' /> --%>
		
	</c:forEach>
</c:if>
</div>
</div>
		
		<div class="col-sm-10">
		<div id="googleMap" style="height:400px;width:100%;"></div>
		</div>
		</div>
<script>
var parkingLots = JSON.parse('${jsonParkingLots}');
  function myMap() {
	  var map = new google.maps.Map(document.getElementById("googleMap"), {
			zoom: 16,
			center: {lat:37.78229410000001, lng:-122.4158954}
			//center: {lat:"{$lati}", lng:"{$lngi}"}
		});
	
	  
getParkingLotValues(map);

/*marker.setMap(map); */
} 

function getParkingLotValues(map) {
	console.log(" Log ");
	//console.log(${parkingLots});
	//console.log('${jsonParkingLots}');
	//var newData=JSON.stringify(${parkingLots}); 
	//var parkingLotObj = JSON.parse(newData);
	var parkingLotObj = JSON.parse('${jsonParkingLots}');
	console.log(parkingLotObj.parkingPlaces[0]);
	var image = 'https://maps.google.com/mapfiles/kml/shapes/parking_lot_maps.png';
	
	for(var i in parkingLotObj.parkingPlaces) {
		console.log(i + " " + parkingLotObj.parkingPlaces[i].streetName + " lat " + parkingLotObj.parkingPlaces[i].lat);
		console.log(parkingLotObj.parkingPlaces[i].lat + " " + parkingLotObj.parkingPlaces[i].lng + " (types: " + (typeof parkingLotObj.parkingPlaces[i].lat) + ", " + (typeof parkingLotObj.parkingPlaces[i].lng) + ")");
		var floatLat = parseFloat(parkingLotObj.parkingPlaces[i].lat);
		var floatLng = parseFloat(parkingLotObj.parkingPlaces[i].lng);
		var marker = new google.maps.Marker({
			 position: {lat: parkingLotObj.parkingPlaces[i].lat, lng: parkingLotObj.parkingPlaces[i].lng},
			 map: map,
			 icon: image
		 }); 
	}
} 



</script>
<script src="https://maps.googleapis.com/maps/api/js?v=3&amp;key=AIzaSyD_yBCEbeHzr6T71GroPsjXsApSNfjEems&callback=myMap"></script>
</div>
	
	
	
	
</body>