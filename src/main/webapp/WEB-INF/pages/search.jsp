<%--
  Created by IntelliJ IDEA.
  User: yunlongxu
  Date: 4/14/16
  Time: 11:34 PM
  To change this template use File | Settings | File Templates.
--%>
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

    <!--High Chart -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://code.highcharts.com/highcharts.js" type="text/javascript"></script>
    <script src="https://code.highcharts.com/modules/exporting.js" type="text/javascript"></script>

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
            <a class="navbar-brand" href="#">Sensor Cloud</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                ${top}
                <%--<li><a href="#">Home</a></li>--%>
                <%--<li><a href="sensorMaintenance">Sensor Settings</a></li>--%>
                <%--<li><a href="login">Login</a></li>--%>
                <%--<li><a href="#">Help</a></li>--%>
            </ul>
            <%--<form class="navbar-form navbar-right">--%>
                <%--<input type="text" class="form-control" placeholder="Search...">--%>
            <%--</form>--%>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                ${left}
                <%--<li><a href="/">Home</a></li>--%>
                <%--<li class="active"><a href="search">Search<span class="sr-only">(current)</span></a></li>--%>
                <%--<li><a href="monitor">Monitor</a></li>--%>
                <%--<li><a href="sensorMgn">Management</a></li>--%>
                <%--<li><a href="#">Export</a></li>--%>
            </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Sensor Data Request</h1>
            <div class="row placeholders">
                <div id="sensorDataRequest">
                    <%--<form method="get" action="sensormgn">--%>
                        <table class="table table-striped">
                            <tr>
                                <td align="right">Sensor Type</td>
                                <td align="left">
                                    <select id="selectedtype">
                                        <option value="sea_water_pressure">Water Pressure</option>
                                        <option value="sea_water_temperature">Water Temperature</option>
                                        <option value="sea_water_practical_salinity">Water Salinity</option>
                                        <option value="mass_concentration_of_oxygen_in_sea_water">Water Oxygen</option>
                                        <option value="sea_water_ph_reported_on_total_scale">Water PH</option>
                                        <option value="turbidity">Water Turbidity</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td align="right">Start Date</td>
                                <td align="left">
                                    <input type="date" id = "strdate" name="startDate">
                                </td>
                            </tr>
                            <tr>
                                <td align="right">End Date</td>
                                <td align="left">
                                    <input type="date" id = "enddate" name="endDate">
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td align="left">
                                    <input type="submit" onclick="analysis()" value="Submit">
                                </td>
                            </tr>
                        </table>
                    <%--</form>--%>
                </div>
            </div>
            <div id="container-hc" style="min-width: 310px; height: 400px;margin: 0 auto"></div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function(){
 //       analysis();
    });
</script>
<%--<script type="text/javascript">--%>
    <%--$(document).ready(function () {--%>
        <%--$.getJSON('https://www.highcharts.com/samples/data/jsonp.php?filename=usdeur.json&callback=?', function (data) {--%>

            <%--$('#container-hc').highcharts({--%>
                <%--chart: {--%>
                    <%--zoomType: 'x'--%>
                <%--},--%>
                <%--title: {--%>
                    <%--text: 'Sensor data over time'--%>
                <%--},--%>
                <%--subtitle: {--%>
                    <%--text: document.ontouchstart === undefined ?--%>
                            <%--'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in'--%>
                <%--},--%>
                <%--xAxis: {--%>
                    <%--type: 'date time'--%>
                <%--},--%>
                <%--yAxis: {--%>
                    <%--title: {--%>
                        <%--text: 'data value'--%>
                    <%--}--%>
                <%--},--%>
                <%--legend: {--%>
                    <%--enabled: false--%>
                <%--},--%>
                <%--plotOptions: {--%>
                    <%--area: {--%>
                        <%--fillColor: {--%>
                            <%--linearGradient: {--%>
                                <%--x1: 0,--%>
                                <%--y1: 0,--%>
                                <%--x2: 0,--%>
                                <%--y2: 1--%>
                            <%--},--%>
                            <%--stops: [--%>
                                <%--[0, Highcharts.getOptions().colors[0]],--%>
                                <%--[1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]--%>
                            <%--]--%>
                        <%--},--%>
                        <%--marker: {--%>
                            <%--radius: 2--%>
                        <%--},--%>
                        <%--lineWidth: 1,--%>
                        <%--states: {--%>
                            <%--hover: {--%>
                                <%--lineWidth: 1--%>
                            <%--}--%>
                        <%--},--%>
                        <%--threshold: null--%>
                    <%--}--%>
                <%--},--%>

                <%--series: [{--%>
                    <%--type: 'area',--%>
                    <%--name: 'USD to EUR',--%>
                    <%--data: data--%>
                <%--}]--%>
            <%--});--%>
        <%--});--%>
    <%--});--%>
<%--</script>--%>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script>window.jQuery || document.write('<script type="text/javascript" src="/resources/js/jquery.min.js"><\/script>')</script>
<script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>
<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
<!-- <script src="../../assets/js/vendor/holder.min.js"></script> -->
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<!-- <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script> -->
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/analysis.js"></script>
<script src="resources/js/bootstrap-switch.js"></script>
</body>
</html>
