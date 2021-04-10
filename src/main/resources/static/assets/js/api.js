//------------------echarts入门-------------------------
//ajax异步请求数据
function getAreaCount_echart()
{
    console.log("可以执行获取1");
    $.post({
        url:"/mongo/queryarea",
        success:function (data) {

            var x_data = new Array();
            var y_data = new Array();

            for (var i = 0; i < data.length; i++)
            {
                x_data.push(data[i]._id);
                y_data.push(data[i].count);
            }

            // console.log(data[3]._id);
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('echart1'));

            // 绘制图表
            myChart.setOption({
                title: {
                    text: 'ECharts 地区租房数'
                },
                tooltip: {},
                xAxis: {
                    data: x_data,
                    axisLabel: {
                        interval: 0,
                        formatter:function(value)
                        {
                            debugger
                            var ret = "";//拼接加\n返回的类目项
                            var maxLength = 3;//每项显示文字个数
                            var valLength = value.length;//X轴类目项的文字个数
                            var rowN = Math.ceil(valLength / maxLength); //类目项需要换行的行数
                            if (rowN > 1)//如果类目项的文字大于3,
                            {
                                for (var i = 0; i < rowN; i++) {
                                    var temp = "";//每次截取的字符串
                                    var start = i * maxLength;//开始截取的位置
                                    var end = start + maxLength;//结束截取的位置
                                    //这里也可以加一个是否是最后一行的判断，但是不加也没有影响，那就不加吧
                                    temp = value.substring(start, end) + "\n";
                                    ret += temp; //凭借最终的字符串
                                }
                                return ret;
                            }
                            else {
                                return value;
                            }
                        }
                    }
                },
                yAxis: {},
                series: [{
                    name: '租房量',
                    type: 'bar',
                    data: y_data
                }]
            });
        }
    });
}

function getAvgPrice_echart()
{
    console.log("可以执行获取1");
    $.post({
        url:"/mongo/queryprice",
        success:function (data) {

            var x_data = new Array();
            var y_data = new Array();

            for (var i = 0; i < data.length; i++)
            {
                x_data.push(data[i]._id);
                y_data.push(data[i].avg_price);
            }

            // console.log(data[3]._id);
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('echart2'));

            // 绘制图表
            myChart.setOption({
                title: {
                    text: 'ECharts 各地区平均价格'
                },
                tooltip: {},
                xAxis: {
                    type: 'category',
                    data: x_data,
                    axisLabel: {
                        interval: 0,
                        formatter:function(value)
                        {
                            debugger
                            var ret = "";//拼接加\n返回的类目项
                            var maxLength = 3;//每项显示文字个数
                            var valLength = value.length;//X轴类目项的文字个数
                            var rowN = Math.ceil(valLength / maxLength); //类目项需要换行的行数
                            if (rowN > 1)//如果类目项的文字大于3,
                            {
                                for (var i = 0; i < rowN; i++) {
                                    var temp = "";//每次截取的字符串
                                    var start = i * maxLength;//开始截取的位置
                                    var end = start + maxLength;//结束截取的位置
                                    //这里也可以加一个是否是最后一行的判断，但是不加也没有影响，那就不加吧
                                    temp = value.substring(start, end) + "\n";
                                    ret += temp; //凭借最终的字符串
                                }
                                return ret;
                            }
                            else {
                                return value;
                            }
                        }
                    }
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    name: '平均价格',
                    type: 'line',
                    data: y_data
                }]
            });
        }
    });
}

//---------------------百度api----------------------------
// 创建地图实例
var map = new BMap.Map("map");
//设置地图的中心点
var point = new BMap.Point(114.42, 23.12);	//惠州市经纬度
// 初始化地图，设置中心点坐标和地图级别
map.centerAndZoom(point, 13);
// 允许滚轮缩放
map.enableScrollWheelZoom();
//热力图初始化
heatmapOverlay1 = new BMapLib.HeatmapOverlay({"radius":20});
//地图添加热力图层
map.addOverlay(heatmapOverlay1);

buildHeatMap();
//根据数据生成热力图
function buildHeatMap(){
    // var points = [{"lng":114.526809,"lat":22.450285,"count":9},{"lng":114.526809,"lat":22.450285,"count":5},{"lng":114.526809,"lat":22.450285,"count":141},{"lng":114.526809,"lat":22.450285,"count":21},{"lng":114.466622,"lat":22.798179,"count":69},{"lng":120.674992,"lat":24.146723,"count":51},{"lng":114.451202,"lat":22.771797,"count":147},{"lng":120.674992,"lat":24.146723,"count":70},{"lng":114.526809,"lat":22.450285,"count":94},{"lng":114.526809,"lat":22.450285,"count":82},{"lng":120.674992,"lat":24.146723,"count":50},{"lng":114.526809,"lat":22.450285,"count":90},{"lng":114.451202,"lat":22.771797,"count":138},{"lng":114.526809,"lat":22.450285,"count":74},{"lng":114.47483,"lat":22.781175,"count":21},{"lng":114.468504,"lat":22.800674,"count":39},{"lng":114.467608,"lat":22.78534,"count":22},{"lng":114.457016,"lat":22.78719,"count":82}];
    var points = "";
    $.post({
        url:"/mongo/getLngLat",
        async :false,
        success:function (datas) {
            points = datas;
        }
    });
    console.log(points);
    //热力图层添加数据
    heatmapOverlay1.setDataSet({data:points,max:100});
}

//显示热力图
function openHeatmap(){
    heatmapOverlay1.show();
}
//关闭热力图
function closeHeatmap(){
    heatmapOverlay1.hide();
}
closeHeatmap();

function getPoints(){}