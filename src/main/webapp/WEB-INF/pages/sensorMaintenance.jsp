<%--
  Created by IntelliJ IDEA.
  User: yunlongxu
  Date: 4/15/16
  Time: 12:29 AM
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
                <li><a href="/">Home</a></li>
                <li><a href="sensorMaintenance">Sensor Settings</a></li>
                <li><a href="login">Login</a></li>
                <li><a href="#">Help</a></li>
            </ul>
            <form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="Search...">
            </form>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li><a href="/">Home</a></li>
                <li class="active"><a href="">Search <span class="sr-only">(current)</span></a></li>
                <li><a href="monitor">Monitor</a></li>
                <li><a href="#">Analytics</a></li>
                <li><a href="#">Export</a></li>
            </ul>
            <!-- <ul class="nav nav-sidebar">
              <li><a href="">Nav item</a></li>
              <li><a href="">Nav item again</a></li>
              <li><a href="">One more nav</a></li>
              <li><a href="">Another nav item</a></li>
              <li><a href="">More navigation</a></li>
            </ul> -->
            <!-- <ul class="nav nav-sidebar">
              <li><a href="">Nav item again</a></li>
              <li><a href="">One more nav</a></li>
              <li><a href="">Another nav item</a></li>
            </ul> -->
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Sensor Maintenance Request</h1>
            <div class="row placeholders">
                <div id="sensorMaintenanceRequest">
                    <form method="get" action="monitor">
                        <table class="table table-striped">
                            <tr>
                                <td align="right">Action Type</td>
                                <td align="left">
                                    <select>
                                        <option value="Temperature">Register</option>
                                        <option value="WindSpeed">Unregister</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td align="right">Sensor ID</td>
                                <td align="left">
                                    <input type="text" name="sensorID">
                                </td>
                            </tr>
                            <tr>
                                <td align="right">Sensor Name</td>
                                <td align="left">
                                    <input type="text" name="sensorName">
                                </td>
                            </tr>
                            <tr>
                                <td align="right">Sensor Type</td>
                                <td align="left">
                                    <select>
                                        <option value="Temperature">Temperature</option>
                                        <option value="WindSpeed">Wind Speed</option>
                                        <option value="Wave">Wave</option>
                                        <option value="Salinity">Salinity</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td align="right">Latitude</td>
                                <td align="left">
                                    <input type="text" name="latitude">
                                </td>
                            </tr>
                            <tr>
                                <td align="right">Longitude</td>
                                <td align="left">
                                    <input type="text" name="longitude">
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td align="left">
                                    <input type="submit" value="Submit">
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
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
