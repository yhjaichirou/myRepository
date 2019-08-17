$(document).ready(function() {
    (function() {
        showcontent("recommend");
        choosenewstype();
    })();

    function showcontent(newstype) {
        $.ajax({
            type: "GET",
            url: 'http://localhost:8080/dchip/newslist',
            data: {
                newstype: newstype
            },
            datatype: "json",
            success: function(data) {
                $(".index-view-subpage-feed").html("");
                if (data == "blank") {
                    $("<div>").appendTo(".index-view-subpage-feed").text("没有该类型的新闻").css({
                        "color": "#333",
                        "font-size": "15px"
                    });
                } else {
                    $.each(data, function(index, value) {
                        // 给图片板块单独设置一个样式
                        if (!(newstype == "picture")) {
                            var list = $("<div>").appendTo(".index-view-subpage-feed").addClass("index-list");
                            var item = $("<a>").appendTo(list).addClass("index-list-item").attr("href", value.content_url);
                            var main = $("<div>").appendTo(item).addClass("index-list-main");
                            // 判断图片是否为空
                            if (value.imgurl != "") {
                                var list_img = $("<div>").addClass("index-list-img").appendTo(main);
                                var img = $("<img>").attr("src", value.imgurl).appendTo(list_img);
                            }
                            var text = $("<div>").addClass("index-list-text").appendTo(main);
                            var span = $("<span>").addClass("index-list-text-title").appendTo(text).text(value.newstitle);
                            var span = $("<span>").addClass("index-list-text-abs").appendTo(text).text(value.newscontent);
                            var bottom = $("<div>").addClass("index-list-bottom").appendTo(main);
                            var time = $("<div>").addClass("index-list-time").appendTo(bottom);
                            // 判断新闻标签是否为空
                            if (value.newsfrom != "") {
                                var b = $("<b>").addClass("topnews").appendTo(time).text(value.newsfrom);
                            }
                            var b = $("<b>").addClass("index-time").appendTo(time).text(value.date);
                            // 特定页面不显示轮播图
                            if (!(newstype == "recommend" || newstype == "bendi" || newstype == "yule")) {
                                $(".index-view-subpage").css("display", "none");
                            } else {
                                $(".index-view-subpage").css("display", "block");
                            }
                            if (!(newstype == "recommend" || newstype == "yule")) {
                                $(".ui-hotword").css("display", "none");
                            } else {
                                $(".ui-hotword").css("display", "block");
                            }

                        } else {
                            $(".index-view-subpage").css("display", "none");
                            $(".ui-hotword").css("display", "none");
                            var list = $("<div>").appendTo(".index-view-subpage-feed").addClass("index-list");
                            var item = $("<a>").appendTo(list).addClass("index-list-item").attr("href", value.content_url);
                            var main = $("<div>").appendTo(item).addClass("index-img");
                            $("<img>").attr("src", value.imgurl).appendTo(main);
                            var text = $("<div>").addClass("index-list-main-text").appendTo(main);
                            var span1 = $("<span>").addClass("index-list-main-title").appendTo(text).text(value.newstitle);
                            var span1 = $("<span>").addClass("index-list-main-abs").appendTo(text).text(value.newscontent);
                        }

                    });
                }


            }
        });

    }

    // 选择新闻类型
    function choosenewstype() {
        $(".list span").each(function(index) {
            $(this).click(function() {
                var newstype = $(this).attr("name");
                showcontent(newstype);
                $(this).addClass("cur").parents("td").siblings().children().children("span").removeClass("cur");
                $(this).addClass("cur").parents("tr").siblings().children().children().children("span").removeClass("cur");
            });
        });
    }





});
