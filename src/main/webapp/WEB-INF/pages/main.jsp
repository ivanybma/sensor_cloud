<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<!-- add tag spring:url -->
	<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapMinCss"/>
	<spring:url value="/resources/css/dashboard.css" var="dashboardCss"/>
	<!-- finish adding spring:url tag -->

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="icon" href="../../favicon.ico">

	<title>Dashboard</title>

	<!-- Bootstrap core CSS -->
	<!-- link href="/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" -->
	<link href="${bootstrapMinCss}" rel="stylesheet">

	<!-- Custom styles for this template -->
	<link href="${dashboardCss}" rel="stylesheet">

	<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
	<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
	<!-- <script src="../../assets/js/ie-emulation-modes-warning.js"></script> -->

	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
	<style>
		#map {
			width: 880px;
			height: 300px;
		}
	</style>
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top">


	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/">Sensor Cloud</a>
		</div>


		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				${top}
				<%--<li><a href="/signup">Signup</a></li>--%>
				<%--<li><a href="/login">Login</a></li>--%>
			</ul>
		</div>
	</div>

</nav>

<div class="container-fluid">
	<div class="row">
		<div class="col-sm-3 col-md-2 sidebar">
			<ul class="nav nav-sidebar">
				${left}
				<%--<li class="active"><a href="/">Home<span class="sr-only">(current)</span></a></li>--%>
				<%--<li><a href="/search">Search</a></li>--%>
				<%--<li><a href="/monitor">Monitor</a></li>--%>
				<%--<li><a href="/sensorMgn">Management</a></li>--%>
			</ul>
		</div>
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h1 class="page-header">Dashboard</h1>

			<div class="row placeholders">
				<div id="map"></div>
				<script>
					function initMap() {
						var mapDiv = document.getElementById('map');
						var map = new google.maps.Map(mapDiv, {
							center: {lat: 44.540, lng: -78.546},
							zoom: 8
						});
					}
				</script>
				<script src="https://maps.googleapis.com/maps/api/js?callback=initMap"
						async defer></script>
				<!-- <div class="col-xs-6 col-sm-3 placeholder">
                  <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
                  <h4>Map</h4>
                  <span class="text-muted">Something else</span>
                </div> -->
			</div>


			<h2 class="sub-header">Server Ip Address</h2>
			<div class="table-responsive">
				<thead>
				<tr>
					<th>
						<%
							request.setCharacterEncoding("UTF-8");
							out.println("From port: " + request.getLocalPort()+"<br>");
						%>
					</th>
				</tr>
				</thead>
			</div>

			<%--<h2 class="sub-header">Sensor List</h2>--%>
			<%--<div class="table-responsive">--%>
				<%--<table class="table table-striped">--%>
					<%--<thead>--%>
					<%--<tr>--%>
						<%--<th>Sensor Name</th>--%>
						<%--<th>Sensor Type</th>--%>
						<%--<th>Location</th>--%>
						<%--<th>Status</th>--%>
						<%--<th>Edit</th>--%>
					<%--</tr>--%>
					<%--</thead>--%>
					<%--<tbody>--%>
					<%--<tr>--%>
						<%--<td>1,001</td>--%>
						<%--<td>Lorem</td>--%>
						<%--<td>ipsum</td>--%>
						<%--<td>dolor</td>--%>
						<%--<td>sit</td>--%>
					<%--</tr>--%>
					<%--<tr>--%>
						<%--<td>1,002</td>--%>
						<%--<td>amet</td>--%>
						<%--<td>consectetur</td>--%>
						<%--<td>adipiscing</td>--%>
						<%--<td>elit</td>--%>
					<%--</tr>--%>
					<%--<tr>--%>
						<%--<td>1,003</td>--%>
						<%--<td>Integer</td>--%>
						<%--<td>nec</td>--%>
						<%--<td>odio</td>--%>
						<%--<td>Praesent</td>--%>
					<%--</tr>--%>
					<%--<tr>--%>
						<%--<td>1,003</td>--%>
						<%--<td>libero</td>--%>
						<%--<td>Sed</td>--%>
						<%--<td>cursus</td>--%>
						<%--<td>ante</td>--%>
					<%--</tr>--%>
					<%--<tr>--%>
						<%--<td>1,004</td>--%>
						<%--<td>dapibus</td>--%>
						<%--<td>diam</td>--%>
						<%--<td>Sed</td>--%>
						<%--<td>nisi</td>--%>
					<%--</tr>--%>
					<%--<tr>--%>
						<%--<td>1,005</td>--%>
						<%--<td>Nulla</td>--%>
						<%--<td>quis</td>--%>
						<%--<td>sem</td>--%>
						<%--<td>at</td>--%>
					<%--</tr>--%>
					<%--</tbody>--%>
				<%--</table>--%>
			<%--</div>--%>
		</div>
	</div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script type="text/javascript" src="/resources/js/jquery.min.js"><\/script>')</script>
<script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>
<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
<!-- <script src="../../assets/js/vendor/holder.min.js"></script> -->
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<!-- <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script> -->
</body>
</html>
