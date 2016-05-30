/**
 * Created by ivanybma on 5/14/16.
 */


function addMap(currentPsensor) {
    var myLocation = [];
    var label = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
    for (var i = 0; i < currentPsensor.length; i++) {
        var latVal = currentPsensor[i].location.latitude;
        var lngVal = currentPsensor[i].location.longitude;
        var location = new google.maps.LatLng(latVal, lngVal);
        myLocation.push(location);
    }
    var mapDiv = document.getElementById('mapCanvas');
    var googleMap = new google.maps.Map(mapDiv, {
        center: myLocation[0],
        zoom: 5
    });
    for (i = 0; i < myLocation.length; i++) {
        addMarker(myLocation[i], label[i], googleMap);
    }
}

function addMarker(location, label, map) {
    var marker = new google.maps.Marker({
        position: location,
        label: label,
        map: map
    });
}

function Refresh_sea_water_pressure_lst(refreshMap) {

    var VirtualSensorId = "VirtualSensorId";
    var PhysicalSensorId = "PhysicalSensorId";
    var PhysicalSensor_Location = "PhysicalSensor_Location";
    var PhysicalSensor_Status = "PhysicalSensor_Status";
    var PhysicalSensor_latitude = "PhysicalSensor_latitude";
    var PhysicalSensor_longitude = "PhysicalSensor_longitude";
    var category = "sea_water_pressure";

    $.ajax({
        url: "monitorsensor/" + category,
        type: "GET",
        DataType: "json",
        error: function (xhr) {
            //alert("An error occured: " + xhr.status + " " + xhr.statusText);
        },
        success: function (result) {

            $("#sea_water_pressure_list_tb #dy_created_sea_water_pressure_list_tb").remove();


            var virtualsensorid = result.vsensorId;
            var sensorType = result.sensorType;
            var status = result.status;
            var psensorlst = result.psensorList;


            for (var i = 0; i < psensorlst.length; i++) {
                //alert(psensorlst[i].status);
                var newtr = $('<tr id="dy_created_sea_water_pressure_list_tb">').append(
                    $('<td>').append(
                        $('<input type="text" class="form-control" name=' + PhysicalSensor_Location +
                        ' readonly>').val(psensorlst[i].location.location)),
                    $('<td>').append(
                        $('<input id =latitude_' + psensorlst[i].psensorId + 'type="text" class="form-control" name=' + PhysicalSensor_latitude + ' readonly>').val(psensorlst[i].location.latitude)),
                    $('<td>').append(
                        $('<input id =longitude_' + psensorlst[i].psensorId + 'type="text" class="form-control" name=' + PhysicalSensor_longitude + ' readonly>').val(psensorlst[i].location.longitude)),
                    $('<td>').append(
                        $('<span class="badge">' + psensorlst[i].status + '</span>')
                    )
                )

                $("#sea_water_pressure_list_tb").append(newtr);
            }
            if(refreshMap==true) {
                addMap(psensorlst);
            }
        },
        complete: function () {
            setTimeout(Refresh_sea_water_pressure_lst(false), 1500);
        }
    });
}

function Refresh_sea_water_temperature_lst(refreshMap) {
    var VirtualSensorId = "VirtualSensorId";
    var PhysicalSensorId = "PhysicalSensorId";
    var PhysicalSensor_Location = "PhysicalSensor_Location";
    var PhysicalSensor_Status = "PhysicalSensor_Status";
    var PhysicalSensor_latitude = "PhysicalSensor_latitude";
    var PhysicalSensor_longitude = "PhysicalSensor_longitude";


    var category = "sea_water_temperature";
    $.ajax({
        url: "monitorsensor/" + category,
        type: "GET",
        DataType: "json",
        error: function (xhr) {
            //alert("An error occured: " + xhr.status + " " + xhr.statusText);
        },
        success: function (result) {

            $("#sea_water_temperature_list_tb #dy_created_sea_water_temperature_list_tb").remove();


            var virtualsensorid = result.vsensorId;
            var sensorType = result.sensorType;
            var status = result.status;
            var psensorlst = result.psensorList;

            for (var i = 0; i < psensorlst.length; i++) {
                //alert(psensorlst[i].status);
                var newtr = $('<tr id="dy_created_sea_water_temperature_list_tb">').append(
                    $('<td>').append(
                        $('<input type="text" class="form-control" name=' + PhysicalSensor_Location +
                        ' readonly>').val(psensorlst[i].location.location)),
                    $('<td>').append(
                        $('<input id =latitude_' + psensorlst[i].psensorId + 'type="text" class="form-control" name=' + PhysicalSensor_latitude + ' readonly>').val(psensorlst[i].location.latitude)),
                    $('<td>').append(
                        $('<input id =longitude_' + psensorlst[i].psensorId + 'type="text" class="form-control" name=' + PhysicalSensor_longitude + ' readonly>').val(psensorlst[i].location.longitude)),
                    $('<td>').append(
                        $('<span class="badge">' + psensorlst[i].status + '</span>')
                    )
                )

                $("#sea_water_temperature_list_tb").append(newtr);
            }
            if(refreshMap==true) {
                addMap(psensorlst);
            }
        },
        complete: function () {
            setTimeout(Refresh_sea_water_temperature_lst(false), 1500);
        }
    });
}

