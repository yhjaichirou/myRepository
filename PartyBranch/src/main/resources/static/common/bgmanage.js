$(document).ready(function() {
    (function() {
        showcontent("recommend");
        choosenewstype();
    })();
    $(".text-content-list tr").first().css("background", "cadetblue");
    var code = "";
    // 显示内容
    function showcontent(newstype) {
        $.ajax({
            type: "GET",
            url: 'http://localhost:8080/dchip/newslist',
            data: {
                newstype: newstype
            },
            datatype: "json",
            success: function(data) {
                $("#news-content").html("");
                $.each(data, function(index, value) {
                    var newsid = value.newsid;
                    var tr = $("<tr>").appendTo("#news-content");
                    $("<td>").appendTo(tr).text(value.newsid).css("text-align", "center");
                    $("<td>").appendTo(tr).text(value.newstitle);
                    $("<td>").appendTo(tr).text(value.newscontent);
                    $("<td>").appendTo(tr).text(value.content_url);
                    $("<td>").appendTo(tr).text(value.imgurl);
                    $("<td>").appendTo(tr).text(value.newsfrom);
                    $("<td>").appendTo(tr).text(value.date);
                    var button = $("<td>").appendTo(tr);
                    // 点击修改按钮

                    $("<a>").appendTo(button).text("修改").addClass("modify").click(function() {
                        $.ajax({
                            type: "POST",
                            url: 'http://localhost:8080/dchip/newslist',
                            data: {
                                newsid: newsid
                            },
                            datatype: "json",
                            success: function(data) {
                                // 显示旧信息
                                $("#goback").css("display","block");
                                $(".news-update").show();
                                $(".news-content-list").hide();
                                $("#newstitle").val(data[0].newstitle);
                                $("#newscontent").val(data[0].newscontent);
                                $("#content_url").val(data[0].content_url);
                                $("#imgurl").val(data[0].imgurl);
                                $("#newsfrom").val(data[0].newsfrom);
                                $("#date").val(data[0].date);
                                var parent = $("#newscontent").parent("p");
                                $("<input type='text'>").appendTo(parent).addClass("hide").val(newsid);
                                code = 2;

                            }

                        });

                    });
                    // 删除新闻
                    $("<a>").appendTo(button).text("删除").addClass("delete").click(function() {
                        $.ajax({
                            type: "POST",
                            url: 'http://localhost:8080/dchip/newslist',
                            data: {
                                newsid: newsid,
                            },
                            success: function(data) {
                                alert("删除成功");
                            }
                        });

                    });


                });
            }

        });
        //点击添加按钮
        $("#add").click(function() {
            $(".hidden").html("");
            $("#newstitle").focus();
            $(".news-update").show();
            $(".news-content-list").hide();
            $("#goback").css("display","block");
            $("#newstitle").val();
            $("#newscontent").val();
            $("#content_url").val();
            $("#imgurl").val();
            $("#newsfrom").val();
            $("#date").val();
            $("<input type='text'>").appendTo(".hidden").addClass("hide2").val(newstype);
            code = 1;
        });
    }
    // 获得新闻的类型
    function choosenewstype() {
        $(".text-content-list tr").each(function() {
            $(this).click(function() {
                var type = $(this).children("td").attr("name");
                var text = $(this).children().text();
                $(this).css("background", "cadetblue").siblings().css("background", "darkcyan");
                $(".news-title h2 span").text(text);
                showcontent(type);
            });

        });
    }

    // 返回到原来的页面
    $("#goback").click(function() {
        $(".news-update").hide();
        $(".news-content-list").show();
        $("#goback").css("display","none");
    });
    // 添加新闻
    $("#submit").click(function() {
        // 判断关键信息是否为空
        if ($("#newstitle").val() == "" || $("#content_url").val() == "" || $("#newscontent").val() == "") {
            alert("请完善新闻信息！");
        } else {
            if (code == 1) {
                $.ajax({
                    url: 'http://localhost:8080/dchip/newslist',
                    type: "POST",
                    datatype: "json",
                    data: {
                        newstype: $(".hide2").val(),
                        newstitle: $("#newstitle").val(),
                        newscontent: $("#newscontent").val(),
                        content_url: $("#content_url").val(),
                        imgurl: $("#imgurl").val(),
                        newsfrom: $("#newsfrom").val(),
                        date: $("#date").val()
                    },
                    success: function(data) {
                        alert("添加成功");
                        $(".news-update").hide();
                        $(".news-content-list").show();
                    }
                });
            } else if (code == 2) {
                $.ajax({
                    type: "POST",
                    url: 'http://localhost:8080/dchip/newslist',
                    data: {
                        newsid: $(".hide").val(),
                        newstitle: $("#newstitle").val(),
                        newscontent: $("#newscontent").val(),
                        content_url: $("#content_url").val(),
                        imgurl: $("#imgurl").val(),
                        newsfrom: $("#newsfrom").val(),
                        date: $("#date").val()
                    },
                    success: function(data) {
                        alert('修改成功');
                        $(".news-update").hide();
                        $(".news-content-list").show();
                    }
                });


            }
        }

    });


});
