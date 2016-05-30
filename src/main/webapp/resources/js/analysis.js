/**
 * Created by ljtao on 5/17/16.
 */


function analysis() {
    //var category = "sea_water_practical_salinity";

    //
    //$.ajax({
    //    url: "rtvSensorData/sea_water_practical_salinity/2016-05-15/2016-05-15",
    //    type: "GET",
    //    DataType: "json",
    //    error: function (xhr) {
    //        alert("An error occured: " + xhr.status + " " + xhr.statusText);
    //    },
    //    success: function (result) {
    //
    //    alert(result.length);
    //        alert(data);
    //    }
    //});

    var type = $('#selectedtype option:selected').val();
    var strdt = $('#strdate').val();
    var enddt = $('#enddate').val();

    var url = "rtvSensorData/"+type+"/"+strdt+"/"+enddt;
    //alert(url);

    $.getJSON(url, function (data) {
  //  $.getJSON('https://www.highcharts.com/samples/data/jsonp.php?filename=usdeur.json&callback=?', function (data) {
       // alert(data);
        var arr = [];
        //$.each(data, function(key, val) {
        //    var y = val.dataDateTime;
        //    var name = key;
        //    var customTooltip = val.dataValue;
        //    arr.push({dataDateTime: y, dataValue: customTooltip})
        //})
        for(var i=0; i<data.length; i++){
            var datevalue = data[i].dataDateTime;
            var datavalue = data[i].dataValue;
            var year = datevalue.substring(0,4);
            var month = datevalue.substring(5,7);
            var day = datevalue.substring(8,10);
      //      alert(year+"**"+month+"**"+day);
     //       alert(year+"**"+month+"**"+day+ "   " + Date.UTC(year,month,day));
            arr.push([Date.UTC(year,month,day), parseFloat(datavalue)/100]);
        }
     //   alert(arr);

        $('#container-hc').highcharts({
            chart: {
                zoomType: 'x'
            },
            title: {
                text: 'Sensor data over time'
            },
            subtitle: {
                text: document.ontouchstart === undefined ?
                    'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in'
            },
            xAxis: {
                type: 'datetime'
            },
            yAxis: {
                title: {
                    text: 'data value'
                }
            },
            legend: {
                enabled: false
            },
            plotOptions: {
                area: {
                    fillColor: {
                        linearGradient: {
                            x1: 0,
                            y1: 0,
                            x2: 0,
                            y2: 1
                        },
                        stops: [
                            [0, Highcharts.getOptions().colors[0]],
                            [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                        ]
                    },
                    marker: {
                        radius: 2
                    },
                    lineWidth: 1,
                    states: {
                        hover: {
                            lineWidth: 1
                        }
                    },
                    threshold: null
                }
            },

            series: [{
                type: 'area',
                name: 'date',
                data: arr
            //    data: [
            //        [Date.UTC(2013,5,2),0.7695],
            //        [Date.UTC(2013,5,3),0.7648],
            //        [Date.UTC(2013,5,4),0.7645],
            //        [Date.UTC(2013,5,5),0.7638],
            //        [Date.UTC(2013,5,6),0.7549],
            //        [Date.UTC(2013,5,7),0.7562],
            //        [Date.UTC(2013,5,9),0.7574],
            //        [Date.UTC(2013,5,10),0.7543],
            //        [Date.UTC(2013,5,11),0.7510],
            //        [Date.UTC(2013,5,12),0.7498]]
            }]
        });
    });
}