function Refresh_sea_water_practical_salinity_lst(refreshMap) {

    var VirtualSensorId = "VirtualSensorId";
    var PhysicalSensorId = "PhysicalSensorId";
    var PhysicalSensor_Location = "PhysicalSensor_Location";
    var PhysicalSensor_Status = "PhysicalSensor_Status";
    var PhysicalSensor_latitude = "PhysicalSensor_latitude";
    var PhysicalSensor_longitude = "PhysicalSensor_longitude";


    var category = "sea_water_practical_salinity";
    $.ajax({
        url: "monitorsensor/" + category,
        type: "GET",
        DataType: "json",
        error: function (xhr) {
            //alert("An error occured: " + xhr.status + " " + xhr.statusText);
        },
        success: function (result) {

            $("#sea_water_practical_salinity_list_tb #dy_created_sea_water_practical_salinity_list_tb").remove();


            var virtualsensorid = result.vsensorId;
            var sensorType = result.sensorType;
            var status = result.status;
            var psensorlst = result.psensorList;

            for (var i = 0; i < psensorlst.length; i++) {
                //alert(psensorlst[i].status);
                var newtr = $('<tr id="dy_created_sea_water_practical_salinity_list_tb">').append(
                    $('<td>').append(
                        $('<input type="text" class="form-control" name=' + PhysicalSensor_Location +
                        ' readonly>').val(psensorlst[i].location.location)),
                    $('<td>').append(
                        $('<input id =latitude_' + psensorlst[i].psensorId + 'type="text" class="form-control" name=' + PhysicalSensor_latitude + ' readonly>').val(psensorlst[i].location.latitude)),
                    $('<td>').append(
                        $('<input id =longitude_' + psensorlst[i].psensorId + 'type="text" class="form-control" name=' + PhysicalSensor_longitude + ' readonly>').val(psensorlst[i].location.longitude)),
                    $('<td>').append(
                        $('<span class="badge">' + psensorlst[i].status + '</span>')
                    )
                )

                $("#sea_water_practical_salinity_list_tb").append(newtr);
            }
            if(refreshMap==true) {
                addMap(psensorlst);
            }
        },
        complete: function () {
            setTimeout(Refresh_sea_water_practical_salinity_lst(false), 1500);
        }
    });
}

function Refresh_mass_concentration_of_oxygen_in_sea_water_lst(refreshMap) {

    var VirtualSensorId = "VirtualSensorId";
    var PhysicalSensorId = "PhysicalSensorId";
    var PhysicalSensor_Location = "PhysicalSensor_Location";
    var PhysicalSensor_Status = "PhysicalSensor_Status";
    var PhysicalSensor_latitude = "PhysicalSensor_latitude";
    var PhysicalSensor_longitude = "PhysicalSensor_longitude";


    var category = "mass_concentration_of_oxygen_in_sea_water";
    $.ajax({
        url: "monitorsensor/" + category,
        type: "GET",
        DataType: "json",
        error: function (xhr) {
            //alert("An error occured: " + xhr.status + " " + xhr.statusText);
        },
        success: function (result) {

            $("#mass_concentration_of_oxygen_in_sea_water_list_tb #dy_created_mass_concentration_of_oxygen_in_sea_water_list_tb").remove();


            var virtualsensorid = result.vsensorId;
            var sensorType = result.sensorType;
            var status = result.status;
            var psensorlst = result.psensorList;

            for (var i = 0; i < psensorlst.length; i++) {
                //alert(psensorlst[i].status);
                var newtr = $('<tr id="dy_created_mass_concentration_of_oxygen_in_sea_water_list_tb">').append(
                    $('<td>').append(
                        $('<input type="text" class="form-control" name=' + PhysicalSensor_Location +
                        ' readonly>').val(psensorlst[i].location.location)),
                    $('<td>').append(
                        $('<input id =latitude_' + psensorlst[i].psensorId + 'type="text" class="form-control" name=' + PhysicalSensor_latitude + ' readonly>').val(psensorlst[i].location.latitude)),
                    $('<td>').append(
                        $('<input id =longitude_' + psensorlst[i].psensorId + 'type="text" class="form-control" name=' + PhysicalSensor_longitude + ' readonly>').val(psensorlst[i].location.longitude)),
                    $('<td>').append(
                        $('<span class="badge">' + psensorlst[i].status + '</span>')
                    )
                )

                $("#mass_concentration_of_oxygen_in_sea_water_list_tb").append(newtr);
            }
            if(refreshMap==true) {
                addMap(psensorlst);
            }
        },
        complete: function () {
            setTimeout(Refresh_mass_concentration_of_oxygen_in_sea_water_lst(false), 1500);
        }
    });
}

