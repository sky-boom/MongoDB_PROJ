//==============================折叠菜单效果==================================
$(function () {
    var Accordion = function (el, multiple) {
        this.el = el || {};
        this.multiple = multiple || false;

        var links = this.el.find('.link');

        links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown)
    }

    Accordion.prototype.dropdown = function (e) {
        var $el = e.data.el;
        $this = $(this),
            $next = $this.next();

        $next.slideToggle();
        $this.parent().toggleClass('open');

        if (!e.data.multiple) {
            $el.find('.submenu').not($next).slideUp().parent().removeClass('open');
        }
        ;
    }

    var accordion = new Accordion($('#accordion'), false);
});

//==========================================ajax分页加载 + 条件查询============================================

var start = 0;     //分页初始页数
var area = null;
var priceStart = 0;
var priceEnd = 999999;
var roomType = null;
var position = null;

function print()
{
    console.log("start = " + start);
    console.log("area = " + area);
    console.log("priceStart = " + priceStart);
    console.log("priceEnd = " + priceEnd);
    console.log("roomType = " + roomType);
    console.log("position = " + position);
}
//点击"所有"时，刷新页面
function reflash(){
    window.location.reload();
}

//初始化
function init(){
    start = 0;
    $("#content").html("");
    queryComb();
    // start += 10;    //下一页
}

$(".condition .area li").click(function(){
    $(this).addClass("checked").siblings().removeClass("checked");
    // area = $(".area .checked").text();
    area = $(".area .checked span").text();

    // start = 10;     //分页初始页数
    // $(".condition .price input:first").attr("checked", true);
    // $(".condition .roomType input").attr("checked", true);
    // $(".condition .position input").attr("checked", true);
    // priceStart = 0;
    // priceEnd = 999999;
    // roomType = null;
    // position = null;
    init();
    print();
});
$(".condition .price li").click(function(){
    $(this).addClass("checked").siblings().removeClass("checked");
    var prices = $(".price .checked span").attr("name").split("-");
    priceStart = prices[0];
    priceEnd = prices[1];
    init();
    print();
});
$(".condition .roomType li").click(function(){
    $(this).addClass("checked").siblings().removeClass("checked");
    // roomType = $(".roomType .checked span").text();
    roomType = $(".roomType .checked span").attr("name");

    init();
    print();
});
$(".condition .position li").click(function(){
    $(this).addClass("checked").siblings().removeClass("checked");
    // position = $(".position .checked span").text();
    position = $(".position .checked span").attr("name");

    init();
    print();
});

//访问后台
function getRentHtml(data)
{
    var html = "";  //拼接所有租房信息
    for (var i = 0; i < data.length; i++)
    {
        html +=
            '<li class="main-show">' +
            '<div class="if-img"></div>' +
            '<div class="info">' +
                '<i class="if-title" name="title">' + data[i].title + '</i>' +
                '<i class="if-mess">' +
                    '<span name="roomType">' + data[i].roomType + '</span> /' +
                    '<span name="square">' + data[i].square + '</span>平米 /' +
                    '<span name="position">' + data[i].position + '</span> /' +
                    '<span name="floor">' + data[i].floor + '</span>层' +
                '</i>' +
                '<i class="if-mess2" name="address">' + data[i].address + '</i>' +
                '<i class="if-mess2" name="area">' + data[i].area + '</i>' +
                '<i class="if-foot">' +
                    '<span class="fa fa-clock-o"></span>' +
                    '1天前维护' +
                '</i>' +
            '</div>' +
                '<i class="if-price"><span name="price">' + data[i].price + '</span>元/月</i>' +
            '</li>'
    }
    return html;
}

//******万能多条件查询*******
function queryComb()
{
    $.post({
        url:"/mongo/query",
        data:{'pageStart': start,
                'area': area,
                'priceStart':priceStart,
                'priceEnd':priceEnd,
                'roomType':roomType,
                'position':position },
        success:function (data) {
            var html = getRentHtml(data);  //拼接所有租房信息
            $("#content").append(html);
            start += 10;    //下一页
            // console.log(data);
            // alert(status);
        }
    });
}

//===================到底触发事件====================
$(window).scroll(function ()
{
	var scrollTop = $(this).scrollTop();    //获取滚动条到顶部的距离
	var scrollHeight = $(document).height();    //获取文档区域高度
	var windowHeight = $(this).height();    //获取滚动条的高度
	if (scrollTop + windowHeight > scrollHeight - 1)
	{
   　　 //此处是滚动条到底部时候触发的事件，在这里写要加载的数据，或者是拉动滚动条的操作
        console.log("到底了,发起请求");
        queryComb();
　　}
});

//选中样式
$(".horizon li").click(function(){
    // $(this).toggleClass("checked");
    $(this).addClass("checked").siblings().removeClass("checked");
})




// ================右侧展示内容同级隐藏===================
$(function(){
    $("#result .list:not(:first)").hide();
});
$(function() {
    //获取点击事件的对象
    $(".submenu li a").click(function() {

        console.log($("#accordion li a").index(this));
        // console.log($(this).siblings());
        console.log("事件触发");
        //获取要显示或隐藏的对象
        var divShow = $("#result").children('.list');
        // console.log(divShow);
        //判断当前对象是否被选中，如果没选中的话进入if循环
        if (!$(this).hasClass('selected')) {
            //获取当前对象的索引
            var index = $("#accordion li a").index(this);
            //当前对象添加选中样式并且其同胞移除选中样式；

            $(this).addClass('selected').parent().parent().parent().siblings('li').children('ul').children('li').children('a').removeClass('selected');
            $(this).parent().siblings().children().removeClass('selected');
            //索引对应的div块显示
            $(divShow[index]).show();

            //控制echart数据生成
            if($(divShow[index]).attr("name") === 'echart' ) {
                getAreaCount_echart(); //api.js
                getAvgPrice_echart();
            }
            //控制热力图数据生成
            // if($(divShow[index]).attr("name") === 'Bmap' ) {
            //     Bmap();
            // }

            //索引对应的div块的同胞隐藏
            $(divShow[index]).siblings('.list').hide();
        }
    });
});