function Refresh_sea_water_ph_reported_on_total_scale_lst(refreshMap) {

    var VirtualSensorId = "VirtualSensorId";
    var PhysicalSensorId = "PhysicalSensorId";
    var PhysicalSensor_Location = "PhysicalSensor_Location";
    var PhysicalSensor_Status = "PhysicalSensor_Status";
    var PhysicalSensor_latitude = "PhysicalSensor_latitude";
    var PhysicalSensor_longitude = "PhysicalSensor_longitude";


    var category = "sea_water_ph_reported_on_total_scale";
    $.ajax({
        url: "monitorsensor/" + category,
        type: "GET",
        DataType: "json",
        error: function (xhr) {
            //alert("An error occured: " + xhr.status + " " + xhr.statusText);
        },
        success: function (result) {

            $("#sea_water_ph_reported_on_total_scale_list_tb #dy_created_sea_water_ph_reported_on_total_scale_list_tb").remove();


            var virtualsensorid = result.vsensorId;
            var sensorType = result.sensorType;
            var status = result.status;
            var psensorlst = result.psensorList;

            for (var i = 0; i < psensorlst.length; i++) {
                //alert(psensorlst[i].status);
                var newtr = $('<tr id="dy_created_sea_water_ph_reported_on_total_scale_list_tb">').append(
                    $('<td>').append(
                        $('<input type="text" class="form-control" name=' + PhysicalSensor_Location +
                        ' readonly>').val(psensorlst[i].location.location)),
                    $('<td>').append(
                        $('<input id =latitude_' + psensorlst[i].psensorId + 'type="text" class="form-control" name=' + PhysicalSensor_latitude + ' readonly>').val(psensorlst[i].location.latitude)),
                    $('<td>').append(
                        $('<input id =longitude_' + psensorlst[i].psensorId + 'type="text" class="form-control" name=' + PhysicalSensor_longitude + ' readonly>').val(psensorlst[i].location.longitude)),
                    $('<td>').append(
                        $('<span class="badge">' + psensorlst[i].status + '</span>')
                    )
                )

                $("#sea_water_ph_reported_on_total_scale_list_tb").append(newtr);
            }
            if(refreshMap==true) {
                addMap(psensorlst);
            }
        },
        complete: function () {
            setTimeout(Refresh_sea_water_ph_reported_on_total_scale_lst(false), 1500);
        }
    });
}

function Refresh_turbidity_lst(refreshMap) {

    var VirtualSensorId = "VirtualSensorId";
    var PhysicalSensorId = "PhysicalSensorId";
    var PhysicalSensor_Location = "PhysicalSensor_Location";
    var PhysicalSensor_Status = "PhysicalSensor_Status";
    var PhysicalSensor_latitude = "PhysicalSensor_latitude";
    var PhysicalSensor_longitude = "PhysicalSensor_longitude";


    var category = "turbidity";
    $.ajax({
        url: "monitorsensor/" + category,
        type: "GET",
        DataType: "json",
        error: function (xhr) {
            //alert("An error occured: " + xhr.status + " " + xhr.statusText);
        },
        success: function (result) {

            $("#turbidity_list_tb #dy_created_turbidity").remove();


            var virtualsensorid = result.vsensorId;
            var sensorType = result.sensorType;
            var status = result.status;
            var psensorlst = result.psensorList;

            for (var i = 0; i < psensorlst.length; i++) {
                //alert(psensorlst[i].status);
                var newtr = $('<tr id="dy_created_turbidity">').append(
                    $('<td>').append(
                        $('<input type="text" class="form-control" name=' + PhysicalSensor_Location +
                        ' readonly>').val(psensorlst[i].location.location)),
                    $('<td>').append(
                        $('<input id =latitude_' + psensorlst[i].psensorId + 'type="text" class="form-control" name=' + PhysicalSensor_latitude + ' readonly>').val(psensorlst[i].location.latitude)),
                    $('<td>').append(
                        $('<input id =longitude_' + psensorlst[i].psensorId + 'type="text" class="form-control" name=' + PhysicalSensor_longitude + ' readonly>').val(psensorlst[i].location.longitude)),
                    $('<td>').append(
                        $('<span class="badge">' + psensorlst[i].status + '</span>')
                    )
                )

                $("#turbidity_list_tb").append(newtr);
            }
            if(refreshMap==true) {
                addMap(psensorlst);
            }
        },
        complete: function () {
            setTimeout(Refresh_turbidity_lst(false), 1500);
        }
    });
}



